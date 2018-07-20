package com.mga.financing.model;

import com.mga.financing.base.bean.BaseResponse;
import com.mga.financing.base.model.ObjectLoader;
import com.mga.financing.bean.request.BuyReq;
import com.mga.financing.http.Api;
import com.mga.financing.http.HttpResultFunc;
import com.mga.financing.http.RetrofitServiceManager;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by mga on 2018/7/20 18:11.
 */

public class BuyLoader extends ObjectLoader{
    private BuyService mBuyService;
    private String TAG = getClass().getSimpleName();

    public BuyLoader() {
        mBuyService = RetrofitServiceManager.getInstance().create(BuyService.class);
    }

    /**
     * 登录
     *
     * @param
     * @return
     */
    public Observable<String> buy(BuyReq buyReq) {

        return observe(mBuyService.buy(buyReq))
                .map(new HttpResultFunc<String>());

    }

    public interface BuyService {
        @POST(Api.BUY_PRODUCT)
        Observable<BaseResponse<String>> buy(@Body BuyReq buyReq);
    }
}
