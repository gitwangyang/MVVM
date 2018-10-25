package com.dotawang.mvvm.ui.vm;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Handler;
import android.text.TextUtils;

import com.dotawang.mvvm.ui.activity.MainActivity;
import com.dotawang.mvvm.base.BaseViewModel;
import com.dotawang.mvvm.binding.command.BindingAction;
import com.dotawang.mvvm.binding.command.BindingCommand;
import com.dotawang.mvvm.utils.ToastUtils;

/**
 * Created by Dota.Wang on 2018/10/14.
 */

public class LoginViewModel extends BaseViewModel {
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");
    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //密码开关观察者
        public ObservableBoolean pSwitchObservable = new ObservableBoolean(false);
    }

    public LoginViewModel(Context context) {
        super(context);
    }

    //清除用户名的点击事件, 逻辑从View层转换到ViewModel层
    public BindingCommand clearUserNameOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            userName.set("");
        }
    });
    //密码显示开关  (你可以尝试着狂按这个按钮,会发现它有防多次点击的功能)
    public BindingCommand passwordShowSwitchOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //让观察者的数据改变,在View层的监听则会被调用
            uc.pSwitchObservable.set(!uc.pSwitchObservable.get());
        }
    });
    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });

    /**
     * 网络模拟一个登陆操作
     **/
    private void login() {
        if (TextUtils.isEmpty(userName.get().trim())) {
            ToastUtils.showShort("请输入账号！");
            return;
        }
        if (TextUtils.isEmpty(password.get().trim())) {
            ToastUtils.showShort("请输入密码！");
            return;
        }
        showDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialog();
                //进入DemoActivity界面
                startActivity(MainActivity.class);
                //关闭页面
                ((Activity)context).finish();
            }
        },3*1000);
    }
}
