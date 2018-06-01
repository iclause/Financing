package com.mga.financing.mvp;

import android.view.View;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;

/**
 * Created by mga on 2018/5/30 15:09.
 */

public class BindCardActivity2 extends BaseActivity {
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

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initialize() {
        super.initialize();

    }




}
