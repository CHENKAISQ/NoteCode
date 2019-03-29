package com.test.ck.coordinatorlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by W541 on 2019/3/15.
 */

public class FristBehavior extends CoordinatorLayout.Behavior<View> {
    private int direChange;

    public FristBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    /**
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param axes
     * @param type
     * @return
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }



    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if (dy > 0 && direChange < 0 || dy < 0 && direChange > 0){
            direChange = 0;
        }

        direChange += dy;
        if (direChange > child.getHeight() && child.getVisibility() == View.INVISIBLE){
            //不能使用gone
            child.setVisibility(View.VISIBLE);
         }else if (direChange < 0 && child.getVisibility() == View.VISIBLE){
            child.setVisibility(View.INVISIBLE);
        }
    }

}
