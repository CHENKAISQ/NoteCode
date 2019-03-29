package com.test.ck.snackbar;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private Button tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (Button) findViewById(R.id.tv);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar();
            }
        });
    }

    private void showSnackbar() {
//        Snackbar的make方法的第一个参数是一个View类型的参数，
//         这个View类型的参数是Snackbar的父控件，所以选择合适的父控件以确保Snackbar能显示在正确的位置
        Snackbar.make(constraintLayout,"提示内容",Snackbar.LENGTH_LONG)
                .setAction("点击事件", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"123456",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
