package com.mga.financing.mvp.charge;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.bean.response.ProductRes;
import com.mga.financing.model.TestLoader;
import com.mga.financing.mvp.order.BuyOrderActivity;
import com.mga.financing.subscribers.ProgressSubscriber;

import java.util.List;

/**
 * Created by mga on 2018/6/15 15:55.
 */

public class ChargePresenter extends BasePresenterImpl<ChargeContact.View> implements ChargeContact.Presenter {

    private final Context mContext;
    private String TAG = getClass().getSimpleName();

    public ChargePresenter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public void submit() {
        TestLoader mTestLoader=new TestLoader();
        mTestLoader.listAllProduct().subscribe(new ProgressSubscriber<List<ProductRes>>(mContext){
            @Override
            public void onNext(List<ProductRes> productRes) {
                super.onNext(productRes);
                if(!isViewAttach()) return;
                Log.i(TAG,"all_productReslist :"+productRes.toString());

                getView().showPopDialog();
            }

        });
    }

    @Override
    public void submitPassword(final Bundle bundle) {
        TestLoader mTestLoader=new TestLoader();
        mTestLoader.listAllProduct().subscribe(new ProgressSubscriber<List<ProductRes>>(mContext){
            @Override
            public void onNext(List<ProductRes> productRes) {
                super.onNext(productRes);
                if(!isViewAttach()) return;
                Log.i(TAG,"all_productReslist :"+productRes.toString());

                getView().toOtherLayout(BuyOrderActivity.class,bundle);
            }

        });
    }
}
