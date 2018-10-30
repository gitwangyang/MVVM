package com.dotawang.mvvm.ui;

import android.databinding.Observable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.dotawang.mvvm.BR;
import com.dotawang.mvvm.R;
import com.dotawang.mvvm.base.BaseActivity;
import com.dotawang.mvvm.databinding.ActivityLoginBinding;
import com.dotawang.mvvm.ui.vm.LoginViewModel;
import com.dotawang.mvvm.utils.EditTextUtils;

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
        //监听ViewModel中pSwitchObservable的变化, 当ViewModel中执行【uc.pSwitchObservable.set(!uc.pSwitchObservable.get());】时会回调该方法
        viewModel.uc.pSwitchObservable.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                //pSwitchObservable是boolean类型的观察者,所以可以直接使用它的值改变密码开关的图标
                if (viewModel.uc.pSwitchObservable.get()){
                    //密码可见
                    //在xml中定义id后,使用binding可以直接拿到这个view的引用,不再需要findViewById去找控件了
                    binding.ivSwichPasswrod.setImageResource(R.drawable.show_psw_press);
                    binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    //密码不可见
                    binding.ivSwichPasswrod.setImageResource(R.drawable.show_psw);
                    binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                //光标置末尾
                binding.etPassword.setSelection(binding.etPassword.length());
            }
        });
        binding.etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EditTextUtils.setEditTextInputSpace(charSequence,binding.etUsername);
                EditTextUtils.setEditTextInputSpeChat(binding.etUsername);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString().trim())){
                    binding.ivClearUsername.setVisibility(View.VISIBLE);
                }else {
                    binding.ivClearUsername.setVisibility(View.INVISIBLE);
                }
            }
        });
        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EditTextUtils.setEditTextInputSpace(charSequence,binding.etPassword);
                EditTextUtils.setEditTextInputSpeChat(binding.etPassword);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString().trim())){
                    binding.ivSwichPasswrod.setVisibility(View.VISIBLE);
                }else {
                    binding.ivSwichPasswrod.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}
