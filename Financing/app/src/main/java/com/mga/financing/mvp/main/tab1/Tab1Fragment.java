package com.mga.financing.mvp.main.tab1;

import android.os.Bundle;
import android.view.View;

import com.mga.financing.R;
import com.mga.financing.adapter.MlistAdapter;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseFragment;
import com.mga.financing.bean.ProductBean;
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
    public void toOtherLayout(Class aClass, Bundle bundle) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragmet1;
    }

    @Override
    protected BasePresenter createPresenter() {
        tab1Presenter = new Tab1Presenter();
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

    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        // 正在下拉刷新操作
        logi("onRefresh");
        tab1Presenter.getProductList();
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

    }



    @Override
    public void refreshOk(List<ProductBean> productBeanList, Map<Integer, Integer> lettes) {
        logi("refreshOk");
        mAdapter.refreshData(productBeanList,lettes);
        prl.refreshFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void refreshFail() {
        logi("refreshFail");
        prl.refreshFinish(PullToRefreshLayout.FAIL);
    }
}
