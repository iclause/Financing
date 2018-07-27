package com.mga.financing.mvp.order;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.bean.request.BuyReq;
import com.mga.financing.bean.response.ProductRes;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.http.ApiException;
import com.mga.financing.model.BuyLoader;
import com.mga.financing.model.TestLoader;
import com.mga.financing.mvp.main.MainActivity;
import com.mga.financing.subscribers.ProgressSubscriber;
import com.mga.financing.subscribers.SubscriberOnNextListener;
import com.mga.financing.utils.AppManager;
import com.mga.financing.utils.UserInfoManager;

import java.util.List;

/**
 * Created by mga on 2018/6/20 20:03.
 */

public class BuyOrderPresenter extends BasePresenterImpl<BuyOrderContact.View> implements BuyOrderContact.Presenter {
    private  BuyLoader buyLoader;
    private String TAG = getClass().getSimpleName();
    private BuyReq buyReq;
    private SubscriberOnNextListener<String> buyOnNextLis;
    private SubscriberOnNextListener<List<ProductRes>> testOnNextLis;

    public BuyOrderPresenter(Context context) {
        super(context);
        buyLoader=new BuyLoader();
    }


    @Override
    public void buy(Bundle bundle) {
            buyReq = new BuyReq();

        long timestamp=System.currentTimeMillis();
        Log.i(TAG,"save account :"+UserInfoManager.readAccount(mContext));
//        buyReq.setBindnumber(UserInfoManager.readAccount(mContext));
        buyReq.setBindnumber(UserInfoManager.readAccount(mContext));
        buyReq.setProductid(bundle.getString(BundleKeyConstant.PRODUCTID,""));
        buyReq.setNumber(bundle.getString(BundleKeyConstant.WEIGHT,"0"));
        buyReq.setOrderid(String.valueOf(timestamp));
        buyReq.setTimestamp(String.valueOf(timestamp));
        buyReq.setUserid("weixinid"+UserInfoManager.readAccount(mContext));

        buyOnNextLis=new SubscriberOnNextListener<String>() {

            @Override
            public void onNext(String s) {
                if(!isViewAttach()) return;
                getView().toOtherLayout(MainActivity.class,null);
                AppManager.finishActivity((Activity)mContext);
            }

            @Override
            public void onError(ApiException e) {
                if(!isViewAttach()) return;
                getView().showFailReason(e.getCode(), null);
            }
        };
        buyLoader.buy(buyReq).subscribe(new ProgressSubscriber<String>(buyOnNextLis,mContext));
    }

    @Override
    public void queryBalance() {
        TestLoader mTestLoader=new TestLoader();

        testOnNextLis=new SubscriberOnNextListener<List<ProductRes>>() {

            @Override
            public void onNext(List<ProductRes> productRes) {
                if(!isViewAttach()) return;
              getView().showBalance("19235");
            }

            @Override
            public void onError(ApiException e) {

            }
        };
        mTestLoader.listAllProduct().subscribe(new ProgressSubscriber<List<ProductRes>>(testOnNextLis,mContext));
    }
}
