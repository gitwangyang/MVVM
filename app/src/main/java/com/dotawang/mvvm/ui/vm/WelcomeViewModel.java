package com.dotawang.mvvm.ui.vm;

import android.app.Activity;
import android.content.Context;

import com.dotawang.mvvm.MainActivity;
import com.dotawang.mvvm.base.BaseViewModel;
import com.dotawang.mvvm.binding.command.BindingAction;
import com.dotawang.mvvm.binding.command.BindingCommand;
import com.dotawang.mvvm.ui.activity.WelcomeActivity;


/**
 * Created by Dota.Wang on 2018/10/24.
 */

public class WelcomeViewModel extends BaseViewModel {

    public BindingCommand skipToMainOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            skipToMain();
        }
    });

    public WelcomeViewModel(Context context) {
        super(context);
    }

    /**
     * 从欢迎页跳转到首页
     */
    private void skipToMain() {
        startActivity(MainActivity.class);
        //关闭页面
        ((Activity) context).finish();

    }
}
