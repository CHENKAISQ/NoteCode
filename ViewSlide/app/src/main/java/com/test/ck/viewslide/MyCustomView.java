package com.test.ck.viewslide;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;


/**
 * Created by ck on 2019/3/21.
 */

public class MyCustomView extends View {
    private float x;
    private float y;
    private Scroller scroller;

    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化Scroller
        scroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //获取触摸点的横纵坐标
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

//                scollTo和scollBy移动的是View的内容,如果在ViewGroup中使用,则是移动其所有的子View
//                dx,dy必须是负的
//                scrollBy(-dx,-dy);
//                ((View)getParent()).scrollBy(-dx,-dy);

//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + dx;
//                layoutParams.topMargin = getTop() + dy;
//                setLayoutParams(layoutParams);
//                offsetLeftAndRight(dx);
//                offsetTopAndBottom(dy);
//                layout(getLeft() + dx,getTop() + dy,getRight() + dx,getBottom() + dy);
                break;
            case MotionEvent.ACTION_UP:
//                smoothTo(event);
                break;
        }
        return true;
    }

    public void smoothTo(int x1,int y1) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int disX = x1 - scrollX;
        int disY = y1 - scrollY;
        scroller.startScroll(scrollX,scrollY,disX,disY,2000);
        invalidate();
    }

    /**
     * 此方法会在onDraw()方法中调用
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }
    }
}
