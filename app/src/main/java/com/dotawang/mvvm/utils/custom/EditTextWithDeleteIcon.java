package com.dotawang.mvvm.utils.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.dotawang.mvvm.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created on 2019/3/15
 * Title:带有删除图标的输入框
 * @author Android-汪洋
 * @Description:
 */
public class EditTextWithDeleteIcon extends android.support.v7.widget.AppCompatEditText implements View.OnTouchListener {
    private int mWidth, mHeight;
    private boolean isAliganCenter = true;
    private boolean mShowDeleteIcon;
    private Drawable mDrawableDelete;
    private int mDeleteIconWidth;
    private int mDeleteIconHeight;

    public EditTextWithDeleteIcon(Context context) {
        this(context, null);
    }

    public EditTextWithDeleteIcon(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTextWithDeleteIcon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addReplaceEndOfLineFilter();
        mDrawableDelete = getResources().getDrawable(R.drawable.selector_register_icon_close);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithDeleteIcon);
        isAliganCenter = typedArray.getBoolean(R.styleable.EditTextWithDeleteIcon_isAliganCenter, true);
        mShowDeleteIcon = typedArray.getBoolean(R.styleable.EditTextWithDeleteIcon_showDeleteIcon, false);
        mDeleteIconWidth = typedArray.getDimensionPixelSize(R.styleable.EditTextWithDeleteIcon_deleteIconWidth, 0);
        mDeleteIconHeight = typedArray.getDimensionPixelSize(R.styleable.EditTextWithDeleteIcon_deleteIconHeight, 0);
        typedArray.recycle();
        setOnTouchListener(this);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (text.length() > 0 && mShowDeleteIcon) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setCompoundDrawablesRelative(getCompoundDrawablesRelative()[0],
                        getCompoundDrawablesRelative()[1],
                        mDrawableDelete,
                        getCompoundDrawablesRelative()[3]);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setCompoundDrawablesRelative(getCompoundDrawablesRelative()[0],
                        getCompoundDrawablesRelative()[1],
                        mShowDeleteIcon ? null : getCompoundDrawablesRelative()[2],
                        getCompoundDrawablesRelative()[3]);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Drawable[] drawables = new Drawable[0];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            drawables = getCompoundDrawablesRelative();
        }
        Drawable drawableLeft = drawables[0];
        Drawable drawableTop = drawables[1];
        Drawable drawableRight = drawables[2];
        Drawable drawableBottom = drawables[3];
        if (mShowDeleteIcon) {
            setDrawable(mDrawableDelete, 2, mDeleteIconWidth, mDeleteIconHeight);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            this.setCompoundDrawablesRelative(drawableLeft, drawableTop, drawableRight, drawableBottom);
        }

    }

    private void setDrawable(Drawable drawable, int tag, int drawableWidth, int drawableHeight) {
        //获取图片实际长宽
        int width = drawableWidth == 0 ? drawable.getIntrinsicWidth() : drawableWidth;
        int height = drawableHeight == 0 ? drawable.getIntrinsicHeight() : drawableHeight;
        int left = 0, top = 0, right = 0, bottom = 0;
        switch (tag) {
            case 0:
            case 2:
                left = 0;
                top = isAliganCenter ? 0 : -getLineCount() * getLineHeight() / 2 + getLineHeight() / 2;
                right = width;
                bottom = top + height;
                break;
            case 1:
                left = isAliganCenter ? 0 : -mWidth / 2 + width / 2;
                top = 0;
                right = left + width;
                bottom = top + height;
                break;
            default:
                break;
        }
        drawable.setBounds(left, top, right, bottom);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            if (mShowDeleteIcon) {
                if (event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - mDrawableDelete.getBounds().width())) {
                    getEditableText().clear();
                }
            }
            showKeyBoard(event);
        }
        return false;
    }

    public void showKeyBoard(MotionEvent event) {

    }

    /**
     * 添加过滤器 过滤掉复制文字中的换行符，防止崩溃
     * 参考
     * https://www.jianshu.com/p/9641bd9a2377
     */
    private void addReplaceEndOfLineFilter() {
        ArrayList<InputFilter> list = new ArrayList<>();
        InputFilter[] filters = getFilters();
        if (null != filters) {
            list.addAll(Arrays.asList(filters));
        }
        //替换掉所有的转义符
        InputFilter replaceEndOfLineFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source == null) {
                    return null;
                }
                source = source.toString().replaceAll("\n", " ");
                return source;
            }
        };
        list.add(replaceEndOfLineFilter);
        setFilters(list.toArray(new InputFilter[0]));
    }
}
