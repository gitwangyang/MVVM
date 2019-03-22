package com.dotawang.mvvm.ui.vm;

import android.content.Context;

import com.dotawang.mvvm.base.BaseViewModel;
import com.dotawang.mvvm.binding.command.BindingAction;
import com.dotawang.mvvm.binding.command.BindingCommand;
import com.dotawang.mvvm.dialog.CommonDialog;
import com.dotawang.mvvm.dialog.DialogUtils;
import com.dotawang.mvvm.utils.ToastUtils;

/**
 * Created by Dota.Wang on 2018/10/25.
 */

public class MainViewModel extends BaseViewModel {
    /**
     * 轮播图点击监听
     */
    public BindingCommand bannerOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("轮播图的show time");
//            toastDialogOnClickCommand.execute();
        }
    });

    /**
     * 弹出框监听
     */
    public BindingCommand toastDialogOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            DialogUtils.showDialog(context, "title", "content", "<font color=#999999>确定</font>", "<font color=#f24957>取消</font>", true, new CommonDialog.ICommonDialogListener() {
                    @Override
                    public void onConfirmClick() {
                        ToastUtils.showShort("轮播图的show time");
                    }

                    @Override
                    public void onCancelClick() {

                    }
                });
        }
    });

    public MainViewModel(Context context) {
        super(context);
    }
}
