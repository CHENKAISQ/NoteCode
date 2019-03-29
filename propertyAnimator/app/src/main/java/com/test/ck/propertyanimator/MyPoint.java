package com.test.ck.propertyanimator;

/**
 * Created by ck on 2019/3/29.
 */

public class MyPoint {
    // 设置两个变量用于记录坐标的位置
    private float x;
    private float y;

    // 构造方法用于设置坐标
    public MyPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // get方法用于获取坐标
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
