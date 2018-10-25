package com.dotawang.mvvm.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dotawang.mvvm.BR;
import com.dotawang.mvvm.R;
import com.dotawang.mvvm.base.BaseFragment;
import com.dotawang.mvvm.base.BaseViewModel;

/**
 * Created by Dota.Wang on 2018/10/26.
 */

public class MineFragment extends BaseFragment {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_mine;
    }

    @Override
    public int initVariableId() {
        return BR.viewmodel;
    }

    @Override
    public BaseViewModel initViewModel() {
        return new BaseViewModel();
    }
}
