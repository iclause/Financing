package com.mga.financing.mvp.login;

import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseView;

/**
 * Created by mga on 2018/4/12 11:22.
 */

/**
 * 所有登录、注册相关接口
 */
public class LoginContact {
   public  interface View extends BaseView {
        String getPhoneNumber();

    }





    interface Presenter extends BasePresenter {
        /**
         * 获取验证码并检查此账号是否注册过
         */
        void checkIsRegist();

        void login();
        /**
         * 验证码登录
         */
        void vfc();
        /**
         * 注册
         */
        void regist();
        /**
         * 语音验证码
         */
        void audioVfc();


    }
}
