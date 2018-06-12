package com.mga.financing.mvp.order;

import android.view.View;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;

/**
 * Created by mga on 2018/6/12 17:32.
 */

public class BuyOrderActivity extends BaseActivity {

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText(getResources().getString(R.string.buy_order));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_order;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
