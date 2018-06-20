package com.mga.financing.base.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.ui.CustomDialog;
import com.mga.financing.utils.AppManager;
import com.mga.financing.utils.ToastUtil;


/**
 * Created by mga on 2016/7/6.
 */
public abstract class BaseActivity<A extends BaseActivity, V extends BaseView, T extends BasePresenter<V>> extends AppCompatActivity implements View.OnClickListener, BaseView {
    //    private int netMobile;//网络类型
    //    private Toolbar toolbar;
//    private ImageView leftArrIV;
//    private TextView titleToolbar;
//    private String toolbar_name;
//    private ImageView rightArrIV;
    protected final String TAG = getClass().getSimpleName();
    protected String BASETAG = BaseActivity.class.getSimpleName();
    protected ActionBar mAppBar;
    protected TextView mAppBarTitle;
    protected ImageView mAppBarTitleIcon;
    protected ImageView mAppBarRightIcon;
    protected TextView mAppBarRightText;
    protected ImageView mAppBarRedIcon;//抽屉消息提醒小圆点
    protected ImageView mAppbarLeftBackIcon;//返回按钮
    protected Toolbar mToolbar;
    protected T mPresenter;
    //    protected A mActivity;
    private boolean mToolbarVisible = true;
    private View mAllToolbarlayout;
    protected String mPageName;
    private CustomDialog mCustomDialog;
    protected Bundle mBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        JLog.d(TAG, "onCreate");
        Log.i(TAG, "onCreate: ");
        initialize();

    }


    /**
     * 获得页面布局Id
     *
     * @return 布局Id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    protected abstract T createPresenter();

    protected void setupAppBar() {
        mToolbar = (Toolbar) findViewById(R.id.mtoolbar);
        if (mToolbar == null) return;
        mAllToolbarlayout = (View) findViewById(R.id.toolbarlayout);

//        JLog.d(TAG, "mToolbarVisible=" + mToolbarVisible+"mAllToolbarlayoutId="+mAllToolbarlayout.getId());
        if (!mToolbarVisible) {
            mAllToolbarlayout.setVisibility(View.GONE);
//            JLog.d(TAG, "set no toolbar");
            return;
        }
        mAppBarTitle = (TextView) findViewById(R.id.main_title);
        mAppBarRightIcon = (ImageView) findViewById(R.id.right_icon);
        mAppBarTitleIcon = (ImageView) findViewById(R.id.main_title_iv);
        mAppBarRightText = (TextView) findViewById(R.id.right_text);
        mAppBarRedIcon = (ImageView) findViewById(R.id.deliver_red_icon);
        mAppbarLeftBackIcon = (ImageView) findViewById(R.id.left_back_icon);
        mAppBarTitle.setOnClickListener(this);
        mAppBarRightIcon.setOnClickListener(this);
        mAppBarTitleIcon.setOnClickListener(this);
        mAppBarRightText.setOnClickListener(this);
        mAppbarLeftBackIcon.setOnClickListener(this);
        if (mToolbar == null) {
            return;
        }
        setSupportActionBar(mToolbar);
        mAppBar = getSupportActionBar();
    }

    @Override
    public void toOtherLayout(Class aClass, Bundle bundle) {
        logi("tootherlayout other :" + aClass.getSimpleName().toString());
        Intent intent = new Intent(this, aClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);

    }

    protected void initialize() {
        setContentView(getLayoutId());
        getBundle();
        setupAppBar();
        initView();
//        this.mActivity=saySonActivity();
        AppManager.addActivity(this);
//        initData();
        //创建presenter
        this.mPresenter = createPresenter();
//        关联view
        if (mPresenter != null) {
            mPresenter.attach((V) this);

        }
        //友盟
//        MobclickAgent.setDebugMode(true);


        mPageName = getClass().getSimpleName();
//        JLog.d(TAG,"mpagename="+mPageName);
    }

    protected void showNoToolbar() {
//        JLog.d(TAG, "showNoToolbar");
        mToolbarVisible = false;
    }

    protected void showFullScreenIfSupport() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        JLog.d(TAG, "--->onDestroy");
        Log.i(TAG, "onDestroy: ");
        AppManager.finishActivity(this);
        if (mPresenter != null) {
            mPresenter.detach();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        JLog.d(TAG, "--->onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        JLog.d(TAG, "--->onStop");
        Log.i(TAG, "onStop: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
//        JLog.d(TAG, "--->onResume");
//        JLog.d(TAG, "--->onResume  mPageName="+mPageName);

//        MobclickAgent.onPageStart(mPageName);
//        MobclickAgent.onResume(this);
        Log.i(TAG, "onResume: ");
        if (mCustomDialog != null) {
            logi("mcustomprogress ：" + mCustomDialog.isShowing());
        } else {
            logi("mcustomprogress==null");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back_icon:
                AppManager.finishActivity(this);
                break;
            default:

                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
//        JLog.d(TAG, "--->onPause");
//        JPushInterface.onPause(this);
//        MobclickAgent.onPageEnd(mPageName);
//        MobclickAgent.onPause(this);
    }

    protected Bundle getBundle() {
        mBundle = getIntent().getExtras();
        if (mBundle != null && mBundle.toString() != null) {
            logi("getBundle = " + mBundle.toString());
        }
        return mBundle;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("按下了back键   onKeyDown()");
            finish();
            return false;
        } else {

            return super.onKeyDown(keyCode, event);
        }
    }


    @Override
    public void showDialog(boolean isshow, String msg) {
        if (msg == null) {
            msg = "";
        }
        if (mCustomDialog == null) {
            mCustomDialog = new CustomDialog(this, msg);
        }
        if (isshow) {
            if (mCustomDialog == null || !mCustomDialog.isShowing()) {
                mCustomDialog.show();
                logi("mCustomDialog.show()");
            }
        } else {
            if (mCustomDialog != null) {
                mCustomDialog.dismiss();
                mCustomDialog = null;
                logi("mCustomDialog.dismiss()   mCustomDialog=null;");
            }
        }
    }


    @Override
    public void showToast(String msg) {
        ToastUtil.show(this, msg);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showFailReason(int errcode, Intent intent) {

    }


    public void logi(String str) {
        Log.i(TAG, str);
    }

    public void loge(String str) {
        Log.e(TAG, str);
    }
}

