package com.mga.financing.base.view;import android.content.Context;import android.content.Intent;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v4.app.Fragment;import android.util.Log;import android.view.LayoutInflater;import android.view.View;import android.view.View.OnClickListener;import android.view.ViewGroup;import com.mga.financing.base.presenter.BasePresenter;import com.mga.financing.ui.CustomDialog;import com.mga.financing.utils.NetCodeShowUtil;import com.mga.financing.utils.ToastUtil;/** * @mga */public abstract class BaseFragment<V extends BaseView, T extends BasePresenter<V>> extends Fragment implements BaseView,OnClickListener{    private final String TAG = this.getClass().getSimpleName();    protected T mPresenter;    protected Bundle mBundle;    private CustomDialog mCustomDialog;    @Override    public void onAttach(Context context) {        super.onAttach(context);        logi( TAG+"------>onAttach");    }    @Override    public void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        logi( TAG+"------>onCreate");    }    @Override    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        logi( TAG+"------>onCreateView");        View view = inflater.inflate(bindLayout(), container, false);        //注入P层        this.mPresenter = createPresenter();        if (mPresenter != null) {            mPresenter.attach((V) this);        }        return view;    }    @Override    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        initView(view, savedInstanceState);    }    protected Bundle getBundle() {        if(getArguments()!=null){            mBundle=getArguments();        }        return mBundle;    }    @Override    public void onResume() {        super.onResume();        logi(TAG+"------>onResume");//        MobclickAgent.onPageStart(mPageName);    }    @Override    public void onPause() {        super.onPause();        logi(TAG+"------>onPause");//        MobclickAgent.onPageEnd(mPageName);    }    @Override    public void onDestroy() {        super.onDestroy();        logi(TAG+"------>onDestroy");        if (mPresenter != null) {            mPresenter.detach();            mPresenter=null;            System.gc();        }    }    @Override    public void onDetach() {        super.onDetach();    }    protected abstract int bindLayout();    protected abstract T createPresenter();    protected abstract void initView(View view, Bundle savedInstanceState);    protected void logi(String msg) {        Log.i(TAG, msg);    }    protected void loge(String msg) {        Log.e(TAG, msg);    }    @Override    public void showToast(String msg) {        ToastUtil.show(getActivity(), msg);    }    @Override    public void showFailReason(int errcode, Intent intent) {        NetCodeShowUtil.showAndNeJump(getContext(), errcode, intent);    }    @Override    public void showDialog(boolean isshow, String msg) {        if (msg == null) {            msg = "";        }        if (mCustomDialog == null) {            mCustomDialog = new CustomDialog(getContext(), msg);        }        if (isshow) {            if (mCustomDialog == null || !mCustomDialog.isShowing()) {                mCustomDialog.show();                logi("mCustomDialog.show()");            }        } else {            if (mCustomDialog != null) {                mCustomDialog.dismiss();                mCustomDialog = null;                logi("mCustomDialog.dismiss()   mCustomDialog=null;");            }        }    }    @Override    public Context getContext() {        return super.getContext();    }    @Override    public void toOtherLayout(Class aClass, Bundle bundle) {        logi("tootherlayout other :" + aClass.getSimpleName().toString());        Intent intent = new Intent(getActivity(), aClass);        if (bundle != null) {            intent.putExtras(bundle);        }        startActivity(intent);    }}