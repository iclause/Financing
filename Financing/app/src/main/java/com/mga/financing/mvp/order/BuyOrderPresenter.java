package com.mga.financing.mvp.order;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.bean.request.BuyReq;
import com.mga.financing.http.ApiException;
import com.mga.financing.model.BuyLoader;
import com.mga.financing.mvp.main.MainActivity;
import com.mga.financing.subscribers.ProgressSubscriber;
import com.mga.financing.utils.UserInfoManager;

/**
 * Created by mga on 2018/6/20 20:03.
 */

public class BuyOrderPresenter extends BasePresenterImpl<BuyOrderContact.View> implements BuyOrderContact.Presenter {
    private  BuyLoader buyLoader;
    private String TAG = getClass().getSimpleName();
    private BuyReq buyReq;

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
        buyReq.setBindnumber("13681570672");
        buyReq.setProductid("aaaa");
        buyReq.setNumber("aaaa");
        buyReq.setOrderid(String.valueOf(timestamp));
        buyReq.setTimestamp(String.valueOf(timestamp));
        buyReq.setUserid("weixinid");
        buyLoader.buy(buyReq).subscribe(new ProgressSubscriber<String>(mContext){
            @Override
            public void onNext(String s) {
                super.onNext(s);
                if(!isViewAttach()) return;
                getView().toOtherLayout(MainActivity.class,null);

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if(!isViewAttach()) return;
                getView().showFailReason(((ApiException)e).getCode(), null);
            }
        });
    }
}
