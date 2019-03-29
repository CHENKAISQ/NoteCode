package com.test.ck.coordinatorlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_2;
    private float x1;
    private ViewGroup.MarginLayoutParams layoutParams;
    private int top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        layoutParams = (ViewGroup.MarginLayoutParams) tv_2.getLayoutParams();
        tv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CooActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                top = tv_2.getTop();
                x1 = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getY();
                int abs = (int) (x - x1);
                layoutParams.topMargin = top + abs;
                tv_2.setLayoutParams(layoutParams);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
