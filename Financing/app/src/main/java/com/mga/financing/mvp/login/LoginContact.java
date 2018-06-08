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
    public interface View extends BaseView {
        String getPhoneNumber();

        void countDown();

    }


    interface Presenter extends BasePresenter {
        /**
         * 获取验证码并检查此账号是否注册过
         */
        void checkIsRegist(String account);

        void login(String account, String password);

        void getVfc(String account);

        /**
         * 验证码登录
         */
        void vfc(String account);

        /**
         * 注册
         */
        void regist(String account);

        /**
         * 语音验证码
         */
        void audioVfc(String account);


    }
}
