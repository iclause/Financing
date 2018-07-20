package com.mga.financing.mvp.regist;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.mvp.login.LoginContact;
import com.mga.financing.mvp.login.LoginPresenter;
import com.mga.financing.utils.ECCUtils;

/**
 * Created by mga on 2018/4/10 16:25.
 */

public class RegistActivity extends BaseActivity implements LoginContact.View {
    private LoginPresenter mLoginPresenter;
    private TextView registTv;
    private EditText vfcEt;
    private EditText registPasswordEt;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView() {
        registTv = (TextView) findViewById(R.id.regist_tv);
        registTv.setOnClickListener(this);
        vfcEt = (EditText) findViewById(R.id.regist_vfc_et);
        registPasswordEt = (EditText) findViewById(R.id.regist_password_et);
    }

    @Override
    protected BasePresenter createPresenter() {
        mLoginPresenter = new LoginPresenter(this);
        return mLoginPresenter;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.regist_tv:
                if (!ECCUtils.isVertificationCode(vfcEt.getText().toString())) {
                    showToast("请输入有效验证码");
                    return;
                }
                if (!ECCUtils.isPassword(registPasswordEt.getText().toString())) {
                    showToast("请输入有效密码");
                    return;
                }
                mLoginPresenter.regist(getBundle().getString(BundleKeyConstant.PHONENUMBER),
                        registPasswordEt.getText().toString(), vfcEt.getText().toString());
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

    @Override
    public String getPassWord() {
        return registPasswordEt.getText().toString();
    }

    @Override
    public void countDown() {

    }
}
