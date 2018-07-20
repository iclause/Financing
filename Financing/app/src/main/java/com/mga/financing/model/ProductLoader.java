package com.mga.financing.model;

import com.mga.financing.base.bean.BaseResponse;
import com.mga.financing.base.model.ObjectLoader;
import com.mga.financing.bean.request.ProductReq;
import com.mga.financing.bean.response.ProductRes;
import com.mga.financing.http.Api;
import com.mga.financing.http.HttpResultFunc;
import com.mga.financing.http.RetrofitServiceManager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Func1;


public class ProductLoader extends ObjectLoader {
    private ProductService mProductService;
    private String TAG = getClass().getSimpleName();

    public ProductLoader() {
        mProductService = RetrofitServiceManager.getInstance().create(ProductService.class);
    }

    /**
     * 登录
     *
     * @param
     * @return
     */
    public Observable<List<ProductRes>> listAllProduct() {

        return observe(mProductService.listAllProduct())
                .map(new HttpResultFunc<List<ProductRes>>()).map(new Func1<List<ProductRes>, List<ProductRes>>() {
                    @Override
                    public List<ProductRes> call(List<ProductRes> productRes) {

                        Collections.sort(productRes, new Comparator<ProductRes>() {
                            @Override
                            public int compare(ProductRes lhs, ProductRes rhs) {
                                return Integer.parseInt(lhs.getProductslogan()) - Integer.parseInt(rhs.getProductslogan());
                            }
                        });
                        return productRes;
                    }
                });

    }

    /**
     * 登录
     *
     * @param
     * @return
     */
    public Observable<List<ProductRes>> listProduct(ProductReq productid) {

        return observe(mProductService.listProduct(productid))
                .map(new HttpResultFunc<List<ProductRes>>());

    }


    public interface ProductService {

        @POST(Api.LIST_PRODUCT)
        Observable<BaseResponse<List<ProductRes>>> listProduct(@Body ProductReq productid);


        @POST(Api.LIST_ALLPRODUCT)
        Observable<BaseResponse<List<ProductRes>>> listAllProduct();
    }

}

