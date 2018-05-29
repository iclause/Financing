package com.mga.financing.mvp.main.tab1;

import android.util.Log;

import com.mga.financing.base.bean.BaseNet;
import com.mga.financing.base.model.Callback;
import com.mga.financing.base.model.Token;
import com.mga.financing.base.presenter.BasePresenterImpl;
import com.mga.financing.bean.ProductBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mga on 2018/5/10 11:12.
 */

public class Tab1Presenter extends BasePresenterImpl<Tab1Contact.View> implements Tab1Contact.Presenter {


    private String TAG = Tab1Presenter.class.getSimpleName();
    private List<ProductBean> mList = null;
    private HashMap<Integer,Integer> mLettes=null;

    @Override
    public void getProductList() {
        BaseNet baseNet = new BaseNet();
        baseNet.setDescription("normal");
        getData(Token.API_USER_DATA, baseNet, new Callback() {

            @Override
            public void onSuccess(Object data) {
                Log.i(TAG, "onSuccess");
                initData();
                if (isViewAttach()) {
                    getView().refreshOk(mList,mLettes);
                }
            }

            @Override
            public void onFailure(Object data) {
                Log.i(TAG, "onFailure");
                if (isViewAttach()) {
                    getView().refreshFail();
                }
            }

            @Override
            public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i(TAG, "onError");
                if (isViewAttach()) {
                    getView().refreshFail();
                }
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        });
    }

    private void initData() {
            mList = new ArrayList<>();
            mLettes=new HashMap<>();
//        private TextView proName_tv; //eg:流动金
//        private TextView proDes_tv;  //eg:1元起购，随时买卖
//        private TextView upDownNum_tv;  //eg:1.0%
//        private TextView proDes1_tv;  //eg:目标年化回报率
//        private TextView circle_tv;  //eg:1~10个月
        for (int i = 0; i < 10; i++) {
            ProductBean pb = new ProductBean();
            if (i < 3) {
                pb.setProName("流动金");
                pb.setProDes(i+"元起购，随时买卖");
                pb.setUpDownNum("1.0%");
                pb.setProDes1("目标年化回报率");
                pb.setCircle("1~10个月");
                pb.setHeadertype(0);

            } else {
                pb.setProName("流动金");
                pb.setProDes("1元起购，随时买卖");
                pb.setUpDownNum("1.0%");
                pb.setProDes1("复合年化回报率");
                pb.setCircle("30~100天");
                pb.setHeadertype(1);
            }
            mList.add(pb);
        }
//        //获取名字首字母-大写
//        for (ProductBean pb : mList) {
//            String cahr = HanziToPinyin.getInstance().get(contant.getName().trim()
//                    .substring(0, 1)).get(0).target.substring(0, 1).toUpperCase().toUpperCase();
//            contant.setFirstChar(cahr);
//
//        }
//        //根据首字母排序
//        Collections.sort(mContantList, new Comparator<Contant>() {
//            @Override
//            public int compare(Contant contant, Contant t1) {
//                return contant.getFirstChar().compareTo(t1.getFirstChar());
//            }
//        });
        //保存每个字母下的联系人在数据中的位置
        for (int i = 0; i < mList.size(); i++) {
//            mList.get(i).setHeadIndex(i);
            if (!mLettes.containsKey(mList.get(i).getHeadertype())) {
                mLettes.put(mList.get(i).getHeadertype(), i);
            }
        }
        Log.i(TAG,"mlist"+mList.size());
        Log.i(TAG,"mLettes"+mLettes.size());
    }
}
