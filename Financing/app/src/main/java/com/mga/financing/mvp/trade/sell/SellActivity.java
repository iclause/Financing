package com.mga.financing.mvp.trade.sell;

import android.view.View;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;

/**
 * Created by mga on 2018/8/9 15:38.
 */

public class SellActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_sell_golden;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText("卖出黄金");

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
