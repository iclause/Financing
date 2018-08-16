package com.mga.financing.mvp.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.bean.request.LoginReq;
import com.mga.financing.bean.request.RegistReq;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.http.ApiException;
import com.mga.financing.http.NetCode;
import com.mga.financing.model.LoginLoader;
import com.mga.financing.model.RegistLoader;
import com.mga.financing.mvp.main.MainActivity;
import com.mga.financing.mvp.regist.RegistActivity;
import com.mga.financing.safe.Md5Encrypt;
import com.mga.financing.subscribers.ProgressSubscriber;
import com.mga.financing.subscribers.SubscriberOnNextListener;
import com.mga.financing.utils.AppManager;
import com.mga.financing.utils.DevUtils;
import com.mga.financing.utils.UserInfoManager;

/**
 * Created by mga on 2018/4/12 11:22.
 */

public class LoginPresenter extends BasePresenterImpl<LoginContact.View> implements LoginContact.Presenter {


    private static final String TAG = LoginPresenter.class.getSimpleName();
    private final LoginLoader loginLoader;
    private final RegistLoader registLoader;
    private ProgressSubscriber<String> mProgressSubscriber;
    private SubscriberOnNextListener<Integer> registOnNextLis;
    private SubscriberOnNextListener<String> loginOnNextLis;
    private SubscriberOnNextListener<Integer> checkIsRegistOnNextLis;

    public LoginPresenter(Context context) {
        super(context);
        loginLoader=new LoginLoader();
        registLoader=new RegistLoader();

    }


    @Override
    public void checkIsRegist(String account) {
        Log.i(TAG,"excute checkIsRegist");
        if (!isViewAttach()) return;
        RegistReq registReq = new RegistReq();
        registReq.setBindnumber(getView().getPhoneNumber());

        checkIsRegistOnNextLis=new SubscriberOnNextListener<Integer>() {

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(ApiException e) {
                if (!isViewAttach()) return;
                Bundle bundle = new Bundle();
                bundle.putString(BundleKeyConstant.PHONENUMBER, getView().getPhoneNumber());
                    switch ( e.getCode()) {
                        case NetCode.USER_NOT_OPEN_ACCOUNT:
                            //注册成功进入到注册界面输入注册验证码
                            getView().toOtherLayout(RegistActivity.class, bundle);
                            break;
                        case NetCode.USER_ALREADY_OPEN_ACCOUN:
                            //已经注册过，跳到登录界面直接登录
                            getView().toOtherLayout(LoginPasswordActivity.class, bundle);
                            break;
                        default:
                            //显示错误弹窗
                            getView().showFailReason(e.getCode(), null);
                            break;

                }

            }


        };
        registLoader.isRegist(registReq).subscribe(new ProgressSubscriber<Integer>(checkIsRegistOnNextLis,mContext) );
    }

    @Override
    public void login(final String account, final String password) {
        Log.i(TAG,"excute login");
        if (!isViewAttach()) return;
        LoginReq loginReq = new LoginReq();
        loginReq.setBindnumber(getView().getPhoneNumber());
        loginReq.setPassword(Md5Encrypt.getMD5(password));
        loginReq.setImei(DevUtils.getImei(mContext.getApplicationContext()));
        loginReq.setRegistration_id("170976fa8a8220f42d4");
        loginReq.setDevice_type(DevUtils.DEVICE_TYPE);

        loginOnNextLis=new SubscriberOnNextListener<String>() {

            @Override
            public void onNext(String s) {
                Log.i(TAG,"login str"+s);
//                保存密码
                UserInfoManager.saveUserLoginInfo(mContext,account,password);
                Log.i(TAG,"save user data :\n account "+UserInfoManager.readAccount(mContext));
                Log.i(TAG,"save user data :\n password "+UserInfoManager.readPassword(mContext,account));
                if (!isViewAttach()) return;
                Bundle bundle = new Bundle();
                bundle.putString(BundleKeyConstant.PHONENUMBER, getView().getPhoneNumber());
                getView().toOtherLayout(MainActivity.class, bundle);
                AppManager.finishAllActivity();
            }

            @Override
            public void onError(ApiException e) {
                if (!isViewAttach()) return;
                getView().showFailReason(e.getCode(), null);

            }


        };
        loginLoader.login(loginReq).subscribe(new ProgressSubscriber<String>(loginOnNextLis,mContext));

    }

    @Override
    public void vfc(String account, String vfc) {

    }

    @Override
    public void getVfc(String account) {

    }

    @Override
    public void regist(final String account, final String password, String vfc) {
        Log.i(TAG,"excute regist");
        RegistReq registReq = new RegistReq();
        registReq.setUserid("weixinid"+UserInfoManager.readAccount(mContext));
        registReq.setNickname("zhangsan");
        registReq.setBindnumber(getView().getPhoneNumber());
        registReq.setVerificationcode(vfc);
        registReq.setPassword(Md5Encrypt.getMD5(password ));
        registReq.setImei(DevUtils.getImei(mContext.getApplicationContext()));
        registReq.setRegistration_id("170976fa8a8220f42d4");
        registReq.setDevice_type(DevUtils.DEVICE_TYPE);

        registOnNextLis=new SubscriberOnNextListener<Integer>() {

            @Override
            public void onNext(Integer integer) {
//                保存密码
                Log.i("onNext","integer:"+integer);
                UserInfoManager.saveUserLoginInfo(mContext,account,password);
                if (!isViewAttach()) return;
                Bundle bundle = new Bundle();
                bundle.putString(BundleKeyConstant.PHONENUMBER, getView().getPhoneNumber());
                login(account,password);
            }

            @Override
            public void onError(ApiException e) {
                Log.i("onNext","ApiException:"+e.getCode());
                if (!isViewAttach()) return;
                getView().showFailReason(e.getCode(), null);
            }


        };
        registLoader.regist(registReq).subscribe(new ProgressSubscriber<Integer>(registOnNextLis, mContext));

    }

    @Override
    public void audioVfc(String account, String vfc) {

    }

}
