package com.mga.financing.mvp;

import android.view.View;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;

/**
 * Created by mga on 2018/5/30 14:45.
 */

public class BindCardActivity1 extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_card1;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText(R.string.bind_card);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
