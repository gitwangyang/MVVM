package com.andview.refreshview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by 2144 on 2017/4/25.
 */

public class XWebView extends WebView {

    private boolean isTop = false;

    public XWebView(Context context) {
        super(context);
    }

    public XWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public XWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isBottom() {
        return getContentHeight()*getScale() == getHeight() + getScrollY();
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (scrollY <= 0) {
            if (!isTop && deltaY < 0) {
                isTop = true;
            } else {
                isTop = false;
            }
        } else {
            isTop = false;
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    public boolean isTop() {
        return isTop;
    }

    @Override
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }
}
