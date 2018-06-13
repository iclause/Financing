package com.mga.financing.mvp.charge;

import android.view.View;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;

/**
 * Created by mga on 2018/6/13 18:58.
 */

public class ChargeActivity2 extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_charge2;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText(getResources().getString(R.string.charge));
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void initView() {
       


    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.submit_tv:

                break;
        }
    }
}
