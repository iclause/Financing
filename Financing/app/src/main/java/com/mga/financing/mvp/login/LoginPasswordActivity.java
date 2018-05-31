package com.mga.financing.mvp.login;

/**
 * Created by mga on 2018/4/17 14:53.
 */

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
public class LoginPasswordActivity extends BaseActivity implements LoginContact.View {
    private LoginPresenter mLoginPresenter;
    private EditText passwordEt;
    private TextView loginTv;
    private TextView toVfcLoginTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_password;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() {
        passwordEt = (EditText) findViewById(R.id.password_et);
        loginTv = (TextView) findViewById(R.id.login_tv);
        toVfcLoginTv = (TextView) findViewById(R.id.to_vfc_login_tv);
        loginTv.setOnClickListener(this);
        toVfcLoginTv.setOnClickListener(this);
        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ECCUtils.isPassword(s.toString())) {
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
            case R.id.login_tv:
                mLoginPresenter.login(getBundle().getString(BundleKeyConstant.PHONENUMBER),passwordEt.getText().toString());
                break;
            case R.id.to_vfc_login_tv:
                toOtherLayout(LoginVfcActivity.class, getBundle());
                break;
        }
    }


    @Override
    public String getPhoneNumber() {
        if (getBundle() == null) {
            return "";
        }
        return getBundle().getString(BundleKeyConstant.PHONENUMBER);
    }
}
