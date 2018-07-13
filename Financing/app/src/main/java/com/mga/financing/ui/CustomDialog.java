package com.mga.financing.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mga.financing.R;

/**
 * 加载提醒对话框
 */
public class CustomDialog extends ProgressDialog {
    private TextView contentTv;
    private String mContent;
    private LinearLayout rootView;

    public CustomDialog(Context context) {
        super(context,R.style.CustomDialog);
        this.mContent=context.getResources().getString(R.string.loading);
    }

    public CustomDialog(Context context, String content) {
        super(context,R.style.CustomDialog);
            this.mContent = content;
    }

    public CustomDialog(Context context, int theme,String content) {
        super(context, R.style.CustomDialog);
        this.mContent=content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.custom_progressdialog);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
         rootView = (LinearLayout) findViewById(R.id.loading_ll);
        contentTv=(TextView)findViewById(R.id.tv_load_dialog);
        contentTv.setText(mContent);

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}

