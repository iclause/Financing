package com.mga.financing.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.mvp.charge.ChargePresenter;


/**
 * Created by Administrator on 2016/12/22.
 */
public class MPopupDialog extends AppCompatDialog implements View.OnClickListener {
    private final ChargePresenter mPresenter;
    private ListView mListview;
    private Button sendBtn;
    private Context mContext;
    private TextView title;
    private String TAG = MPopupDialog.class.getName();
    private ProgressDialog mProgressDialog;
    public static final int DIALOGTOANSWERFLAG=1;
    private TextView chargeNumTv;
    private EditText inputChargePasswordEt;
    private TextView userBankCardInfoTv;
    private TextView confirmTv;

    public MPopupDialog(Context context, ChargePresenter chargePresenter) {
        super(context, R.style.MPopupDialog);
        this.mContext = context;
        this.mPresenter=chargePresenter;
        setMsgDialog();
    }

    private void setMsgDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.popupdialog, null);
        chargeNumTv = (TextView) mView.findViewById(R.id.charge_num_tv);
        userBankCardInfoTv = (TextView) mView.findViewById(R.id.use_bankcard_info_tv);
        confirmTv = (TextView) mView.findViewById(R.id.confirm_tv);
        confirmTv.setOnClickListener(this);
        inputChargePasswordEt = (EditText) mView.findViewById(R.id.input_charge_password_et);


        this.setCanceledOnTouchOutside(true);                 //点击外部关闭窗口
        Window win = this.getWindow();
        /////////获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int screenheight = dm.heightPixels;
        win.setGravity(Gravity.BOTTOM);                       //从下方弹出
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //宽度填满
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;    //高度自适应
//        lp.height = (int)(screenheight*0.65);
        win.setAttributes(lp);

        super.setContentView(mView);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_tv:
                // TODO: 2018/6/15  提交支付密码
                mPresenter.submitPassword();
                break;

        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }


}
