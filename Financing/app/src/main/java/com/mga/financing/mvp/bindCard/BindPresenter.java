package com.mga.financing.mvp.bindCard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.bean.response.ProductRes;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.model.TestLoader;
import com.mga.financing.mvp.login.LoginPresenter;
import com.mga.financing.mvp.order.BuyOrderActivity;
import com.mga.financing.subscribers.ProgressSubscriber;
import com.mga.financing.utils.UserInfoManager;

import java.util.List;

/**
 * Created by mga on 2018/6/8 15:07.
 */

public class BindPresenter extends LoginPresenter implements BindContact.Presenter {
    private String TAG = getClass().getSimpleName();
    private Bundle mBundle = null;

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
        mTestLoader.listAllProduct().subscribe(new ProgressSubscriber<List<ProductRes>>(mContext){
            @Override
            public void onNext(List<ProductRes> productRes) {
                super.onNext(productRes);
                if(!isViewAttach()) return;
                Log.i(TAG,"all_productReslist :"+productRes.toString());
                Log.i(TAG,"save bankcard :"+mBundle.getString(BundleKeyConstant.BANKCARD));
                UserInfoManager.saveBankCard(mContext,UserInfoManager.readAccount(mContext),mBundle.getString(BundleKeyConstant.BANKCARD));

                Log.i(TAG,"account :"+account+"  read bankcard :"+UserInfoManager.readBankCard(mContext,account));
                getView().toOtherLayout(BuyOrderActivity.class,mBundle);
            }

        });
    }
}
