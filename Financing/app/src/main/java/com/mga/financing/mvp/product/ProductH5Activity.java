package com.mga.financing.mvp.product;

import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.constant.BundleKeyConstant;

/**
 * Created by mga on 2018/7/18 15:36.
 * h5产品详情
 */

public class ProductH5Activity extends BaseActivity {
    private WebView mWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_products_h5;
    }
    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppBarTitle.setText(getBundle().getString(BundleKeyConstant.TITLE));
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
    }
    @Override
    protected void initView() {
        mWebView=(WebView)findViewById(R.id.myWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(this,"buy");
        mWebView.loadUrl(getBundle().getString(BundleKeyConstant.H5URL));

        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * h5选择购买时调用此接口
     */
    @JavascriptInterface
    public void startWechatShare(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Code for WebView goes here


            }
        });
    }
}
