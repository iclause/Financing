package com.mga.financing.mvp.trade.buy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.constant.NowPrice;
import com.mga.financing.mvp.charge.ChargeActivity;
import com.mga.financing.mvp.trade.TradeContact;
import com.mga.financing.mvp.trade.TradePresenter;
import com.mga.financing.ui.BuyPopupDialog;

/**
 * Created by mga on 2018/6/12 17:32.
 */

public class BuyActivity extends BaseActivity implements TradeContact.View{

    private TextView goToChargeTv;
    private float bankBalance = (float) 0.00; //余额
    private float order = (float) 256.00; //订单金额
    private RelativeLayout buyRl;
    private RelativeLayout chargeRl;
    private TextView priceTv;
    private TextView weightTv;
    private TextView needChargeTv;
    private TextView nowPriceTv2;
    private TextView wallletBalanceTv;
    private TradePresenter mTradePresenter;
    private TextView buyTv;
    private Bundle buyBundle;
    private BuyPopupDialog mBuyPopDialog;

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
        mTradePresenter.queryBalance();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_order;
    }

    @Override
    protected void initView() {

        chargeRl = (RelativeLayout) findViewById(R.id.charge_rl);
        buyRl = (RelativeLayout) findViewById(R.id.buy_rl);

        wallletBalanceTv = (TextView) findViewById(R.id.wallet_balance_tv2);
        if (getBundle()!=null&&getBundle().getString(BundleKeyConstant.CHARGE_PRICE)!=null) {
            Log.i(TAG, "chargeprice = " + getBundle().getString(BundleKeyConstant.CHARGE_PRICE).toString());
            wallletBalanceTv.setText(getBundle().getString(BundleKeyConstant.CHARGE_PRICE,"0.00元").toString());
        }else{

            wallletBalanceTv.setText("0.00元");
        }
        String chargePrice=getBundle().getString(BundleKeyConstant.CHARGE_PRICE,"0.00元").toString();
        chargePrice=chargePrice.substring(0,chargePrice.indexOf("元"));

        logi("bankBalance="+bankBalance);

        weightTv = (TextView) findViewById(R.id.weight_tv);
        priceTv = (TextView) findViewById(R.id.price_tv);
        nowPriceTv2 = (TextView) findViewById(R.id.now_price_tv2);
        nowPriceTv2.setText(NowPrice.getPrice(this)+"元/克");
        needChargeTv = (TextView) findViewById(R.id.need_charge_num_tv);
        weightTv.setText(getBundle().getString(BundleKeyConstant.WEIGHT,"0克"));
        logi("order all price ="+getBundle().getString(BundleKeyConstant.PRICE,"0.00元"));
        priceTv.setText(getBundle().getString(BundleKeyConstant.PRICE,"0.00元"));
        String orderPrice=getBundle().getString(BundleKeyConstant.PRICE,"0.00元");
//        orderPrice=orderPrice.substring(0,orderPrice.indexOf("元"));
        order=Float.valueOf(orderPrice);
        logi("order="+order);
        judgeIsCharge();
        needChargeTv.setText(getBundle().getString(BundleKeyConstant.PRICE,"0.00")+"元");
        goToChargeTv = (TextView) findViewById(R.id.goto_charge_tv);
        goToChargeTv.setOnClickListener(this);

        buyTv = (TextView) findViewById(R.id.buy_tv);
        buyTv.setOnClickListener(this);
    }

    private void judgeIsCharge() {
        if (bankBalance < order) {
            chargeRl.setVisibility(View.VISIBLE);
            buyRl.setVisibility(View.GONE);
        } else {
            chargeRl.setVisibility(View.GONE);
            buyRl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showBalance(String balance) {
        logi("bankBalance="+bankBalance);
        bankBalance=Float.valueOf(balance);
        wallletBalanceTv.setText(balance);
        judgeIsCharge();
    }

    @Override
    protected BasePresenter createPresenter() {
        mTradePresenter =new TradePresenter(this);
        return mTradePresenter;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.goto_charge_tv:
                toOtherLayout(ChargeActivity.class, getBundle());
                break;
            case R.id.buy_tv:
                // TODO: 2018/6/15 买入
                logi("buy_tv onclick");

                showPopDialog();

//                mTradePresenter.buy(buyBundle);
                break;
        }
    }
    @Override
    public void showPopDialog() {
        if(buyBundle==null) {
            buyBundle = new Bundle();
        }
        buyBundle.putString(BundleKeyConstant.WEIGHT,weightTv.getText().toString());
        // TODO: 2018/7/20 h5没打通，先采用productid=“1”的产品
        buyBundle.putString(BundleKeyConstant.PRODUCTID,"1");
        buyBundle.putString(BundleKeyConstant.BUY_PRICE,priceTv.getText().toString());
        mBuyPopDialog=new BuyPopupDialog(this,mTradePresenter,buyBundle);
        mBuyPopDialog.show();
    }

    @Override
    public void dismissPopDialog() {
        if(mBuyPopDialog!=null&&mBuyPopDialog.isShowing()){
            mBuyPopDialog.dismiss();
            mBuyPopDialog=null;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        // TODO: 2018/6/13  获取银行卡余额
        mTradePresenter.queryBalance();
        judgeIsCharge();
    }
}
