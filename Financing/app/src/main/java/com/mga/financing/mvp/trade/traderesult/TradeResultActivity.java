package com.mga.financing.mvp.trade.traderesult;

import android.view.View;
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_traderesult;
    }

    @Override
    protected void initView() {
        weightNumTv = (TextView) findViewById(R.id.weight_num_tv);
        ppwTv = (TextView) findViewById(R.id.price_per_weight_tv);
        payPriceTv = (TextView) findViewById(R.id.pay_price_tv);
        scdTv = (TextView) findViewById(R.id.start_count_date_tv);
        doneTv = (TextView) findViewById(R.id.done_tv);
        doneTv.setOnClickListener(this);
    }

    @Override
    protected void initialize() {
        super.initialize();
//        初始化布局数据
        weightNumTv.setText(getBundle().getString(BundleKeyConstant.WEIGHT));
        ppwTv.setText(NowPrice.price);
        payPriceTv.setText(getBundle().getString(BundleKeyConstant.PRICE));
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
