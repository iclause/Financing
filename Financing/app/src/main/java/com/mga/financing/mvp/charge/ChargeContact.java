package com.mga.financing.mvp.charge;

import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseView;

/**
 * Created by mga on 2018/6/15 15:52.
 */

public class ChargeContact {

    interface View extends BaseView{
        void showPopDialog();
        void dismissPopDialog();
    }
    interface Presenter extends BasePresenter{

        //提交充值订单
        void submit();
        //提交充值密码
        void submitPassword();

    }
}
