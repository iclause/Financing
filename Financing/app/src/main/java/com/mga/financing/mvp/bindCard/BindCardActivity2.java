package com.mga.financing.mvp.bindCard;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ebupt.jlog.JLog;
import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.mvp.login.LoginContact;
import com.mga.financing.utils.RegexUtils;

/**
 * Created by mga on 2018/5/30 15:09.
 */

public class BindCardActivity2 extends BaseActivity implements LoginContact.View {
    private TextView cardNameTv;
    private TextView sTv;
    private TextView tv2;
    private CheckBox cb;
    private EditText isEt;
    private TextView vfcTv;
    private EditText ipEt;
    private TextView cardTypeTv;
    private BindPresenter mBindCardPresenter;
    public static Activity instance;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_card2;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText(getResources().getString(R.string.bind_card));
    }

    @Override
    protected void initView() {
        cardNameTv = (TextView) findViewById(R.id.card_name_tv);
        cardTypeTv = (TextView) findViewById(R.id.card_type_tv);
        ipEt = (EditText) findViewById(R.id.input_phone_et);
        vfcTv = (TextView) findViewById(R.id.vfc_tv);
        isEt = (EditText) findViewById(R.id.input_sms_et);
        cb = (CheckBox) findViewById(R.id.checkbox);
        tv2 = (TextView) findViewById(R.id.text2); //一键支付协议
        sTv = (TextView) findViewById(R.id.submit_tv);

        vfcTv.setOnClickListener(this);
        tv2.setOnClickListener(this);
        sTv.setOnClickListener(this);

        isEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (RegexUtils.isVfc(s.toString())) {
                    sTv.setEnabled(true);
                    sTv.setBackgroundResource(R.color.orange_btn);
                } else {
                    sTv.setEnabled(false);
                    sTv.setBackgroundResource(R.color.grey_btn);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    protected BasePresenter createPresenter() {
        mBindCardPresenter = new BindPresenter(this, getBundle());
        return mBindCardPresenter;
    }

    @Override
    protected void initialize() {
        super.initialize();
        instance=this;


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.vfc_tv:
                if (!RegexUtils.isPhoneNumber(ipEt.getText().toString())) {
                    showToast("请输入正确的11位手机号码");
                    return;
                }
                // TODO: 2018/6/8 1.发送验证码;
                mBindCardPresenter.getVfc(cardNameTv.getText().toString());


                break;
            case R.id.tv2:
                // TODO: 2018/6/8 弹出协议界面

                break;
            case R.id.submit_tv:
                // TODO: 2018/6/8 提交
                if (!RegexUtils.isPhoneNumber(ipEt.getText().toString())) {
                    showToast("请输入正确的11位手机号码");
                    return;
                }
                if (!RegexUtils.isVfc(isEt.getText().toString())) {
                    showToast("请输入正确的6位验证码");
                    return;
                }
                if (!cb.isChecked()) {
                    showToast("请阅读并同意《一键支付协议》");
                    return;
                }

                // TODO: 2018/6/8 1.发送验证码;
                mBindCardPresenter.submitBindCard(getPhoneNumber(), isEt.getText().toString());


                break;
        }
    }

    @Override
    public String getPhoneNumber() {
        return cardNameTv.getText().toString();
    }

    @Override
    public String getPassWord() {
        return null;
    }

    private int timeoutlen = 60;

    private int UPDATE_TIME = 100;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE_TIME) {
                timeoutlen--;
                vfcTv.setText(timeoutlen + "s");

                if (timeoutlen > 0) {
                    vfcTv.setEnabled(false);
                    handler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
                } else {
                    vfcTv.setEnabled(true);
                    vfcTv.setText(R.string.get_vfc);

                }
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void countDown() {
        // TODO: 2018/6/8 开始倒计时
        timeoutlen = 60;
        new Thread() {
            @Override
            public void run() {
                super.run();
                handler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
                JLog.i(TAG, "同步后数据");
            }
        }.start();
    }
}
