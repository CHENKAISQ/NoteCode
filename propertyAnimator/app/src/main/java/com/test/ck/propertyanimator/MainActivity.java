package com.test.ck.propertyanimator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private Button button;
    private CriView criView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        button = (Button) findViewById(R.id.button);
        criView = (CriView) findViewById(R.id.criView);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v
             *  ObjectAnimator.ofFloat(Object object, String property, float ....values);
             *  参数说明
             *  object 需要操作的对象
             *  property 需要操作的对象的属性(这个属性必须要有get和set方法)
             *   float ....values：动画初始值 & 结束值（不固定长度）
             */
            @Override
            public void onClick(View v) {
                //ObjectAnimator实现的简单平移
//                startTranslation();
                //ValueAnimator.ofObject实现动画
//                startVaueObject();
                //ObjectAnimator.ofObject实现动画
//                startObjectObj();
                ObjectAnimator oa1 = ObjectAnimator.ofFloat(iv, "translationX", 0, 200);
                ObjectAnimator oa2 = ObjectAnimator.ofFloat(iv, "alpha", 1f, 0f, 1f);
                ObjectAnimator oa3 = ObjectAnimator.ofFloat(iv, "scaleX", 1f, 0.5f, 1f);
                AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.play(oa1).with(oa2).after(oa3);
//                animatorSet.playSequentially(oa1,oa2,oa3);
                animatorSet.playTogether(oa1,oa2,oa3);
                animatorSet.setDuration(5000);
                animatorSet.start();
            }
        });

    }

    private void startObjectObj() {
        MyPoint startPoint = new MyPoint(CriView.RADIUS, CriView.RADIUS);// 初始点为圆心(70,70)
        MyPoint endPoint = new MyPoint(200, 250);// 结束点为(200, 250)
        ObjectAnimator oa = ObjectAnimator.ofObject(criView, "currentPoint", new PointEvaluator(), startPoint, endPoint);
        oa.setDuration(3000);
        oa.start();
    }

    private void startVaueObject() {
        MyPoint startPoint = new MyPoint(CriView.RADIUS, CriView.RADIUS);// 初始点为圆心(70,70)
        MyPoint endPoint = new MyPoint(200, 250);// 结束点为(200, 250)
        ValueAnimator va = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        va.setDuration(3000);
        va.start();
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                MyPoint animatedValue = (MyPoint) animation.getAnimatedValue();
                criView.setPonit(animatedValue);
                //equestLayout方法只会导致当前view的measure和layout，而draw不一定被执行，
                // 只有当view的位置发生改变才会执行draw方法，因此如果要使当前view重绘需要调用invalidate
//                        criView.requestLayout();
                criView.invalidate();
            }
        });
    }

    private void startTranslation() {
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationX", 0, 200);
        //设置动画时长
        oa.setDuration(2000);
        //设置动画延迟播放时间
        oa.setStartDelay(500);
        // 设置动画重复播放次数 = 重放次数+1 动画播放次数 = infinite时,动画无限重复
        oa.setRepeatCount(1);
        // 设置重复播放动画模式 ValueAnimator.RESTART(默认):正序重放;ValueAnimator.REVERSE:倒序回放
        oa.setRepeatMode(ValueAnimator.REVERSE);
        oa.start();
    }
}
