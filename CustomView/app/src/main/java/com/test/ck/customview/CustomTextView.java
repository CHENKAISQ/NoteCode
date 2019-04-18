package com.test.ck.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ck on 2019/4/10.
 * 继承系统控件
 */

public class CustomTextView extends TextView {

    private Paint paint;

    public CustomTextView(Context context) {
        super(context);
        init();
    }
    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        invalidate();
    }


    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
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
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStrokeWidth(2);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        //画斜线
        canvas.drawLine(0,0,width,height,paint);
    }
}
