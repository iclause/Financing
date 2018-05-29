package com.mga.financing.mvp.regist;

import android.view.View;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.mvp.login.LoginContact;
import com.mga.financing.mvp.login.LoginPresenter;

/**
 * Created by mga on 2018/4/10 16:25.
 */

public class RegistActivity extends BaseActivity implements LoginContact.View{
    private LoginPresenter mLoginPresenter;
    private TextView registTv;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView() {
       registTv=(TextView)findViewById(R.id.regist_tv);
        registTv.setOnClickListener(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        mLoginPresenter = new LoginPresenter();
        return mLoginPresenter;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.regist_tv:
                mLoginPresenter.regist();
                break;
        }
    }


    @Override
    public String getPhoneNumber() {
        if(getBundle()==null){
            return "";
        }
        return getBundle().getString(BundleKeyConstant.PHONENUMBER);
    }
}
