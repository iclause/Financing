package com.mga.financing.mvp.product;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.mga.financing.R;
import com.mga.financing.adapter.MlistAdapter;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.bean.response.ProductRes;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.mvp.main.tab1.Tab1Contact;
import com.mga.financing.mvp.main.tab1.Tab1Presenter;

import java.util.List;
import java.util.Map;

/**
 * Created by mga on 2018/7/18 15:35.
 */

public class ProductsActivity extends BaseActivity implements Tab1Contact.View {
    private ListView lv;
    private Tab1Presenter mProductsPresenter;
    private MlistAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_products;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppBarTitle.setText(getBundle().getString(BundleKeyConstant.TITLE));
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initialize() {
        super.initialize();
        mProductsPresenter.getProductList(getBundle().getString(BundleKeyConstant.PRODUCTID));
    }

    @Override
    protected void initView() {
        lv = (ListView) findViewById(R.id.pullable_lv);
        mAdapter = new MlistAdapter(getContext(), null, null);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
                bundle.putString(BundleKeyConstant.H5URL,((ProductRes)mAdapter.getItem(position)).getH5url());
                bundle.putString(BundleKeyConstant.TITLE,((ProductRes)mAdapter.getItem(position)).getProductname());
                bundle.putString(BundleKeyConstant.PRODUCTID,((ProductRes)mAdapter.getItem(position)).getProductid());
                toOtherLayout(ProductH5Activity.class,bundle);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        mProductsPresenter = new Tab1Presenter(this);
        return mProductsPresenter;
    }

    @Override
    public void refreshOk(List<ProductRes> productResList, Map<Integer, Integer> lettes) {
        logi("refreshOk ");
        mAdapter.refreshData(productResList,null);
    }

    @Override
    public void refreshGoldenPrice(String price) {
//        donothing
    }

    @Override
    public void refreshFail() {

    }
}
