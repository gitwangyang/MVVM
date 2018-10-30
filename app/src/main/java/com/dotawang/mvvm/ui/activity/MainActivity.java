package com.dotawang.mvvm.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;

import com.dotawang.mvvm.BR;
import com.dotawang.mvvm.R;
import com.dotawang.mvvm.base.BaseActivity;
import com.dotawang.mvvm.base.BaseViewModel;
import com.dotawang.mvvm.databinding.ActivityMainBinding;
import com.dotawang.mvvm.ui.fragment.HomeFragment;
import com.dotawang.mvvm.ui.fragment.MessageFragment;
import com.dotawang.mvvm.ui.fragment.MineFragment;
import com.dotawang.mvvm.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

/**
 *
 */
public class MainActivity extends BaseActivity<ActivityMainBinding,BaseViewModel> {

    private long mExitTime = 0;
    private List<Fragment> mFragments;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewmodel;
    }

    @Override
    public BaseViewModel initViewModel() {
        return new BaseViewModel(this);
    }


    @Override
    public void initData() {
        super.initData();
        //初始化Fragment
        initFragment();
        //初始化底部Button
        initBottomTab();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new MineFragment());
        //默认选中第一个
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, mFragments.get(0));
        transaction.commitAllowingStateLoss();
    }

    private void initBottomTab() {
        final NavigationController navigationController = binding.pagerBottomTab.material()
                .addItem(R.drawable.function, "功能",getResources().getColor(R.color.bg_bottom_bar))
                .addItem(R.drawable.message_select, "消息",getResources().getColor(R.color.bg_bottom_bar))
                .addItem(R.drawable.mine_select, "我的",getResources().getColor(R.color.bg_bottom_bar))
                .setDefaultColor(ContextCompat.getColor(this, R.color.textColorVice))
                .build();
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, mFragments.get(index));
                transaction.commitAllowingStateLoss();
            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点击二次退出应用
     */
    public void exit(){
        if ((System.currentTimeMillis() - mExitTime)>2000){
            ToastUtils.showShortSafe(getResources().getString(R.string.fish_app));
            mExitTime = System.currentTimeMillis();
        }else {
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
