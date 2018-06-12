package com.mga.financing.mvp.bindCard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.R;
import com.mga.financing.base.bean.BaseNet;
import com.mga.financing.base.model.Callback;
import com.mga.financing.base.model.Token;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.mvp.login.LoginPresenter;
import com.mga.financing.mvp.order.BuyOrderActivity;
import com.mga.financing.utils.ActivityCollector;
import com.mga.financing.utils.UserInfoManager;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mga on 2018/6/8 15:07.
 */

public class BindPresenter extends LoginPresenter implements BindContact.Presenter {
    private Context mContect;
    private String TAG = getClass().getSimpleName();
    private Bundle mBundle = null;

    public BindPresenter(Context context) {
        super(context);
    }

    public BindPresenter(Context context, Bundle bundle) {
        super(context);
        this.mBundle = bundle;
        this.mContect = context;
    }

    @Override
    public void submitBindCard(String account, String vfc) {
        // TODO: 2018/4/17 网络请求
        if (isViewAttach()) {
            getView().showDialog(true, getView().getContext().getResources().getString(R.string.loading));
        }
        BaseNet baseNet = new BaseNet();
        baseNet.setDescription("normal");
        getData(Token.API_USER_DATA, baseNet, new Callback() {
            @Override
            public void onSuccess(Object data) {
                //tologinsecond
                Log.i(TAG, "onSuccess");
                String bankCard = mBundle.getString(BundleKeyConstant.BANKCARD);
                UserInfoManager.saveBankCard(mContect, UserInfoManager.readAccount(mContect), bankCard);
                if (isViewAttach()) {
                    ActivityCollector.removeActivity(BindCardActivity1.instance);
                    ActivityCollector.removeActivity(BindCardActivity2.instance);
                    Bundle bundle = new Bundle();
                    bundle.putString(BundleKeyConstant.PHONENUMBER, getView().getPhoneNumber());
                    getView().toOtherLayout(BuyOrderActivity.class, bundle);

                }
            }

            @Override
            public void onFailure(Object data) {
                //toregist
                if (isViewAttach()) {
                    getView().showToast("提交失败");
                }
            }

            @Override
            public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
                if (isViewAttach()) {
                    getView().showDialog(false, null);
                }
            }
        });
    }
}
