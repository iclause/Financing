package com.mga.financing.mvp.charge;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.bean.response.ProductRes;
import com.mga.financing.http.ApiException;
import com.mga.financing.model.TestLoader;
import com.mga.financing.mvp.trade.buy.BuyActivity;
import com.mga.financing.subscribers.ProgressSubscriber;
import com.mga.financing.subscribers.SubscriberOnNextListener;
import com.mga.financing.utils.AppManager;

import java.util.List;

/**
 * Created by mga on 2018/6/15 15:55.
 */

public class ChargePresenter extends BasePresenterImpl<ChargeContact.View> implements ChargeContact.Presenter {

    private final Context mContext;
    private String TAG = getClass().getSimpleName();
    private SubscriberOnNextListener<List<ProductRes>> testOnNextLis;

    public ChargePresenter(Context context) {
        super(context);
        this.mContext = context;
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
        TestLoader mTestLoader=new TestLoader();

        testOnNextLis=new SubscriberOnNextListener<List<ProductRes>>() {

            @Override
            public void onNext(List<ProductRes> productRes) {
                if(!isViewAttach()) return;
                Log.i(TAG,"all_productReslist :"+productRes.toString());
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
        mTestLoader.listAllProduct().subscribe(new ProgressSubscriber<List<ProductRes>>(testOnNextLis,mContext));
    }
}
