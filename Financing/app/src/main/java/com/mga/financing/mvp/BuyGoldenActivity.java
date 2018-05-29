package com.mga.financing.mvp;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;

/**
 * Created by mga on 2018/5/29 18:55.
 */

public class BuyGoldenActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_golden_order;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
