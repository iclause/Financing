package com.mga.financing.mvp.main.tab1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.mga.financing.R;
import com.mga.financing.adapter.MlistAdapter;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseFragment;
import com.mga.financing.bean.response.ProductRes;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.mvp.product.ProductH5Activity;
import com.mga.financing.mvp.product.ProductsActivity;
import com.mga.financing.ui.pullableview.PullToRefreshLayout;
import com.mga.financing.ui.pullableview.PullToRefreshLayout.OnRefreshListener;
import com.mga.financing.ui.pullableview.PullableListView;
import com.mga.financing.ui.pullableview.PullableRecycleView;

import java.util.List;
import java.util.Map;

/**
 * Created by mga on 2018/4/25 16:07.
 */

public class Tab1Fragment extends BaseFragment implements OnRefreshListener, Tab1Contact.View {
    private PullToRefreshLayout prl;
    private Tab1Presenter tab1Presenter;
    private PullableRecycleView prv;
    private MlistAdapter mAdapter;
    private PullableListView plv;



    @Override
    protected int bindLayout() {
        return R.layout.fragmet1;
    }

    @Override
    protected BasePresenter createPresenter() {
        tab1Presenter = new Tab1Presenter(getContext());
        return tab1Presenter;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        prl = (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        prl.setOnRefreshListener(this);
//        RecyclerView
//        prv=(PullableRecycleView)view.findViewById(R.id.pullable_rv);
//        prv.setLayoutManager(new LinearLayoutManager(getContext()));
//        mAdapter=new MyAdapter(getContext(),null,null);
//        prv.setAdapter(mAdapter);

//        ListView
        plv=(PullableListView)view.findViewById(R.id.pullable_lv);
        mAdapter=new MlistAdapter(getContext(),null,null);
        plv.setAdapter(mAdapter);
        plv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                logi("plv------onclick"+((ProductRes)mAdapter.getItem(position)).getFathermenu());
                if("0".equals(((ProductRes)mAdapter.getItem(position)).getFathermenu())){
                     //子级菜单，跳转到h5
                    Bundle bundle=new Bundle();
                    bundle.putString(BundleKeyConstant.H5URL,((ProductRes)mAdapter.getItem(position)).getH5url());
                    bundle.putString(BundleKeyConstant.TITLE,((ProductRes)mAdapter.getItem(position)).getProductname());
                    bundle.putString(BundleKeyConstant.PRODUCTID,((ProductRes)mAdapter.getItem(position)).getProductid());
                    toOtherLayout(ProductH5Activity.class,bundle);

                }else if("1".equals(((ProductRes)mAdapter.getItem(position)).getFathermenu())){
                    //父级菜单，跳转到产品列表界面
                    Bundle bundle=new Bundle();
                    bundle.putString(BundleKeyConstant.TITLE,((ProductRes)mAdapter.getItem(position)).getProductname());
                    bundle.putString(BundleKeyConstant.PRODUCTID,((ProductRes)mAdapter.getItem(position)).getProductid());
                    toOtherLayout(ProductsActivity.class,bundle);

                }else{
                    loge("非子级菜单/父级菜单");
                }
            }
        });

    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        // 正在下拉刷新操作
        logi("onRefresh");
        tab1Presenter.getAllProductList();
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

    }

    @Override
    public void onResume() {
        super.onResume();
        tab1Presenter.getAllProductList();
    }

    @Override
    public void refreshOk(List<ProductRes> productResList, Map<Integer, Integer> lettes) {
        logi("refreshOk");
        mAdapter.refreshData(productResList,lettes);
        prl.refreshFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void refreshGoldenPrice(String price) {
//        donothing
    }

    @Override
    public void refreshFail() {
        logi("refreshFail");
        prl.refreshFinish(PullToRefreshLayout.FAIL);
    }

    @Override
    public void onClick(View v) {

    }
}
