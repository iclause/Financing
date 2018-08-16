package com.mga.financing.mvp.trade;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.bean.request.BuyReq;
import com.mga.financing.bean.request.SellReq;
import com.mga.financing.bean.request.UserinfoReq;
import com.mga.financing.bean.response.ProductRes;
import com.mga.financing.bean.response.UserinfoRes;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.http.ApiException;
import com.mga.financing.model.BuyLoader;
import com.mga.financing.model.SellLoader;
import com.mga.financing.model.UserinfoLoader;
import com.mga.financing.mvp.trade.traderesult.TradeResultActivity;
import com.mga.financing.subscribers.ProgressSubscriber;
import com.mga.financing.subscribers.SubscriberOnNextListener;
import com.mga.financing.utils.AppManager;
import com.mga.financing.utils.UserInfoManager;

import java.util.List;

/**
 * 交易用的presenter
 * 包含：买入、卖出、查询银行卡账户余额接口
 * Created by mga on 2018/6/20 20:03.
 */

public class TradePresenter extends BasePresenterImpl<TradeContact.View> implements TradeContact.Presenter {
    private  UserinfoLoader mUserinfoLoader;
    private  SellLoader sellLoader;
    private  BuyLoader buyLoader;
    private String TAG = getClass().getSimpleName();
    private BuyReq buyReq;
    private SubscriberOnNextListener<String> buyOnNextLis;
    private SubscriberOnNextListener<List<ProductRes>> testOnNextLis;
    private SellReq sellReq;
    private SubscriberOnNextListener<String> sellOnNextLis;
    private SubscriberOnNextListener<UserinfoRes> userinfoOnNextLis;
    private UserinfoReq userinfoReq;

    public TradePresenter(Context context) {
        super(context);
        buyLoader=new BuyLoader();
        sellLoader=new SellLoader();
        mUserinfoLoader=new UserinfoLoader();
    }


    @Override
    public void buy(final Bundle bundle) {
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
                getView().dismissPopDialog();
                bundle.putInt(BundleKeyConstant.TRADE_TYPE,BundleKeyConstant.TRADE_TYPE_BUY);
                getView().toOtherLayout(TradeResultActivity.class,bundle);
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
    public void sell(final Bundle bundle) {
        sellReq = new SellReq();

        long timestamp=System.currentTimeMillis();
        Log.i(TAG,"save account :"+UserInfoManager.readAccount(mContext));
//        buyReq.setBindnumber(UserInfoManager.readAccount(mContext));
        sellReq.setBindnumber(UserInfoManager.readAccount(mContext));
        sellReq.setProductid("1");
        sellReq.setNumber(bundle.getString(BundleKeyConstant.WEIGHT,"0"));
        sellReq.setOrderid(String.valueOf(timestamp));
        sellReq.setTimestamp(String.valueOf(timestamp));
        sellReq.setUserid("weixinid"+UserInfoManager.readAccount(mContext));

        sellOnNextLis=new SubscriberOnNextListener<String>() {

            @Override
            public void onNext(String s) {
                if(!isViewAttach()) return;
                getView().dismissPopDialog();
                bundle.putInt(BundleKeyConstant.TRADE_TYPE,BundleKeyConstant.TRADE_TYPE_SELL);
                getView().toOtherLayout(TradeResultActivity.class,bundle);
                AppManager.finishActivity((Activity)mContext);
            }

            @Override
            public void onError(ApiException e) {
                if(!isViewAttach()) return;
                getView().showFailReason(e.getCode(), null);
            }


        };
        sellLoader.sell(sellReq).subscribe(new ProgressSubscriber<String>(sellOnNextLis,mContext));
    }

    @Override
    public void queryBalance() {


       userinfoOnNextLis=new SubscriberOnNextListener<UserinfoRes>() {

            @Override
            public void onNext(UserinfoRes userinfoRes) {
                if(!isViewAttach()) return;
              Log.i(TAG,"userinfoRes :"+userinfoRes);
              getView().showBalance(userinfoRes.getBalance());
            }

            @Override
            public void onError(ApiException e) {

            }


        };
        if(userinfoReq==null){
            userinfoReq=new UserinfoReq();
        }
        userinfoReq.setBindnumber(UserInfoManager.readAccount(mContext));
        userinfoReq.setUserid("weixinid"+UserInfoManager.readAccount(mContext));
        mUserinfoLoader.getUserinfo(userinfoReq).subscribe(new ProgressSubscriber<UserinfoRes>(userinfoOnNextLis,mContext));
    }
}
