package com.dotawang.mvvm.ui.vm;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dotawang.mvvm.R;
import com.dotawang.mvvm.base.BaseViewModel;

/**
 * Created by Dota.Wang on 2018/10/23.
 */

public class SplashViewModel extends BaseViewModel {

    private int localimg;
    public SplashViewModel(Context context) {
        super(context);
    }


    //localimg  为网络返回的url
    @BindingAdapter("bind:localimg")
    public static void getInternetImage(ImageView imageView,int localimg){
        Glide.with(imageView.getContext()).load(R.drawable.benbenla).into(imageView);
    }

    public int getLocalimg() {
        return localimg;
    }

    public void setLocalimg(int localimg) {
        this.localimg = localimg;
    }
}
