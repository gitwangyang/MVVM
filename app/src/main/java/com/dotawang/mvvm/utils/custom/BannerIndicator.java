package com.dotawang.mvvm.utils.custom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.dotawang.mvvm.R;
import com.dotawang.mvvm.utils.ScreenUtils;
import com.youth.banner.Banner;

/**
 * Created on 2019/3/15
 * Title: 首页 banner 可变小圆点
 * @author Android-汪洋
 * @Description:
 */
public class BannerIndicator extends LinearLayout{
    private int dashGap;
    private int position;
    private ViewPager viewPager;
    private int amount;

    public BannerIndicator(Context context) {
        this(context, null);
    }

    public BannerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BannerIndicator);
        dashGap = (int) array.getDimension(R.styleable.BannerIndicator_gap, ScreenUtils.dpToPx(3));
        array.recycle();
    }

    public void setUpWidthViewPager(ViewPager viewPager) {
        setUpWidthViewPager(viewPager, 0);
    }

    public void setUpWidthViewPager(ViewPager viewPager, int count) {
        removeAllViews();
        if (viewPager == null || viewPager.getAdapter().getCount() < 2) {
            return;
        }
        position = 0;
        this.viewPager = viewPager;
        amount = count > 0 ? count : viewPager.getAdapter().getCount();
        for (int i = 0; i < amount; i++) {
            BannerItemView imageView = new BannerItemView(getContext());
            LayoutParams layoutParams = new LayoutParams(ScreenUtils.dpToPx(12),
                    ScreenUtils.dpToPx(4));
            if (i > 0) {
                layoutParams.setMargins(dashGap, 0, 0, 0);
                imageView.setAlpha(0.4f);
            } else {
                layoutParams.setMargins(0, 0, 0, 0);
                imageView.setAlpha(1);
            }
            imageView.setLayoutParams(layoutParams);
            addView(imageView);
            setLarge(0);
        }
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                position = amount > 0 ? position % amount : position;
                if (BannerIndicator.this.position != position) {
                    resetSize(BannerIndicator.this.position, position);
                    BannerIndicator.this.position = position;
                }
            }
        });
    }

    public void setUpWithViewPager(Banner banner, int count) {
        removeAllViews();
        position = 0;
        this.viewPager = banner.getViewPager();
        if (viewPager == null || viewPager.getAdapter().getCount() < 2 || count < 2) {
            return;
        }
        amount = count;
        for (int i = 0; i < amount; i++) {
            BannerItemView imageView = new BannerItemView(getContext());
            LayoutParams layoutParams = new LayoutParams(ScreenUtils.dpToPx(12),
                    ScreenUtils.dpToPx(4));
            if (i > 0) {
                layoutParams.setMargins(dashGap, 0, 0, 0);
                imageView.setAlpha(0.4f);
            } else {
                layoutParams.setMargins(0, 0, 0, 0);
                imageView.setAlpha(1);
            }
            imageView.setLayoutParams(layoutParams);
            addView(imageView);
        }
        setLarge(0);
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (BannerIndicator.this.position != position) {
                    resetSize(BannerIndicator.this.position, position);
                    BannerIndicator.this.position = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //重置指示样式
    private void resetSize(int last, int current) {
        setLarge(current);
        setSmall(last);
    }

    //小圆点变成圆角矩形
    private void setLarge(int position) {
        if (getChildAt(position) instanceof BannerItemView) {
            AnimatorSet set = new AnimatorSet();
            ValueAnimator animator = getEnlarge((BannerItemView) getChildAt(position));
            ValueAnimator alpha = ObjectAnimator.ofFloat(getChildAt(position), "alpha", 0.4f, 1f);
            set.play(animator).with(alpha);
            set.setDuration(618);
            set.start();
        }
    }

    public void setCurrent(int position) {
        if (viewPager == null) {
            return;
        }
        resetSize(this.position, position);
        this.position = position;
    }

    //放大动画
    private ValueAnimator getEnlarge(BannerItemView roundRectView) {
        return ObjectAnimator.ofFloat(roundRectView,
                "rectWidth",
                0, ScreenUtils.dpToPx(4));
    }

    //缩小动画
    private ValueAnimator getShrink(BannerItemView roundRectView) {
        return ObjectAnimator.ofFloat(roundRectView,
                "rectWidth",
                ScreenUtils.dpToPx(4), 0);
    }

    //圆角矩形变成小圆点
    public void setSmall(int small) {
        if (getChildAt(small) instanceof BannerItemView) {
            AnimatorSet set = new AnimatorSet();
            ValueAnimator alpha = ObjectAnimator.ofFloat(getChildAt(position), "alpha", 1, 0.4f);
            ValueAnimator animator = getShrink((BannerItemView) getChildAt(small));
            set.play(animator).with(alpha);
            set.setDuration(618);
            set.start();
        }
    }
}
