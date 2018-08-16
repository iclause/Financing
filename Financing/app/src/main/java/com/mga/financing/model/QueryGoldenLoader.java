package com.mga.financing.model;

import com.mga.financing.base.bean.BaseResponse;
import com.mga.financing.base.model.ObjectLoader;
import com.mga.financing.bean.response.QueryGoldenRes;
import com.mga.financing.http.Api;
import com.mga.financing.http.HttpResultFunc;
import com.mga.financing.http.RetrofitServiceManager;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by mga on 2018/7/20 18:11.
 */

public class QueryGoldenLoader extends ObjectLoader{
    private QueryGoldenService mQueryGoldenService;
    private String TAG = getClass().getSimpleName();

    public QueryGoldenLoader() {
        mQueryGoldenService = RetrofitServiceManager.getInstance().create(QueryGoldenService.class);
    }

    /**
     * 登录
     *
     * @param
     * @return
     */
    public Observable<QueryGoldenRes> queryGolden() {

        return observe(mQueryGoldenService.queryGolden())
                .map(new HttpResultFunc<QueryGoldenRes>());

    }

    public interface QueryGoldenService {
        @POST(Api.GOLDPRICE)
        Observable<BaseResponse<QueryGoldenRes>> queryGolden();
    }
}
