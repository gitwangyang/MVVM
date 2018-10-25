package com.dotawang.mvvm.ui.vm;

import android.content.Context;

import com.dotawang.mvvm.ui.activity.MainActivity;
import com.dotawang.mvvm.base.BaseViewModel;
import com.dotawang.mvvm.binding.command.BindingAction;
import com.dotawang.mvvm.binding.command.BindingCommand;
import com.dotawang.mvvm.dialog.CommonDialog;
import com.dotawang.mvvm.dialog.DialogUtils;

/**
 * Created by Dota.Wang on 2018/10/25.
 */

public class MainViewModel extends BaseViewModel {

    public BindingCommand toastDialogOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            DialogUtils.showDialog(context, "title", "content", "<font color=#999999>取消</font>", "<font color=#f24957>确定</font>", true, new CommonDialog.ICommonDialogListener() {
                    @Override
                    public void onConfirmClick() {

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
