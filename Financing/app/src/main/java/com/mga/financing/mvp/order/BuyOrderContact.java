package com.mga.financing.mvp.order;

import android.os.Bundle;

import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseView;

/**
 * Created by mga on 2018/6/15 15:52.
 */

public class BuyOrderContact {

    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter{

        void buy(Bundle bundle);

    }
}
