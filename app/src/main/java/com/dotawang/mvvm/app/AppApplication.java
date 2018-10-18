package com.dotawang.mvvm.app;

import com.dotawang.mvvm.base.BaseApplication;

/**
 * 全局管理
 * Created by Dota.Wang on 2018/10/11.
 */

public class AppApplication extends BaseApplication{
    //todo 需要修改  参照MVVMHabit
    private static AppApplication sInstance;

    @Override
    public void onCreate() {
        sInstance = this;
        super.onCreate();
    }

    public static AppApplication getInstance() {
        return sInstance;
    }
}
