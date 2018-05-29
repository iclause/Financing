package com.mga.financing.mvp.main.tab2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseFragment;

/**
 * Created by mga on 2018/4/25 16:07.
 */

public class Tab2Fragment extends BaseFragment {
    private WebView mWebview;
    private WebSettings mWebSettings;
    private TextView beginLoading;
    private TextView loading;
    private TextView endLoading;
    private TextView mtitle;

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
        beginLoading = (TextView) view.findViewById(R.id.text_beginLoading);
        endLoading = (TextView) view.findViewById(R.id.text_endLoading);
        loading = (TextView) view.findViewById(R.id.text_Loading);
        mtitle = (TextView) view.findViewById(R.id.title);


        mWebview = (WebView) view.findViewById(R.id.webview);

        mWebSettings = mWebview.getSettings();

        mWebview.loadUrl("http://www.baidu.com/");

        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //设置WebChromeClient类
        mWebview.setWebChromeClient(new WebChromeClient() {
            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("标题在这里");
                mtitle.setText(title);
            }

            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    loading.setText(progress);
                } else if (newProgress == 100) {
                    String progress = newProgress + "%";
                    loading.setText(progress);
                }
            }
        });

        //设置WebViewClient类
        mWebview.setWebViewClient(new WebViewClient() {
            //设置加载前的函数
             @Override
             public void onPageStarted(WebView view, String url, Bitmap favicon) {
                 System.out.println("开始加载了"); beginLoading.setText("开始加载了");
             }
             // /设置结束加载函数
             @Override public void onPageFinished(WebView view, String url) {
                 endLoading.setText("结束加载了");
             }
        });


    }

    //点击返回上一页面而不是退出浏览器
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()) {
//            mWebview.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event); }
    //销毁Webview
    @Override
    public void onDestroy() {
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory(); ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy(); mWebview = null; } super.onDestroy();
    }


    @Override
    public void onClick(View v) {

    }
}



