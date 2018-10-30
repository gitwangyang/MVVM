package com.dotawang.mvvm.dialog;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dotawang.mvvm.R;
import com.dotawang.mvvm.utils.ScreenUtils;


/**
 * 通用弹窗
 * Created by dotawang on 2017/12/11.
 */

public class CommonDialog extends DialogFragment {
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_BUTTON_CONFIRM = "BUTTON_CONFIRM";
    private static final String KEY_BUTTON_CANCEL = "BUTTON_CANCEL";
    private TextView tvConfirm;
    private TextView tvTitle;
    private TextView tvContent;
    private TextView tvCancel;
    private ICommonDialogListener listener;
    private ICommonDialogDismissListener mICommonDialogDismissListener;

    public CommonDialog() {
    }

    public static CommonDialog getInstance(String title, String content) {
        CommonDialog dialog = new CommonDialog();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putString(KEY_CONTENT, content);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static CommonDialog getInstance(String title, String content, String btnText, String cancelText) {
        CommonDialog dialog = new CommonDialog();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putString(KEY_CONTENT, content);
        bundle.putString(KEY_BUTTON_CONFIRM, btnText);
        bundle.putString(KEY_BUTTON_CANCEL, cancelText);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.commondialog_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle_common_dialog);
        tvContent = (TextView) view.findViewById(R.id.tvContent_common_dialog);
        tvConfirm = (TextView) view.findViewById(R.id.tvConfirm_common_dialog);
        tvCancel = (TextView) view.findViewById(R.id.tvCancel_common_dialog);
        if (!TextUtils.isEmpty(getArguments().getString(KEY_TITLE))) {
            tvTitle.setText(Html.fromHtml(getArguments().getString(KEY_TITLE)));
        } else {
            tvTitle.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tvContent.getLayoutParams();
            layoutParams.topMargin = ScreenUtils.dpToPx(38);
            tvContent.setLayoutParams(layoutParams);
        }
        if (!TextUtils.isEmpty(getArguments().getString(KEY_CONTENT))) {
            tvContent.setText(Html.fromHtml(getArguments().getString(KEY_CONTENT)));
        }
        if (!TextUtils.isEmpty(getArguments().getString(KEY_BUTTON_CONFIRM))) {
            tvConfirm.setText(Html.fromHtml(getArguments().getString(KEY_BUTTON_CONFIRM)));
        }
        if (!TextUtils.isEmpty(getArguments().getString(KEY_BUTTON_CANCEL))) {
            tvCancel.setVisibility(View.VISIBLE);
            tvCancel.setText(Html.fromHtml(getArguments().getString(KEY_BUTTON_CANCEL)));
        } else {
            tvCancel.setVisibility(View.GONE);
        }
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.onConfirmClick();
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.onCancelClick();
                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
    }

    public void setListener(ICommonDialogListener listener) {
        this.listener = listener;
    }

    public void setICommonDialogDismissListener(ICommonDialogDismissListener ICommonDialogDismissListener) {
        mICommonDialogDismissListener = ICommonDialogDismissListener;
    }

    public interface ICommonDialogListener {
        void onConfirmClick();

        void onCancelClick();
    }

    public interface ICommonDialogDismissListener  {
        void onDismiss();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (IllegalStateException ignore) {
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mICommonDialogDismissListener != null) {
            mICommonDialogDismissListener.onDismiss();
        }
    }
}
