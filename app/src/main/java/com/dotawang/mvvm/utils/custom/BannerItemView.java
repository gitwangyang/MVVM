package com.dotawang.mvvm.utils.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created on 2019/3/15
 * Title: 可缩小成圆放大成圆角矩形的view
 * @author Android-汪洋
 * @Description:
 */
public class BannerItemView extends View {
    private Paint mPaint;
    private float rectWidth;
    private RectF rectF;

    public BannerItemView(Context context) {
        super(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectF = new RectF();
        mPaint.setColor(Color.WHITE);
    }

    public BannerItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectF = new RectF();
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.left = getWidth() / 2 - getHeight() / 2 - rectWidth;
        rectF.right = getHeight() / 2 + getWidth() / 2 + rectWidth;
        rectF.top = 0;
        rectF.bottom = getHeight();
        canvas.drawRoundRect(rectF, getHeight() / 2, getHeight() / 2, mPaint);
    }

    public float getRectWidth() {
        return rectWidth;
    }

    public void setRectWidth(float rectWidth) {
        this.rectWidth = rectWidth;
        invalidate();
    }

}
