package com.mga.financing.mvp.order;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;

/**
 * Created by mga on 2018/6/12 17:32.
 */

public class BuyOrderActivity extends BaseActivity {

    private TextView goToChargeTv;
    private float  bankBalance= (float) 0.00;
    private float  order= (float) 256.00;
    private RelativeLayout buyRl;
    private RelativeLayout chargeRl;

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText(getResources().getString(R.string.buy_order));
    }

    @Override
    protected void initialize() {
        super.initialize();
        // TODO: 2018/6/13 获取银行卡余额
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_order;
    }

    @Override
    protected void initView() {

        chargeRl=(RelativeLayout)findViewById(R.id.charge_rl);
        buyRl=(RelativeLayout)findViewById(R.id.buy_rl);
        judgeIsCharge();
        goToChargeTv=(TextView)findViewById(R.id.goto_charge_tv);
        goToChargeTv.setOnClickListener(this);

    }

    private void judgeIsCharge() {
        if(bankBalance<order){
            chargeRl.setVisibility(View.VISIBLE);
            buyRl.setVisibility(View.GONE);
        }else{
            chargeRl.setVisibility(View.GONE);
            buyRl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.goto_charge_tv:

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: 2018/6/13  获取银行卡余额

        judgeIsCharge();
    }
}
