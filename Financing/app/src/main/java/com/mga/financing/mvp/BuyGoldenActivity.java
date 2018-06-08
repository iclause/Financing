package com.mga.financing.mvp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.mvp.bindCard.BindCardActivity1;
import com.mga.financing.utils.RegexUtils;
import com.mga.financing.utils.UserInfoManager;

/**
 * Created by mga on 2018/5/29 18:55.
 */

public class BuyGoldenActivity extends BaseActivity {
    private TextView walletBalanceTv;
    private TextView coTv;
    private TextView gpTv;
    private CheckBox cb;
    private LinearLayout nLl;
    private TextView yrTv;
    private TextView epTv;
    private TextView csTv;
    private EditText iwopEt;
    private TextView wopTv;
    private TextView rtgpTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_golden_order;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText(R.string.revolving_funds);
    }

    @Override
    protected void initView() {
        walletBalanceTv = (TextView) findViewById(R.id.wallet_balance_tv2);
        rtgpTv = (TextView) findViewById(R.id.real_time_golden_price_tv);
        wopTv = (TextView) findViewById(R.id.weight_or_price_tv);
        iwopEt = (EditText) findViewById(R.id.input_weight_or_price_et);
        csTv = (TextView) findViewById(R.id.change_state_tv);
        epTv = (TextView) findViewById(R.id.expect_price_tv2);
        yrTv = (TextView) findViewById(R.id.year_rate_tv2);
        nLl = (LinearLayout) findViewById(R.id.notice_ll);
        cb = (CheckBox) findViewById(R.id.checkbox);
        gpTv = (TextView) findViewById(R.id.golden_protocal_tv);
        coTv = (TextView) findViewById(R.id.confirm_order_tv);
        csTv.setOnClickListener(this);
        nLl.setOnClickListener(this);
        gpTv.setOnClickListener(this);
        coTv.setOnClickListener(this);
        iwopEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    coTv.setBackgroundColor(getResources().getColor(R.color.orange_btn));
                    coTv.setEnabled(true);
                    float weight=Float.parseFloat(s.toString());
                    float goldenprice=Float.parseFloat(rtgpTv.getText().toString());
                    float price=weight*goldenprice;
                    epTv.setText(String.valueOf(price)+"元");
                } else {
                    coTv.setBackgroundColor(getResources().getColor(R.color.grey_btn));
                    coTv.setEnabled(false);
                    epTv.setText("0.00元");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.change_state_tv:

                break;
            case R.id.notice_ll:

                break;
            case R.id.golden_protocal_tv:

                break;
            case R.id.confirm_order_tv:
                if (!cb.isChecked()) {
                    showToast(getResources().getString(R.string.read_agree_golden_protocal));
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(BundleKeyConstant.WEIGHT, iwopEt.getText().toString());
                bundle.putString(BundleKeyConstant.PRICE, epTv.getText().toString());

                String bankCardStr = UserInfoManager.readBankCard(this, UserInfoManager.readAccount(this));
                if ("".equals(bankCardStr) || !RegexUtils.isBankCard(bankCardStr)) {
                    //绑定银行卡
                 toOtherLayout(BindCardActivity1.class,bundle);
                } else {
                    //至购买订单

                }
                break;
        }
    }
}
