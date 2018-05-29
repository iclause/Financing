package com.mga.financing.mvp.main.tab3;

import android.os.Bundle;
import android.view.View;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseFragment;

/**
 * Created by mga on 2018/4/25 16:07.
 */

public class Tab3Fragment extends BaseFragment{
    @Override
    public void toOtherLayout(Class aClass, Bundle bundle) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragmet3;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }
}
