package com.test.ck.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ck on 2019/3/15.
 */

public class LetsgoBehavior extends CoordinatorLayout.Behavior {
    public LetsgoBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 根据条件过滤判断返回值，返回true联动，返回flase不联动，即behavior不生效
     * @param parent 当前的CoordinatorLayout
     * @param child 设置Behavior 的View
     * @param dependency 我们关心的那个View
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return true;
    }

    /**
     * 根据我们关心的View的变化来对我们设置Behavior的View进行一系列处理
     * @param parent 当前的CoordinatorLayout
     * @param child 设置Behavior 的View
     * @param dependency 我们关心的那个View
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int top = dependency.getTop();
        int left = dependency.getLeft();
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        layoutParams.topMargin = top - 200;
        layoutParams.leftMargin = left;
        return true;
    }
}
