package com.mga.financing.mvp.trade.sell;

import android.view.View;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.constant.BundleKeyConstant;

/**
 * 黄金资产界面
 * Created by mga on 2018/8/10 18:11.
 */

public class GoldenAssetsActivity extends BaseActivity {
    private TextView sellTv;
    private TextView gawTv;
    private TextView goldPriceTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_golden_assets;
    }

    @Override
    protected void initView() {
        gawTv=(TextView)findViewById(R.id.golden_assets_weight_tv);
        goldPriceTv=(TextView)findViewById(R.id.gold_price_tv);
        gawTv.setText(getBundle().getString(BundleKeyConstant.USER_WEIGHT));
        goldPriceTv.setText(getBundle().getString(BundleKeyConstant.USER_PRICE));
        sellTv=(TextView)findViewById(R.id.sell_tv);
        sellTv.setOnClickListener(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText("黄金资产");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.sell_tv:
            toOtherLayout(SellActivity.class,getBundle());
                break;

        }
    }
}
