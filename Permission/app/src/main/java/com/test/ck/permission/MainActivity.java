package com.test.ck.permission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("tag","onCreate");
//        TextView tv_phone = (TextView) findViewById(R.id.tv_phone);
//        tv_phone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //检查权限
//                if (Build.VERSION.SDK_INT >= 23) {
//                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
//                            != PackageManager.PERMISSION_GRANTED) {
//                        authorize();
//                    }else{
//                        callPhone();
//                    }
//                }else{
//                    callPhone();
//                }
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("tag","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("tag","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("tag","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tag","onDestroy");
    }

    /**
     * 打电话
     */
    @SuppressLint("MissingPermission")
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri parse = Uri.parse("tel:" + "12345");
        intent.setData(parse);
        startActivity(intent);
    }

    /**
     * 授权
     */
    private void authorize() {
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1001);
    }

    /**
     * 申请权限回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1001){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //用户授权去拨打电话
                callPhone();
            }else{
                //处理不再询问,弹窗提醒用户
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.CALL_PHONE)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    AlertDialog alertDialog = builder.setMessage("电话权限被拒绝,拨打电话功能不能使用,请到设置中开启")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .create();
                    alertDialog.show();
                }
                Toast.makeText(MainActivity.this,"拨打电话权限被拒绝",Toast.LENGTH_SHORT).show();

            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
