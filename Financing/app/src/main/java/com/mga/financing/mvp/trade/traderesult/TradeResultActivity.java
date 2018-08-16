package com.mga.financing.mvp.trade.traderesult;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.constant.NowPrice;
import com.mga.financing.mvp.main.MainActivity;
import com.mga.financing.utils.TimeManagement;

/**
 * Created by mga on 2018/8/9 14:22.
 */

public class TradeResultActivity extends BaseActivity {
    private TextView weightNumTv;
    private TextView ppwTv;
    private TextView payPriceTv;
    private TextView scdTv;
    private TextView doneTv;
    private TextView titleTv;
    private TextView balancePayTv;
    private int tradeType;
    private TextView taiTv;
    private LinearLayout buyDetailLl;
    private LinearLayout sellDetailLl;
    private TextView sellWeightTv;
    private TextView sellFeeTv;
    private TextView sellPerPriceTv;
    private TextView sellPriceTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_traderesult;
    }

    @Override
    protected void initView() {
        titleTv = (TextView) findViewById(R.id.title);//title:成功买入黄金
        weightNumTv = (TextView) findViewById(R.id.weight_num_tv);
        ppwTv = (TextView) findViewById(R.id.price_per_weight_tv);
//        买入细节
        buyDetailLl = (LinearLayout) findViewById(R.id.buy_detail_ll);
        taiTv = (TextView) findViewById(R.id.trade_action_info_tv);
        payPriceTv = (TextView) findViewById(R.id.pay_price_tv);
        balancePayTv = (TextView) findViewById(R.id.balance_pay_tv);
        scdTv = (TextView) findViewById(R.id.start_count_date_tv);
        doneTv = (TextView) findViewById(R.id.done_tv);
        doneTv.setOnClickListener(this);
//        卖出细节
        sellDetailLl = (LinearLayout) findViewById(R.id.sell_detail_ll);
        sellWeightTv = (TextView) findViewById(R.id.sell_weight_tv);
        sellPriceTv = (TextView) findViewById(R.id.sell_price_tv);
        sellPerPriceTv = (TextView) findViewById(R.id.sell_per_price);
        sellFeeTv = (TextView) findViewById(R.id.sell_fee_tv);

    }

    @Override
    protected void initialize() {
        super.initialize();
        if(getBundle()==null) return;
        tradeType=getBundle().getInt(BundleKeyConstant.TRADE_TYPE);
//        初始化布局数据
        if(tradeType==BundleKeyConstant.TRADE_TYPE_BUY){
            logi("tradeType:buy");
//            头
            titleTv.setText(getString(R.string.buy_ok));
//            细节
            buyDetailLl.setVisibility(View.VISIBLE);
            sellDetailLl.setVisibility(View.GONE);
            payPriceTv.setText(getBundle().getString(BundleKeyConstant.BUY_PRICE));
            balancePayTv.setText(getBundle().getString(BundleKeyConstant.BUY_PRICE));
        }else if(tradeType==BundleKeyConstant.TRADE_TYPE_SELL){
            logi("tradeType:sell");
//            头
            titleTv.setText(getString(R.string.sell_ok));
//            细节
            buyDetailLl.setVisibility(View.GONE);
            sellDetailLl.setVisibility(View.VISIBLE);
            sellPriceTv.setText(getBundle().getString(BundleKeyConstant.SELL_PRICE));
            sellWeightTv.setText(getBundle().getString(BundleKeyConstant.WEIGHT)+"克");
            sellPerPriceTv.setText(NowPrice.getPrice(this)+"元/克");

        }else{
            loge("交易结果页面异常：非买入或卖出类型");
        }
        weightNumTv.setText(getBundle().getString(BundleKeyConstant.WEIGHT));
        ppwTv.setText(NowPrice.getPrice(this));
        scdTv.setText(TimeManagement.longToStringDate(System.currentTimeMillis()));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.done_tv:
                toOtherLayout(MainActivity.class, null);
                finish();
                break;
            default:

                break;
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
