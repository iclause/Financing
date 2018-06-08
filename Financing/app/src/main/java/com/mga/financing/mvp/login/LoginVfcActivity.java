package com.mga.financing.mvp.login;

/**
 * Created by mga on 2018/4/17 14:53.
 */

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.utils.ECCUtils;

/**
 * 验证码登录界面
 */
public class LoginVfcActivity extends BaseActivity implements LoginContact.View {
    private LoginPresenter mLoginPresenter;
    private TextView loginTv;
    private TextView phonenumberTv;
    private EditText vfcEt;
    private TextView vfcLoginTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_vfc;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initialize() {
        super.initialize();
        mLoginPresenter.getVfc(getBundle().getString(BundleKeyConstant.PHONENUMBER));
    }

    @Override
    protected void initView() {
        vfcEt = (EditText) findViewById(R.id.vfc_et);
        loginTv = (TextView) findViewById(R.id.vfc_login_tv);
        phonenumberTv = (TextView) findViewById(R.id.phonenumber_tv);
        loginTv.setOnClickListener(this);
        phonenumberTv.setText(getPhoneNumber());
        vfcEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ECCUtils.isVertificationCode(s.toString())) {
                    loginTv.setBackground(getResources().getDrawable(R.drawable.shape_login_btn_ennable));
                    loginTv.setEnabled(true);
                } else {
                    loginTv.setBackground(getResources().getDrawable(R.drawable.shape_login_btn_unennable));
                    loginTv.setEnabled(false);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        mLoginPresenter = new LoginPresenter(this);
        return mLoginPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.vfc_login_tv:
                mLoginPresenter.vfc(getBundle().getString(BundleKeyConstant.PHONENUMBER));
                break;
        }
    }

    @Override
    public void showFailReason(int errcode, Intent intent) {

    }


    @Override
    public String getPhoneNumber() {
        if (getBundle() == null) {
            return "";
        }
        return getBundle().getString(BundleKeyConstant.PHONENUMBER);
    }

    @Override
    public void countDown() {
        // TODO: 2018/6/8 倒计时

    }
}
