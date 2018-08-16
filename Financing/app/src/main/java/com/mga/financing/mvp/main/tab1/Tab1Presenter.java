package com.mga.financing.mvp.main.tab1;

import android.content.Context;
import android.util.Log;

import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.bean.request.ProductReq;
import com.mga.financing.bean.response.ProductRes;
import com.mga.financing.bean.response.QueryGoldenRes;
import com.mga.financing.constant.NowPrice;
import com.mga.financing.http.ApiException;
import com.mga.financing.http.NetCode;
import com.mga.financing.model.ProductLoader;
import com.mga.financing.model.QueryGoldenLoader;
import com.mga.financing.subscribers.NormalSubscriber;
import com.mga.financing.subscribers.SubscriberOnNextListener;
import com.mga.financing.utils.SharedPrefUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mga on 2018/5/10 11:12.
 */

public class Tab1Presenter extends BasePresenterImpl<Tab1Contact.View> implements Tab1Contact.Presenter {


    private final ProductLoader mProductLoader;
    private final QueryGoldenLoader mQueryGoldenLoader;
    private String TAG = Tab1Presenter.class.getSimpleName();
    private HashMap<Integer, Integer> mLettes = new HashMap<>();
    private SubscriberOnNextListener<List<ProductRes>> mSubscriberOnNextListener;
    private ProductReq productReq;
    private SubscriberOnNextListener<List<ProductRes>> allProductListOnNextLis;
    private SubscriberOnNextListener<List<ProductRes>> productListOnNextLis;
    private SubscriberOnNextListener<QueryGoldenRes> queryGoldenOnNextLis;


    public Tab1Presenter(Context context) {
        super(context);
        mProductLoader = new ProductLoader();
        mQueryGoldenLoader = new QueryGoldenLoader();

    }

    @Override
    public void getAllProductList() {
        allProductListOnNextLis = new SubscriberOnNextListener<List<ProductRes>>() {

            @Override
            public void onNext(List<ProductRes> productRes) {
                if (!isViewAttach()) return;
                Log.i(TAG, "getAllProductList onNext");
                Log.i(TAG, "all_productReslist :" + productRes.toString());
//                if (!isViewAttach()) return;
                //1.list集合要求按照headertype排好序；2.记录首个显示header的position
                for (int i = 0; i < productRes.size(); i++) {
                    if (!mLettes.containsKey(Integer.parseInt(productRes.get(i).getProductslogan()))) {
                        mLettes.put(Integer.parseInt(productRes.get(i).getProductslogan()), i);
                    }
                }
                getView().refreshOk(productRes, mLettes);
//                Log.i(TAG,"productReslist"+productRes.size());
//                Log.i(TAG,"productReslist"+productRes.toString());
//                Log.i(TAG,"mLettes"+mLettes.size());
//                Log.i(TAG,"mLettes"+mLettes.toString());
            }

            @Override
            public void onError(ApiException e) {
                Log.i(TAG, "getAllProductList onError");
                if (!isViewAttach()) return;
                getView().refreshOk(null, null);
                getView().showFailReason(e.getCode(), null);
            }


        };
        mProductLoader.listAllProduct().subscribe(new NormalSubscriber(allProductListOnNextLis, mContext));
    }

    @Override
    public void getProductList(String productid) {
        if (productReq == null) {
            productReq = new ProductReq();
        }
        productReq.setProductid(productid);

        productListOnNextLis = new SubscriberOnNextListener<List<ProductRes>>() {


            @Override
            public void onNext(List<ProductRes> productRes) {
                Log.i(TAG, "getProductList onNext");
                Log.i(TAG, "productReslist :\n" + productRes.toString());
                if (!isViewAttach()) return;
                getView().refreshOk(productRes, null);
            }

            @Override
            public void onError(ApiException e) {
                Log.i(TAG, "getProductList onError");
                if (!isViewAttach()) return;
                getView().refreshOk(null, null);
                if (e.getCode() == NetCode.PRODUCTS_IS_NULL) {
//                    getView().showToast("产品列表为空");
                }
//                getView().showFailReason(e.getCode(), null);
            }


        };
        mProductLoader.listProduct(productReq).subscribe(new NormalSubscriber(productListOnNextLis, mContext));
    }

    @Override
    public void queryGoldenPrice() {
        queryGoldenOnNextLis = new SubscriberOnNextListener<QueryGoldenRes>() {

            @Override
            public void onNext(QueryGoldenRes queryGoldenRes) {
//                存储当前金价
                NowPrice.price = queryGoldenRes.getGoldprice();
                SharedPrefUtil.setNowGoldenPrice(queryGoldenRes.getGoldprice(), mContext);
                if(!isViewAttach())return;
                getView().refreshGoldenPrice(queryGoldenRes.getGoldprice());
            }

            @Override
            public void onError(ApiException e) {
                Log.i(TAG, "queryGoldenPrice onError");

            }


        };
        mQueryGoldenLoader.queryGolden().subscribe(new NormalSubscriber(queryGoldenOnNextLis, mContext));
    }


//    private void initData() {
//            mList = new ArrayList<>();
//            mLettes=new HashMap<>();
////        private TextView proName_tv; //eg:流动金
////        private TextView proDes_tv;  //eg:1元起购，随时买卖
////        private TextView upDownNum_tv;  //eg:1.0%
////        private TextView proDes1_tv;  //eg:目标年化回报率
////        private TextView circle_tv;  //eg:1~10个月
//        for (int i = 0; i < 10; i++) {
//            ProductRes pb = new ProductRes();
//            if (i < 3) {
//                pb.setProName("流动金");
//                pb.setProDes(i+"元起购，随时买卖");
//                pb.setUpDownNum("1.0%");
//                pb.setProDes1("目标年化回报率");
//                pb.setCircle("1~10个月");
//                pb.setHeadertype(0);
//
//            } else if(i<7) {
//                pb.setProName("流动金");
//                pb.setProDes("1元起购，随时买卖");
//                pb.setUpDownNum("1.0%");
//                pb.setProDes1("复合年化回报率");
//                pb.setCircle("30~100天");
//                pb.setHeadertype(1);
//            }else{
//                pb.setProName("流动金");
//                pb.setProDes("1元起购，随时买卖");
//                pb.setUpDownNum("1.0%");
//                pb.setProDes1("复合年化回报率");
//                pb.setCircle("30~100天");
//                pb.setHeadertype(0);
//            }
//            mList.add(pb);
//        }
////        //获取名字首字母-大写
////        for (ProductBean pb : mList) {
////            String cahr = HanziToPinyin.getInstance().get(contant.getName().trim()
////                    .substring(0, 1)).get(0).target.substring(0, 1).toUpperCase().toUpperCase();
////            contant.setFirstChar(cahr);
////
////        }
////        //根据首字母排序
//        Collections.sort(mList, new Comparator<ProductRes>() {
//            @Override
//            public int compare(ProductRes lhs, ProductRes rhs) {
//                return lhs.getHeadertype()-rhs.getHeadertype();
//            }
//        });
//        //1.list集合要求按照headertype排好序；2.记录首个显示header的position
//        for (int i = 0; i < mList.size(); i++) {
////            mList.get(i).setHeadIndex(i);
//            if (!mLettes.containsKey(mList.get(i).getHeadertype())) {
//                mLettes.put(mList.get(i).getHeadertype(), i);
//            }
//        }
//        Log.i(TAG,"mlist"+mList.size());
//        Log.i(TAG,"mlist"+mList.toString());
//        Log.i(TAG,"mLettes"+mLettes.size());
//        Log.i(TAG,"mLettes"+mLettes.toString());
//    }
}
