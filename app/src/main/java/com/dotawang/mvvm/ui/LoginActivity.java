package com.dotawang.mvvm.ui;

import android.os.Bundle;

import com.dotawang.mvvm.BR;
import com.dotawang.mvvm.R;
import com.dotawang.mvvm.base.BaseActivity;
import com.dotawang.mvvm.databinding.ActivityLoginBinding;
import com.dotawang.mvvm.ui.vm.LoginViewModel;

/**
 * 登录界面
 * Created by Dota.Wang on 2018/10/14.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel>{
    //ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewmodel;
    }

    @Override
    public LoginViewModel initViewModel() {
        //View持有ViewModel的引用 (这里暂时没有用Dagger2解耦)
        return new LoginViewModel(this);
    }

    @Override
    public void initViewObservable() {

    }
}
