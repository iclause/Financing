package com.mga.financing.mvp.trade.sell;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.constant.NowPrice;
import com.mga.financing.mvp.trade.TradeContact;
import com.mga.financing.mvp.trade.TradePresenter;
import com.mga.financing.ui.SellPopupDialog;

import java.text.DecimalFormat;

/**
 * Created by mga on 2018/8/9 15:38.
 */

public class SellActivity extends BaseActivity implements TradeContact.View {
    private TradePresenter mSellPresenter;
    private EditText inputSellEt;
    private RelativeLayout confirmSellRl;
    private Bundle sellBundle;
    private SellPopupDialog mSellPopDialog;
    private TextView expectPriceTv;
    private Float sellprice;
    private String sellPriceStr;
    private TextView rtgpTv;
    private TextView sellOutTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sell_golden;
    }

    @Override
    protected void initView() {
        inputSellEt = (EditText) findViewById(R.id.input_sell_weight_et);
        rtgpTv = (TextView) findViewById(R.id.real_time_golden_price_tv);
        sellOutTv = (TextView) findViewById(R.id.sell_out_tv);
        sellOutTv.setText(getBundle().getString(BundleKeyConstant.USER_WEIGHT));
        rtgpTv.setText(NowPrice.getPrice(this));
        expectPriceTv = (TextView) findViewById(R.id.expect_price_tv2);
        confirmSellRl = (RelativeLayout) findViewById(R.id.confirm_sell_out_rl);
        confirmSellRl.setClickable(false);
        confirmSellRl.setEnabled(false);
        confirmSellRl.setOnClickListener(this);
        inputSellEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    sellprice = Float.parseFloat(s.toString()) * Float.parseFloat(NowPrice.getPrice(SellActivity.this));
                    DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                    sellPriceStr = decimalFormat.format(sellprice);//format 返回的是字符串
                    expectPriceTv.setText(sellPriceStr.toString());
                    confirmSellRl.setBackground(getResources().getDrawable(R.drawable.shape_sell_btn_ennable));
                    confirmSellRl.setEnabled(true);
                    confirmSellRl.setClickable(true);
                } else {
                    confirmSellRl.setBackground(getResources().getDrawable(R.drawable.shape_sell_btn_unennable));
                    confirmSellRl.setEnabled(false);
                    confirmSellRl.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText("卖出黄金");

    }

    @Override
    protected BasePresenter createPresenter() {
        mSellPresenter = new TradePresenter(this);
        return mSellPresenter;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.confirm_sell_out_rl:
                sellBundle = new Bundle();
                sellBundle.putString(BundleKeyConstant.WEIGHT, inputSellEt.getText().toString());
                sellBundle.putString(BundleKeyConstant.SELL_PRICE, sellPriceStr);
                showPopDialog();
                break;
            default:

                break;
        }
    }

    @Override
    public void showBalance(String balance) {
//        donothing
    }

    @Override
    public void showPopDialog() {
        mSellPopDialog = new SellPopupDialog(this, mSellPresenter, sellBundle);
        mSellPopDialog.show();
    }

    @Override
    public void dismissPopDialog() {
        if (mSellPopDialog != null && mSellPopDialog.isShowing()) {
            mSellPopDialog.dismiss();
            mSellPopDialog = null;
        }
    }
}
