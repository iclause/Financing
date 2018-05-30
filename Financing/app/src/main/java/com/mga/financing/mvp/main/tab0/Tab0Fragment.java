package com.mga.financing.mvp.main.tab0;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseFragment;
import com.mga.financing.mvp.BuyGoldenActivity;
import com.mga.financing.ui.GlideImageLoader;
import com.mga.financing.ui.pullableview.PullToRefreshLayout;
import com.mga.financing.ui.pullableview.PullToRefreshLayout.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mga on 2018/4/25 16:07.
 */

public class Tab0Fragment extends BaseFragment implements OnRefreshListener {
    private Banner banner;
    private List<Integer> images;
    private List<String> imagespath;
    private boolean isFirstIn=true;
    private PullToRefreshLayout ptrl;
    private TextView buyTv;



    @Override
    protected int bindLayout() {
        return R.layout.fragmet0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }





    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ptrl=((PullToRefreshLayout) view.findViewById(R.id.refresh_view));
        ptrl.setOnRefreshListener(this);
        // 第一次进入自动刷新
        if (isFirstIn)
        {
            ptrl.autoRefresh();
            isFirstIn = false;
        }
        initbanner(view);

        buyTv=(TextView)view.findViewById(R.id.buy_tv);
        buyTv.setOnClickListener(this);
    }

    private void initbanner(View view) {
        // TODO: 2018/5/9 加载轮播图 
        if (images == null) {
            images = new ArrayList<>();
            images.add(R.drawable.home_bottom_insurance_bg);
            images.add(R.drawable.home_bottom_real_gold_bg);
            images.add(R.drawable.ic_bullion_withdraw_offline);
            images.add(R.drawable.ic_bullion_withdraw_online);
        }
if (imagespath == null) {
    imagespath = new ArrayList<>();
    imagespath.add("https://hmls.hfbank.com.cn/hfapp-api/9.png");
    imagespath.add("http://ww1.sinaimg.cn/mw690/006dJESWgw1f6iyb8bzraj31kw0v67a2.jpg");

        }

        banner = (Banner) view.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
//        banner.setImages(images).setImageLoader(new GlideImageLoader()).setDelayTime(2500).start();
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
        // 上拉加载操作
//        new Handler()
//        {
//            @Override
//            public void handleMessage(Message msg)
//            {
                // 千万别忘了告诉控件刷新完毕了哦！
//                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
//            }
//        }.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_tv:
                toOtherLayout(BuyGoldenActivity.class, getBundle());
                break;
        }
    }


}
