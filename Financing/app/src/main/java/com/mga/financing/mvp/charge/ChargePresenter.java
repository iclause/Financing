package com.mga.financing.mvp.charge;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.bean.request.ChargeReq;
import com.mga.financing.bean.response.ProductRes;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.http.ApiException;
import com.mga.financing.model.ChargeLoader;
import com.mga.financing.model.TestLoader;
import com.mga.financing.mvp.trade.buy.BuyActivity;
import com.mga.financing.subscribers.ProgressSubscriber;
import com.mga.financing.subscribers.SubscriberOnNextListener;
import com.mga.financing.utils.AppManager;
import com.mga.financing.utils.UserInfoManager;

import java.util.List;

/**
 * Created by mga on 2018/6/15 15:55.
 */

public class ChargePresenter extends BasePresenterImpl<ChargeContact.View> implements ChargeContact.Presenter {

    private final Context mContext;
    private final ChargeLoader mChargeLoader;
    private String TAG = getClass().getSimpleName();
    private SubscriberOnNextListener<List<ProductRes>> testOnNextLis;
    private SubscriberOnNextListener<String> chargeOnNextLis;
    private ChargeReq chargeReq;

    public ChargePresenter(Context context) {
        super(context);
        this.mContext = context;
        mChargeLoader=new ChargeLoader();

    }

    @Override
    public void submit() {
        TestLoader mTestLoader=new TestLoader();

        testOnNextLis=new SubscriberOnNextListener<List<ProductRes>>() {

            @Override
            public void onNext(List<ProductRes> productRes) {
                if(!isViewAttach()) return;
                Log.i(TAG,"all_productReslist :"+productRes.toString());
                getView().showPopDialog();
            }

            @Override
            public void onError(ApiException e) {
                if (!isViewAttach()) return;
                getView().showFailReason(e.getCode(), null);
            }


        };
        mTestLoader.listAllProduct().subscribe(new ProgressSubscriber<List<ProductRes>>(testOnNextLis,mContext));
    }

    @Override
    public void submitPassword(final Bundle bundle) {

        chargeOnNextLis=new SubscriberOnNextListener<String>() {

            @Override
            public void onNext(String str) {
                Log.i(TAG,"submitPassword :"+ str);
                if(!isViewAttach()) return;
                getView().dismissPopDialog();
                getView().toOtherLayout(BuyActivity.class,bundle);
                AppManager.finishActivity((Activity)mContext);
            }

            @Override
            public void onError(ApiException e) {
                if (!isViewAttach()) return;
                getView().showFailReason(e.getCode(), null);
            }


        };
        if(chargeReq==null){
            chargeReq=new ChargeReq();
        }
        chargeReq.setBindnumber(UserInfoManager.readAccount(mContext));
        chargeReq.setAmount(bundle.getString(BundleKeyConstant.CHARGE_PRICE));
        Log.i(TAG,"BundleKeyConstant.CHARGE_PRICE:"+bundle.getString(BundleKeyConstant.CHARGE_PRICE));
        mChargeLoader.charge(chargeReq).subscribe(new ProgressSubscriber<String>(chargeOnNextLis,mContext));
    }
}
