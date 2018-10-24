package com.dotawang.mvvm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dotawang.mvvm.dialog.CommonDialog;
import com.dotawang.mvvm.dialog.DialogUtils;
import com.dotawang.mvvm.utils.ToastUtils;
import com.dotawang.mvvm.utils.Tool;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private static Boolean isQuit = false;
    private Timer timer = new Timer();
    private TimerTask task;
    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showDialog(MainActivity.this, "title", "content", "<font color=#999999>取消</font>", "<font color=#f24957>确定</font>", true, new CommonDialog.ICommonDialogListener() {
                    @Override
                    public void onConfirmClick() {

                    }

                    @Override
                    public void onCancelClick() {
                    }
                });
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isQuit){
            isQuit = true;
            ToastUtils.showShort(getResources().getString(R.string.fish_app));
            task = new TimerTask() {
                @Override
                public void run() {
                    isQuit = false;
                }
            };
            timer.schedule(task,2000);
        }else {
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
