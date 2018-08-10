package com.mga.financing.mvp.trade;

import android.os.Bundle;

import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseView;

/**
 * Created by mga on 2018/6/15 15:52.
 */

public class TradeContact {

    public interface View extends BaseView {

        void showBalance(String balance);
        void showPopDialog();
        void dismissPopDialog();
    }

    public interface Presenter extends BasePresenter {

        void buy(Bundle bundle);
        void sell(Bundle bundle);
        void queryBalance();

    }
}
