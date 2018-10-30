package com.dotawang.mvvm.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.dotawang.mvvm.BR;
import com.dotawang.mvvm.R;
import com.dotawang.mvvm.base.BaseActivity;
import com.dotawang.mvvm.databinding.ActivitySplashBinding;
import com.dotawang.mvvm.ui.vm.SplashViewModel;
import com.dotawang.mvvm.utils.AppUtils;

/**
 * 启动页
 * Created by Dota.Wang on 2018/10/23.
 */

public class SplashActivity extends BaseActivity<ActivitySplashBinding,SplashViewModel> {

    @Override
    public void initBeforeCreate() {
        super.initBeforeCreate();
        //给欢迎页的背景置空，释放内存
        getWindow().setBackgroundDrawable(null);
        AppUtils.initStatus(this);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public int initVariableId() {
        return BR.viewmodel;
    }

    @Override
    public SplashViewModel initViewModel() {
        return new SplashViewModel(this);
    }

    @Override
    public void initData() {
        super.initData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,WelcomeActivity.class));
                SplashActivity.this.finish();
            }
        },3000);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

}
