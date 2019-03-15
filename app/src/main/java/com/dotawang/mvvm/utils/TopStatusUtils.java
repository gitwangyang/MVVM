package com.dotawang.mvvm.utils;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created on 2019/3/15
 * Title:顶部状态栏的颜色
 * @author Android-汪洋
 * @Description:
 */
public class TopStatusUtils {
    /**
     * 将状态栏改变成对应的颜色 用tag标记来区分界面颜色
     * @param context
     * @param color
     */
    public static void statusTintManager(Activity context, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = context.getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            context.getWindow().setStatusBarColor(color);
            return;
        }
    }
}
