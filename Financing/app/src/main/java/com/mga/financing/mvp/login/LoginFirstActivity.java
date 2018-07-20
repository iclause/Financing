package com.mga.financing.mvp.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.utils.ECCUtils;

/**
 * Created by mga on 2018/4/10 16:25.
 */

public class LoginFirstActivity extends BaseActivity implements LoginContact.View {
    private LoginPresenter mLoginPresenter;
    private EditText phoneNumberEt;
    private TextView nextTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_firststep;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() {
        phoneNumberEt = (EditText) findViewById(R.id.phonenumber_et);
        nextTv = (TextView) findViewById(R.id.next_tv1);
        phoneNumberEt.setOnClickListener(this);
        nextTv.setOnClickListener(this);
        phoneNumberEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ECCUtils.isPhoneNumber(s.toString())){
                    nextTv.setBackground(getResources().getDrawable(R.drawable.shape_login_btn_ennable));
                    nextTv.setEnabled(true);
                }else{
                    nextTv.setBackground(getResources().getDrawable(R.drawable.shape_login_btn_unennable));
                    nextTv.setEnabled(false);
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
        super.onClick(v);
        switch (v.getId()) {
            case R.id.next_tv1:
                mLoginPresenter.checkIsRegist(phoneNumberEt.getText().toString());
                break;
            default:

                break;

        }
    }



    @Override
    public void showFailReason(int errcode, Intent intent) {

    }





    @Override
    public String getPhoneNumber() {
        return phoneNumberEt.getText().toString();
    }

    @Override
    public String getPassWord() {
        return null;
    }

    @Override
    public void countDown() {

    }

}
