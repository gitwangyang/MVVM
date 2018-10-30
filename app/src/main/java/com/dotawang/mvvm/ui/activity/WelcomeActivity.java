package com.dotawang.mvvm.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

import com.dotawang.mvvm.BR;
import com.dotawang.mvvm.R;
import com.dotawang.mvvm.base.BaseActivity;
import com.dotawang.mvvm.databinding.ActivityWelcomeBinding;
import com.dotawang.mvvm.ui.vm.WelcomeViewModel;
import com.dotawang.mvvm.utils.AppUtils;

/**
 * 欢迎页
 * Created by Dota.Wang on 2018/10/24.
 */
public class WelcomeActivity extends BaseActivity<ActivityWelcomeBinding,WelcomeViewModel> {

    private AsyncTask asyncTask;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        AppUtils.initStatus(this);
        return R.layout.activity_welcome;
    }

    @Override
    public int initVariableId() {
        return BR.viewmodel;
    }

    @Override
    public WelcomeViewModel initViewModel() {
        return new WelcomeViewModel(this);
    }

    @Override
    public void initData() {
        super.initData();

        initTaskSkip();

    }

    /**
     * 跳转数字的倒计时
     */
    private void initTaskSkip() {
        asyncTask = new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {
                for (int i = 2; i > 0; i--) {
                    publishProgress(i);
                    SystemClock.sleep(1000);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Object[] values) {
                binding.tvTimeCount.setText(values[0] + "s");
            }

            @Override
            protected void onPostExecute(Object o) {
                if (!viewModel.isClickSkip()){
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
        asyncTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        asyncTask.cancel(true);
    }
}
