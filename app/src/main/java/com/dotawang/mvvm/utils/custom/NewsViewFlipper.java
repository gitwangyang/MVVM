package com.dotawang.mvvm.utils.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

/**
 * Created on 2019/3/15
 * Title: 首页公告循环播放view
 * @author Android-汪洋
 * @Description:
 */
public class NewsViewFlipper extends ViewFlipper {
    public NewsViewFlipper(Context context) {
        super(context);
    }

    public NewsViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e) {
            stopFlipping();
        }
    }
}
