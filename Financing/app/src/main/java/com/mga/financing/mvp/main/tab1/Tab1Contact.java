package com.mga.financing.mvp.main.tab1;

import com.mga.financing.bean.ProductBean;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseView;

import java.util.List;
import java.util.Map;

/**
 * Created by mga on 2018/5/10 11:20.
 */

public class Tab1Contact {
    interface View extends BaseView {
        void refreshOk(List<ProductBean> productBeanList, Map<Integer,Integer> lettes);


        void refreshFail();

    }

    interface Presenter extends BasePresenter {
        void getProductList();
    }
}
