package com.test.ck.propertyanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ck on 2019/3/29.
 */

public class CriView extends View{
    public static final float RADIUS = 70f;// 圆的半径 = 70
    public MyPoint currentPoint;// 当前点坐标
    private Paint mPaint;// 绘图画笔

    public CriView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 如果当前点坐标为空(即第一次)
        if (currentPoint == null) {
            // 创建一个点对象(坐标是(70,70))
            currentPoint = new MyPoint(RADIUS, RADIUS);
            // 在该点画一个圆:圆心 = (70,70),半径 = 70
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x, y, RADIUS, mPaint);
        }else{
            // 如果坐标值不为0,则画圆
            // 所以坐标值每改变一次,就会调用onDraw()一次,就会画一次圆,从而实现动画效果
            // 在该点画一个圆:圆心 = (30,30),半径 = 30
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x, y, RADIUS, mPaint);
        }
    }

    public void setPonit(MyPoint ponit) {
        this.currentPoint = ponit;
    }

    public MyPoint getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(MyPoint currentPoint) {
        this.currentPoint = currentPoint;
        invalidate();
    }
}
