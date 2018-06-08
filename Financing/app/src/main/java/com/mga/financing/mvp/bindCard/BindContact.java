package com.mga.financing.mvp.bindCard;

import com.mga.financing.base.presenter.BasePresenter;

/**
 * Created by mga on 2018/6/8 15:08.
 */

public class BindContact {


    interface Presenter extends BasePresenter{

       void  submitBindCard(String account,String vfc);
    }
}
