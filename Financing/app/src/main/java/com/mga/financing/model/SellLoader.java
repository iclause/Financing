package com.mga.financing.model;

import com.mga.financing.base.bean.BaseResponse;
import com.mga.financing.base.model.ObjectLoader;
import com.mga.financing.bean.request.SellReq;
import com.mga.financing.http.Api;
import com.mga.financing.http.HttpResultFunc;
import com.mga.financing.http.RetrofitServiceManager;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by mga on 2018/7/20 18:11.
 */

public class SellLoader extends ObjectLoader{
    private SellService mSellService;
    private String TAG = getClass().getSimpleName();

    public SellLoader() {
        mSellService = RetrofitServiceManager.getInstance().create(SellService.class);
    }

    /**
     * 登录
     *
     * @param
     * @return
     */
    public Observable<String> sell(SellReq sellReq) {

        return observe(mSellService.buy(sellReq))
                .map(new HttpResultFunc<String>());

    }

    public interface SellService {
        @POST(Api.SELL_PRODUCT)
        Observable<BaseResponse<String>> buy(@Body SellReq sellReq);
    }
}
