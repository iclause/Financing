package com.mga.financing.mvp.bindCard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.bean.response.ProductRes;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.http.ApiException;
import com.mga.financing.model.TestLoader;
import com.mga.financing.mvp.login.LoginPresenter;
import com.mga.financing.mvp.trade.buy.BuyActivity;
import com.mga.financing.subscribers.ProgressSubscriber;
import com.mga.financing.subscribers.SubscriberOnNextListener;
import com.mga.financing.utils.AppManager;
import com.mga.financing.utils.UserInfoManager;

import java.util.List;

/**
 * Created by mga on 2018/6/8 15:07.
 */

public class BindPresenter extends LoginPresenter implements BindContact.Presenter {
    private String TAG = getClass().getSimpleName();
    private Bundle mBundle = null;
    private SubscriberOnNextListener<List<ProductRes>> testOnNextLis;

    public BindPresenter(Context context) {
        super(context);
    }

    public BindPresenter(Context context, Bundle bundle) {
        super(context);
        this.mBundle = bundle;
    }

    @Override
    public void submitBindCard(final String account, String vfc) {
        // TODO: 2018/4/17 网络请求
        TestLoader mTestLoader=new TestLoader();

        testOnNextLis=new SubscriberOnNextListener<List<ProductRes>>() {

            @Override
            public void onNext(List<ProductRes> productRes) {
                if(!isViewAttach()) return;
                Log.i(TAG,"save bankcard :"+mBundle.getString(BundleKeyConstant.BANKCARD));
                UserInfoManager.saveBankCard(mContext,UserInfoManager.readAccount(mContext),mBundle.getString(BundleKeyConstant.BANKCARD));

                Log.i(TAG,"account :"+account+"  read bankcard :"+UserInfoManager.readBankCard(mContext,account));
                getView().toOtherLayout(BuyActivity.class,mBundle);
                AppManager.finishActivity(BindCardActivity2.class);
                AppManager.finishActivity(BindCardActivity1.class);
            }

            @Override
            public void onError(ApiException e) {
                if (!isViewAttach()) return;
                getView().showFailReason(e.getCode(), null);
            }


        };
        mTestLoader.listAllProduct().subscribe(new ProgressSubscriber<List<ProductRes>>(testOnNextLis,mContext));
    }
}
