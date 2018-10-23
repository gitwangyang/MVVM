package com.dotawang.mvvm.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;

import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.dotawang.mvvm.BR;
import com.dotawang.mvvm.MainActivity;
import com.dotawang.mvvm.R;
import com.dotawang.mvvm.base.BaseActivity;
import com.dotawang.mvvm.databinding.ActivitySplashBinding;
import com.dotawang.mvvm.ui.vm.SplashViewModel;

/**
 * 欢迎页
 * Created by Dota.Wang on 2018/10/23.
 */

public class SplashActivity extends BaseActivity<ActivitySplashBinding,SplashViewModel> {

    @Override
    public void initBeforeCreate() {
        super.initBeforeCreate();
        //给欢迎页的背景置空，释放内存
        getWindow().setBackgroundDrawable(null);
        initStatus();
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public int initVariableId() {
        return BR.viewmodel;
    }

    @Override
    public SplashViewModel initViewModel() {
        return new SplashViewModel(this);
    }

    @Override
    public void initData() {
        super.initData();
        binding.ivMark.post(new Runnable() {
            @Override
            public void run() {
//                Glide.with(SplashActivity.this).load(R.drawable.benbenla).into(binding.ivSplash);
                startAnimat();

            }
        });

    }

    ViewPropertyTransition.Animator animator = new ViewPropertyTransition.Animator() {
        @Override
        public void animate(View view) {
            view.setAlpha(0f);
            ObjectAnimator objAnimator=ObjectAnimator.ofFloat(view,"alpha",0f,1f);
            objAnimator.setDuration(2000);
            objAnimator.start();
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    private void initStatus(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decoderView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decoderView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //or ?
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    private void startAnimat(){

        int imgHeight=binding.ivMark.getHeight()/5;
        int height=getWindowManager().getDefaultDisplay().getHeight();
        int curY=height/2 + imgHeight/2;
        int dy=(height-imgHeight)/2;
        AnimatorSet set=new AnimatorSet();
        ObjectAnimator animatorTranslate=ObjectAnimator.ofFloat(binding.ivMark,"translationY",0,dy);
        ObjectAnimator animatorScaleX=ObjectAnimator.ofFloat(binding.ivMark,"ScaleX",1f,0.2f);
        ObjectAnimator animatorScaleY=ObjectAnimator.ofFloat(binding.ivMark,"ScaleY",1f,0.2f);
        ObjectAnimator animatorAlpha=ObjectAnimator.ofFloat(binding.ivMark,"alpha",1f,0.5f);
        set.play(animatorTranslate)
                .with(animatorScaleX).with(animatorScaleY).with(animatorAlpha);
        set.setDuration(1200);
        set.setInterpolator(new AccelerateInterpolator());
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                binding.ivMark.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        SplashActivity.this.finish();
                    }
                },3000);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
