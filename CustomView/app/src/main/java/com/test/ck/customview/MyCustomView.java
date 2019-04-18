package com.test.ck.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ck on 2019/4/10.
 * 继承view
 */

public class MyCustomView extends View {
    private Paint paint;
    private int color;

    public MyCustomView(Context context) {
        super(context);
        init();
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView);
        color = typedArray.getColor(R.styleable.MyCustomView_viewColor, Color.BLACK);
        typedArray.recycle();
        init();
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        //获取自定义属性值

//        Paint.ANTI_ALIAS_FLAG ：抗锯齿标志
//        Paint.FILTER_BITMAP_FLAG : 使位图过滤的位掩码标志
//        Paint.DITHER_FLAG : 使位图进行有利的抖动的位掩码标志
//        Paint.UNDERLINE_TEXT_FLAG : 下划线
//        Paint.STRIKE_THRU_TEXT_FLAG : 中划线
//        Paint.FAKE_BOLD_TEXT_FLAG : 加粗
//        Paint.LINEAR_TEXT_FLAG : 使文本平滑线性扩展的油漆标志
//        Paint.SUBPIXEL_TEXT_FLAG : 使文本的亚像素定位的绘图标志
//        Paint.EMBEDDED_BITMAP_TEXT_FLAG : 绘制文本时允许使用位图字体的绘图标志
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setColor(color);
        paint.setStrokeWidth(2);
    }
    //直接继承view,在xml中的wrap_content和match_content效果一样,
    // wrap_content若要生效,需要重写onmeasure方法,指定一个默认的宽和高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //测量大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //AT_MOST：最大模式，对应于wrap_comtent属性，子View的最终大小是父View指定的SpecSize值，并且子View的大小不能大于这个值
        //EXACTLY：精确模式，对应于 match_parent 属性和具体的数值，父容器测量出 View所需要的大小，也就是SpecSize的值
        if (MeasureSpec.AT_MOST == widthMode && MeasureSpec.AT_MOST == heightMode){
            //宽高均是wrap_content,设置默认宽高
            setMeasuredDimension(500,500);

        }else if (MeasureSpec.AT_MOST == widthMode){
            //宽是wrap_content,设置默认宽度
            setMeasuredDimension(500,heightSize);

        }else if (MeasureSpec.AT_MOST == heightMode){
            //高是wrap_content,设置默认高度
            setMeasuredDimension(widthSize,500);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //整个控件的宽和高(包含padding)
        int width = getWidth();
        int height = getHeight();
        //若要padding生效,需要做以下处理
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        //根据padding值画正方形
        canvas.drawRect(paddingLeft,paddingTop,width - paddingRight,height - paddingBottom,paint);
    }
}
