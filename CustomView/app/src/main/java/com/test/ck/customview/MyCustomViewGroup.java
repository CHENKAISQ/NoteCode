package com.test.ck.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by ck on 2019/4/12.
 */

public class MyCustomViewGroup extends ViewGroup {

    private Scroller scroller;
    private VelocityTracker tracker;

    public MyCustomViewGroup(Context context) {
        super(context);
        //初始化
        init();
    }

    public MyCustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext());
        //快速滑动到其他页面
        tracker = VelocityTracker.obtain();
    }


    //如果父容器的MeasureSpec属性为AT_MOST，子元素的LayoutParams属性为WRAP_CONTENT，我们会发现子元素的MeasureSpec
    // 属性也为AT_MOST，它的SpecSize值为父容器的SpecSize减去padding的值。换句话说，这和子元素设置LayoutParams属性
    // 为MATCH_PARENT效果是一样的
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //测量大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //MyCustomViewGroup的pad定值和子view的margin值未计算入内
        //没有子view
        if (getChildCount() == 0) {
            //设置宽高均为0
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            //宽高模式均是AT_MOST 设置宽为子view宽的总和,高为最高的子view的高度
            int totalWid = 0;
            int totalHet = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                totalWid += childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (totalHet < measuredHeight) {
                    totalHet = measuredHeight;
                }
            }
            setMeasuredDimension(totalWid, totalHet);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //宽模式为AT_MOST
            int totalWid = 0;

            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                totalWid += childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
            }
            setMeasuredDimension(totalWid, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //高模式为AT_MOST

            int totalHet = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);

                int measuredHeight = childAt.getMeasuredHeight();
                if (totalHet < measuredHeight) {
                    totalHet = measuredHeight;
                }
            }
            setMeasuredDimension(widthSize, totalHet);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /*遍历所有的子元素。如果子元素不是GONE，则调用子元素的
        layout方法将其放置到合适的位置上。这相当于默认第一个子元素占满
        了屏幕，后面的子元素就是在第一个屏幕后面紧挨着和屏幕一样大小的
        后续元素。所以left是一直累加的，top保持为0，bottom保持为第一个元
        素的高度，right就是left+元素的宽度(MyCustomViewGroup的pad定值和子view的margin值未计算入内)*/
        int left = 0;
        int childCount = getChildCount();
        View child;
        int totalHet = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            int measuredHeight = childAt.getMeasuredHeight();
            if (totalHet < measuredHeight) {
                totalHet = measuredHeight;
            }
        }
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                int measuredWidth = child.getMeasuredWidth();
                //每个字view的宽度都是一样的
                childWidth = measuredWidth;
                child.layout(left, 0, left + measuredWidth, totalHet);
                left += measuredWidth;
            }
        }

    }

    int lastX;
    int lastY;
    int currentIndex = 0;//当前子元素
    int childWidth = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int detalX = x - lastX;
                scrollBy(-detalX, 0);
                break;
            case MotionEvent.ACTION_UP:
                //相对于当前View滑动的距离,正为向左,负为向右(getScrollX是不断叠加或叠减的,向左滑叠加,向右滑叠减)
                int ditance = getScrollX() - currentIndex * childWidth;
                if (Math.abs(ditance) > childWidth / 2) {
                    if (ditance > 0) {
                        currentIndex++;
                    } else {
                        currentIndex--;
                    }
                }else {
                    tracker.computeCurrentVelocity(2000);
                    float xVelocity = tracker.getXVelocity();//水平移动速度
                    if (Math.abs(xVelocity) > 50){//水平速度大于50,即为快速滑动
                        if (xVelocity > 0) {
                            currentIndex--;
                        } else {
                            currentIndex++;
                        }
                    }
                }

                //currentIndex<0时赋值为0;大于getChildCount() -1时赋值为getChildCount() -1
                currentIndex = currentIndex < 0 ? 0 : (currentIndex > getChildCount() - 1 ? getChildCount() - 1 : currentIndex);
                smoothTo(currentIndex * childWidth, 0);
                //重置tracker
                tracker.clear();
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }

    public void smoothTo(int x1, int y1) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int disX = x1 - scrollX;
        int disY = y1 - scrollY;
        scroller.startScroll(scrollX, scrollY, disX, disY, 2000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
}
