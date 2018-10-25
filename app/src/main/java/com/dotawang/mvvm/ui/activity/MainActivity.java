package com.dotawang.mvvm.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.dotawang.mvvm.BR;
import com.dotawang.mvvm.R;
import com.dotawang.mvvm.base.BaseActivity;
import com.dotawang.mvvm.databinding.ActivityMainBinding;
import com.dotawang.mvvm.ui.vm.MainViewModel;
import com.dotawang.mvvm.utils.Tool;

/**
 *
 */
public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewModel> {

    private long mExitTime = 0;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewmodel;
    }

    @Override
    public MainViewModel initViewModel() {
        return new MainViewModel(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点击二次退出应用
     */
    public void exit(){
        if ((System.currentTimeMillis() - mExitTime)>2000){
            Tool.showToast(getResources().getString(R.string.fish_app));
            mExitTime = System.currentTimeMillis();
        }else {
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
