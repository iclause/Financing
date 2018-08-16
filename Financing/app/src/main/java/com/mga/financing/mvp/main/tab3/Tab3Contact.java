package com.mga.financing.mvp.main.tab3;

import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseView;
import com.mga.financing.bean.response.UserinfoRes;

/**
 * 我的页面
 * Created by mga on 2018/8/15 13:24.
 */

public class Tab3Contact {

    public interface View extends BaseView{
       void refreshUserinfoLayout(UserinfoRes userinfoRes);

    }
    public interface Presenter extends BasePresenter{

        void getUserinfo();
    }
}
