package com.dotawang.mvvm.base;

public interface IBaseViewModel {
//    void initData();

    /**
     * View的界面创建时回调
     */
    void onCreate();

    /**
     * View的界面销毁时回调
     */
    void onDestroy();

    /**
     * 注册RxBus
     */
    void registerRxBus();
    /**
     * 移除RxBus
     */
    void removeRxBus();
}
