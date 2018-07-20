package com.mga.financing.mvp.login;

/**
 * Created by mga on 2018/4/17 14:53.
 */

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.utils.ECCUtils;
import com.mga.financing.utils.PermissionUtils;

/**
 * 验证码登录界面
 */
public class LoginPasswordActivity extends BaseActivity implements LoginContact.View {
    private LoginPresenter mLoginPresenter;
    private EditText passwordEt;
    private TextView loginTv;
    private TextView toVfcLoginTv;
    private static final int REQUEST_PERMISSION_SEETING = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_password;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() {
        passwordEt = (EditText) findViewById(R.id.password_et);
        loginTv = (TextView) findViewById(R.id.login_tv);
        toVfcLoginTv = (TextView) findViewById(R.id.to_vfc_login_tv);
        loginTv.setOnClickListener(this);
        toVfcLoginTv.setOnClickListener(this);
        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ECCUtils.isPassword(s.toString())) {
                    loginTv.setBackground(getResources().getDrawable(R.drawable.shape_login_btn_ennable));
                    loginTv.setEnabled(true);
                } else {
                    loginTv.setBackground(getResources().getDrawable(R.drawable.shape_login_btn_unennable));
                    loginTv.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        mLoginPresenter = new LoginPresenter(this);
        return mLoginPresenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_tv:
                logi("login_tv_____onclick");
                requestPermission();
//                mLoginPresenter.login(getBundle().getString(BundleKeyConstant.PHONENUMBER),passwordEt.getText().toString());
                break;
            case R.id.to_vfc_login_tv:
                toOtherLayout(LoginVfcActivity.class, getBundle());
                break;
        }
    }


    @Override
    public String getPhoneNumber() {
        if (getBundle() == null) {
            return "";
        }
        return getBundle().getString(BundleKeyConstant.PHONENUMBER);
    }

    @Override
    public String getPassWord() {
        return passwordEt.getText().toString();
    }

    @Override
    public void countDown() {

    }
    private void requestPermission() {

        if (Build.VERSION.SDK_INT < 23) {
            mLoginPresenter.login(getBundle().getString(BundleKeyConstant.PHONENUMBER),passwordEt.getText().toString());
        } else {
            if (PermissionUtils.hasPermissions(this, Manifest.permission.READ_PHONE_STATE)) {
                Log.i(TAG, "requestPermission: hasPermissions ");
                    mLoginPresenter.login(getBundle().getString(BundleKeyConstant.PHONENUMBER),passwordEt.getText().toString());

            } else {
                Log.i(TAG, "requestPermission: noPermissions ");
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, PermissionUtils.REQUEST_PERMISSION_READ_PHONE_STATE);
            }
        }

    }

    @TargetApi(VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions 权限处理请求结果
        Log.i(TAG, "onRequestPermissionsResult:" + requestCode);

        if(requestCode==PermissionUtils.REQUEST_PERMISSION_READ_PHONE_STATE){
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_GRANTED) {
                    mLoginPresenter.login(getBundle().getString(BundleKeyConstant.PHONENUMBER),passwordEt.getText().toString());


            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                    Log.e(TAG, "返回的是true");
                    requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, PermissionUtils.REQUEST_PERMISSION_READ_PHONE_STATE);
                } else {
                    Log.e(TAG, "返回的是false");
                    showPermissionDialog(this.getResources().getString(R.string.necessary_permissions));
                }
            }
        }




    }
    private void showPermissionDialog(String content) {

        new AlertDialog.Builder(this)
                .setMessage(content)
                .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "PositiveButton_onclick");
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SEETING);

                    }
                })
                .setTitle(this.getResources().getString(R.string.request_permissions))
                .show();
    }
}
