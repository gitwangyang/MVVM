package com.dotawang.mvvm.app;

import com.dotawang.mvvm.R;
import com.dotawang.mvvm.base.BaseApplication;
import com.dotawang.mvvm.crash.CaocConfig;
import com.dotawang.mvvm.ui.LoginActivity;
import com.dotawang.mvvm.utils.KLog;

/**
 * 全局管理
 * Created by Dota.Wang on 2018/10/11.
 */

public class AppApplication extends BaseApplication{
    private static AppApplication sInstance;

    @Override
    public void onCreate() {
        sInstance = this;
        super.onCreate();
        //开启打印日志
        KLog.init(true);
        //初始化全局异常崩溃
        initCrash();
    }

    private void initCrash() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(LoginActivity.class) //重新启动后的activity
//                .errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
//                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
    }

    public static AppApplication getInstance() {
        return sInstance;
    }
}
