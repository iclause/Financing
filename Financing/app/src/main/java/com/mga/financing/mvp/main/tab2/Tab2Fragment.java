package com.mga.financing.mvp.main.tab2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseFragment;
import com.mga.financing.ui.pullableview.PullToRefreshLayout;
import com.mga.financing.ui.pullableview.PullToRefreshLayout.OnRefreshListener;

/**
 * Created by mga on 2018/4/25 16:07.
 */

public class Tab2Fragment extends BaseFragment implements OnRefreshListener{
    @Override
    public void toOtherLayout(Class aClass, Bundle bundle) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragmet2;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ((PullToRefreshLayout) view.findViewById(R.id.refresh_view))
                .setOnRefreshListener(this);
    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        // 下拉刷新操作
        new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                // 千万别忘了告诉控件刷新完毕了哦！
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
//// 加载操作
//        new Handler()
//        {
//            @Override
//            public void handleMessage(Message msg)
//            {
//                // 千万别忘了告诉控件加载完毕了哦！
//                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
//            }
//        }.sendEmptyMessageDelayed(0, 2000);
    }
}
