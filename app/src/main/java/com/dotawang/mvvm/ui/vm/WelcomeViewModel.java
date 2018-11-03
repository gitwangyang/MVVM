package com.dotawang.mvvm.ui.vm;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dotawang.mvvm.R;
import com.dotawang.mvvm.ui.activity.MainActivity;
import com.dotawang.mvvm.base.BaseViewModel;
import com.dotawang.mvvm.binding.command.BindingAction;
import com.dotawang.mvvm.binding.command.BindingCommand;
import com.dotawang.mvvm.utils.ButtonClickUtils;


/**
 * Created by Dota.Wang on 2018/10/24.
 */

public class WelcomeViewModel extends BaseViewModel {
    //判断是否点击跳转按钮
    private boolean isClickSkip;

    private int welcomeimg;

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
        if (!ButtonClickUtils.isFastDoubleClick()){
            startActivity(MainActivity.class);
            setClickSkip(false);
            //关闭页面
            ((Activity) context).finish();
        }
    }

    //localimg  为网络返回的url
    @BindingAdapter("bind:welcomeimg")
    public static void getInternetImage(ImageView imageView, int welcomeimg){
        Glide.with(imageView.getContext()).load(R.mipmap.ic_launcher).into(imageView);
    }

    public boolean isClickSkip() {
        return isClickSkip;
    }

    public void setClickSkip(boolean clickSkip) {
        isClickSkip = clickSkip;
    }

    public int getWelcomeimg() {
        return welcomeimg;
    }

    public void setWelcomeimg(int welcomeimg) {
        this.welcomeimg = welcomeimg;
    }
}
