package com.mga.financing.mvp.main.tab3;

import android.content.Context;
import android.util.Log;

import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.bean.request.UserinfoReq;
import com.mga.financing.bean.response.UserinfoRes;
import com.mga.financing.http.ApiException;
import com.mga.financing.model.UserinfoLoader;
import com.mga.financing.subscribers.ProgressSubscriber;
import com.mga.financing.subscribers.SubscriberOnNextListener;
import com.mga.financing.utils.UserInfoManager;

/**
 * Created by mga on 2018/8/15 13:26.
 */

public class Tab3Presenter extends BasePresenterImpl<Tab3Contact.View> implements Tab3Contact.Presenter{
    private final UserinfoLoader mUserinfoLoader;
    private SubscriberOnNextListener<UserinfoRes> userinfoOnNextLis;
    private String TAG="Tab3Presenter";
    private UserinfoReq userinfoReq;

    public Tab3Presenter(Context context) {
        super(context);
        mUserinfoLoader=new UserinfoLoader();
    }


    @Override
    public void getUserinfo() {
        userinfoOnNextLis=new SubscriberOnNextListener<UserinfoRes>() {

            @Override
            public void onNext(UserinfoRes userinfoRes) {
                if(!isViewAttach()) return;
                Log.i(TAG,"userinfoRes :"+userinfoRes);
                getView().refreshUserinfoLayout(userinfoRes);

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
