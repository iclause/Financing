package com.mga.financing.mvp.main.tab3;

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

public class Tab3Fragment extends BaseFragment implements OnRefreshListener {
    private PullToRefreshLayout pullToRefreshLayout;

    @Override
    public void toOtherLayout(Class aClass, Bundle bundle) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragmet3;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        pullToRefreshLayout=(PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        pullToRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
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
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

    }
}
