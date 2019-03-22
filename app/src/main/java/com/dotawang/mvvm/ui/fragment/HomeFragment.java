package com.dotawang.mvvm.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dotawang.mvvm.BR;
import com.dotawang.mvvm.R;
import com.dotawang.mvvm.base.BaseFragment;
import com.dotawang.mvvm.databinding.FragmentHomeBinding;
import com.dotawang.mvvm.ui.vm.MainViewModel;

/**
 * Created by Dota.Wang on 2018/10/25.
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding,MainViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewmodel;
    }

    @Override
    public MainViewModel initViewModel() {
        return new MainViewModel(getActivity());
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }
}
