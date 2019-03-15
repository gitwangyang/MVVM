package com.dotawang.mvvm.utils.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.webkit.WebView;

/**
 * Created on 2019/3/15
 * Title: 首页左右可滑动webview
 * @author Android-汪洋
 * @Description: 如果后期webview要统一管理，建议此处继承的是自定义的webview
 */
public class SlideWebView extends WebView {

    private int touchSlop;

    public SlideWebView(Context context) {
        super(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public SlideWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public SlideWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private float mLastMotionX;
    private float mLastMotionY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final float x = ev.getX();
        final float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - mLastMotionX) < Math.abs(y - mLastMotionY) && Math.abs(x - mLastMotionX) > touchSlop) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
