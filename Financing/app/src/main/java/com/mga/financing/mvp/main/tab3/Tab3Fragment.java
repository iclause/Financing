package com.mga.financing.mvp.main.tab3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseFragment;
import com.mga.financing.bean.response.UserinfoRes;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.mvp.login.LoginFirstActivity;
import com.mga.financing.mvp.trade.sell.GoldenAssetsActivity;
import com.mga.financing.ui.pullableview.PullToRefreshLayout;
import com.mga.financing.ui.pullableview.PullToRefreshLayout.OnRefreshListener;
import com.mga.financing.utils.UserInfoManager;

import java.text.DecimalFormat;

import static com.mga.financing.R.id.user_login_tv;

/**
 * Created by mga on 2018/4/25 16:07.
 */

public class Tab3Fragment extends BaseFragment implements OnRefreshListener, Tab3Contact.View {
    private PullToRefreshLayout pullToRefreshLayout;
    private LinearLayout goldenAssetsLl;
    private TextView walletBalanceTv;
    private TextView goldenAssetsPriceTv;
    private TextView goldenAssetsWeightTv;
    private TextView accumulatedIncomeTv;
    private TextView userTotalAssetTv;
    private TextView userAccountTv;
    private Tab3Presenter mTab3Presenter;
    private LinearLayout hasUserLl;
    private LinearLayout noUserLl;
    private TextView userAccountLl;
    private TextView userLoginTv;
    private Bundle mbundle;
    private UserinfoRes mUserinfoRes;


    @Override
    protected int bindLayout() {
        return R.layout.fragmet3;
    }

    @Override
    protected BasePresenter createPresenter() {
        mTab3Presenter = new Tab3Presenter(getContext());
        return mTab3Presenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (UserInfoManager.getIsAutoLogin(getContext())) {
            mTab3Presenter.getUserinfo();
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        pullToRefreshLayout = (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        pullToRefreshLayout.setOnRefreshListener(this);
        goldenAssetsLl = (LinearLayout) view.findViewById(R.id.golden_assets_ll);
        goldenAssetsLl.setOnClickListener(this);

        hasUserLl = (LinearLayout) view.findViewById(R.id.has_user_ll);
        noUserLl = (LinearLayout) view.findViewById(R.id.no_user_ll);
        userAccountLl = (TextView) view.findViewById(R.id.user_account_tv);


        userLoginTv = (TextView) view.findViewById(user_login_tv);
        userLoginTv.setOnClickListener(this);
        userTotalAssetTv = (TextView) view.findViewById(R.id.user_total_asset_tv);
        accumulatedIncomeTv = (TextView) view.findViewById(R.id.accumulated_income_tv);//累计收益
        goldenAssetsWeightTv = (TextView) view.findViewById(R.id.golden_assets_weight_tv);//黄金资产克重
        goldenAssetsPriceTv = (TextView) view.findViewById(R.id.golden_assets_price_tv);//黄金资产价值
        walletBalanceTv = (TextView) view.findViewById(R.id.wallet_balance_tv);//钱包余额
//初始化用户数据
        if (UserInfoManager.getIsAutoLogin(getContext())) {
            hasUserLl.setVisibility(View.VISIBLE);
            noUserLl.setVisibility(View.GONE);
            userAccountLl.setText(UserInfoManager.readAccount(getContext()));
        } else {
            hasUserLl.setVisibility(View.GONE);
            noUserLl.setVisibility(View.VISIBLE);
            accumulatedIncomeTv.setText("0.00");
            goldenAssetsWeightTv.setText("0.00");
            goldenAssetsPriceTv.setText("0.00");
            walletBalanceTv.setText("0.00");
        }

    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        if (UserInfoManager.getIsAutoLogin(getContext())) {
            mTab3Presenter.getUserinfo();
        }
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.golden_assets_ll:
//             showToast("golden_assets_ll onclick");
                if(mBundle==null) mbundle=new Bundle();
                if(mUserinfoRes==null){
                    mbundle.putString(BundleKeyConstant.USER_WEIGHT, "0");
                    mbundle.putString(BundleKeyConstant.USER_PRICE, "0.00");
                }else {
                    mbundle.putString(BundleKeyConstant.USER_WEIGHT, mUserinfoRes.getAmount());
                    mbundle.putString(BundleKeyConstant.USER_PRICE, mUserinfoRes.getGoldvalue());
                }
                toOtherLayout(GoldenAssetsActivity.class, mbundle);
                break;
            case user_login_tv:
                toOtherLayout(LoginFirstActivity.class, null);
                break;
            default:
                showToast("此功能暂未开放");
                break;
        }
    }

    @Override
    public void refreshUserinfoLayout(UserinfoRes userinfoRes) {
        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        Float totalPrice = Float.parseFloat(userinfoRes.getBalance()) + Float.parseFloat(userinfoRes.getGoldvalue());
        DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String totalPricestr = decimalFormat.format(totalPrice);//format 返回的是字符串
        userTotalAssetTv.setText(totalPricestr);
        goldenAssetsWeightTv.setText(userinfoRes.getAmount());
        goldenAssetsPriceTv.setText(userinfoRes.getGoldvalue());
        walletBalanceTv.setText(userinfoRes.getBalance());

//        封装数据
        this.mUserinfoRes=userinfoRes;

    }
}
