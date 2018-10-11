package com.dotawang.mvvm.dialog;

import android.app.Activity;
import android.content.Context;

import com.dotawang.mvvm.utils.Tool;

/**
 * dialog工具类
 * Created by dotawang on 2017/12/15.
 */

public class DialogUtils {
    /**
     * @param activity activity
     * @param content  内容
     */
    public static void showDialog(Context activity, String content) {
        showDialog(activity, "", content);
    }

    /**
     * @param activity activity
     * @param title    标题
     * @param content  内容
     */
    public static void showDialog(Context activity, String title, String content) {
        showDialog(activity, title, content, true);
    }

    /**
     * @param activity     activity
     * @param title        标题
     * @param content      内容
     * @param isCancelable 是否可被取消
     */
    public static void showDialog(Context activity, String title, String content, boolean isCancelable) {
        showDialog(activity, title, content, isCancelable, null);
    }

    /**
     * @param activity     activity
     * @param title        标题
     * @param content      内容
     * @param isCancelable 是否可被取消
     * @param listener     确认按钮点击监听
     */
    public static void showDialog(Context activity, String title, String content, boolean isCancelable,
                                  CommonDialog.ICommonDialogListener listener) {
        showDialog(activity, title, content, "", isCancelable, listener);
    }

    /**
     * @param activity     activity
     * @param title        标题
     * @param content      内容
     * @param btnContent   按钮文本
     * @param isCancelable 是否可取消
     * @param listener     按钮点击监听
     */
    public static void showDialog(Context activity, String title, String content, String btnContent,
                                  boolean isCancelable,
                                  CommonDialog.ICommonDialogListener listener) {
        showDialog(activity, title, content, btnContent, "", isCancelable, listener);
    }

    /**
     * @param activity     activity
     * @param title        标题
     * @param content      内容
     * @param btnContent   确定按钮文本
     * @param cancelText   取消按钮文本
     * @param isCancelable 点击弹窗外或返回键是否可以取消
     * @param listener     按钮点击事件
     */
    public static void showDialog(Context activity, String title, String content, String btnContent,
                                  String cancelText,
                                  boolean isCancelable,
                                  CommonDialog.ICommonDialogListener listener) {
        //在activity没有调用finish时弹窗出现
        if (activity instanceof Activity) {
            Activity activity1 = (Activity) activity;
            if (!activity1.isFinishing()) {
                CommonDialog dialog = CommonDialog.getInstance(title, content, btnContent, cancelText);
                dialog.setCancelable(isCancelable);
                dialog.setListener(listener);
                dialog.show(activity1.getFragmentManager(), CommonDialog.class.getSimpleName());
            } else {
                Tool.showToast(content);
            }
        } else {
            Tool.showToast(content);
        }
    }

    public static void showDialog(Context activity, String title, String content, String btnContent,
                                  String cancelText,
                                  boolean isCancelable,
                                  CommonDialog.ICommonDialogListener listener, CommonDialog.ICommonDialogDismissListener dialogDismissListener) {
        //在activity没有调用finish时弹窗出现
        if (activity instanceof Activity) {
            Activity activity1 = (Activity) activity;
            if (!activity1.isFinishing()) {
                CommonDialog dialog = CommonDialog.getInstance(title, content, btnContent, cancelText);
                dialog.setCancelable(isCancelable);
                dialog.setListener(listener);
                dialog.setICommonDialogDismissListener(dialogDismissListener);
                dialog.show(activity1.getFragmentManager(), CommonDialog.class.getSimpleName());
            } else {
                Tool.showToast(content);
            }
        } else {
            Tool.showToast(content);
        }
    }

    /**
     * 只有内容和一个按钮的弹窗
     *
     * @param content
     * @param button
     */
    public static void showDialogMessageAndButton(Context activity, String content, String button) {
        //在activity没有调用finish时弹窗出现
        if (activity instanceof Activity) {
            Activity activity1 = (Activity) activity;
            if (!activity1.isFinishing()) {
                CommonDialog dialog = CommonDialog.getInstance("", content, button, "");
                dialog.setCancelable(true);
                dialog.setListener(null);
                dialog.show(activity1.getFragmentManager(), CommonDialog.class.getSimpleName());
            } else {
                Tool.showToast(content);
            }
        } else {
            Tool.showToast(content);
        }
    }
}
