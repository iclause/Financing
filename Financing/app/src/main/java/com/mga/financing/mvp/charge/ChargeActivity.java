package com.mga.financing.mvp.charge;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.ui.MPopupDialog;
import com.mga.financing.ui.XEditText;

/**
 * Created by mga on 2018/6/13 18:58.
 */

public class ChargeActivity extends BaseActivity implements ChargeContact.View{
    private TextView submitTv;
    private XEditText chargeNumEt;
    private ImageView bankIv;
    private TextView bankNameTv;
    private TextView infoTv;
    private ChargePresenter mChargePresenter;
    private MPopupDialog mPopDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_charge1;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText(getResources().getString(R.string.charge));
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void initView() {
        bankIv=(ImageView)findViewById(R.id.bank_iv);
        bankNameTv=(TextView)findViewById(R.id.bank_name_tv);
        infoTv=(TextView)findViewById(R.id.info);
        chargeNumEt=(XEditText)findViewById(R.id.charge_num_et);
        submitTv=(TextView)findViewById(R.id.submit_tv);
        submitTv.setOnClickListener(this);


    }

    @Override
    protected BasePresenter createPresenter() {
        mChargePresenter=new ChargePresenter(this);
        return mChargePresenter;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.submit_tv:
                 mChargePresenter.submit();
                break;
        }
    }

    @Override
    public void showPopDialog() {
        mPopDialog=new MPopupDialog(this,mChargePresenter);
        mPopDialog.show();
    }

    @Override
    public void dismissPopDialog() {
        if(mPopDialog!=null&&mPopDialog.isShowing()){
            mPopDialog.dismiss();
            mPopDialog=null;
        }
    }
}
