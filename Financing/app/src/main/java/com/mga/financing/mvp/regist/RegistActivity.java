package com.mga.financing.mvp.regist;

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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.constant.BundleKeyConstant;
import com.mga.financing.mvp.login.LoginContact;
import com.mga.financing.mvp.login.LoginPresenter;
import com.mga.financing.utils.ECCUtils;
import com.mga.financing.utils.PermissionUtils;

import static com.mga.financing.mvp.login.LoginPasswordActivity.REQUEST_PERMISSION_SEETING;

/**
 * Created by mga on 2018/4/10 16:25.
 */

public class RegistActivity extends BaseActivity implements LoginContact.View {
    private LoginPresenter mLoginPresenter;
    private TextView registTv;
    private EditText vfcEt;
    private EditText registPasswordEt;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView() {
        registTv = (TextView) findViewById(R.id.regist_tv);
        registTv.setOnClickListener(this);
        vfcEt = (EditText) findViewById(R.id.regist_vfc_et);
        registPasswordEt = (EditText) findViewById(R.id.regist_password_et);
    }

    @Override
    protected BasePresenter createPresenter() {
        mLoginPresenter = new LoginPresenter(this);
        return mLoginPresenter;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.regist_tv:
                if (!ECCUtils.isVertificationCode(vfcEt.getText().toString())) {
                    showToast("请输入有效验证码");
                    return;
                }
                if (!ECCUtils.isPassword(registPasswordEt.getText().toString())) {
                    showToast("请输入有效密码");
                    return;
                }
                requestPermission();

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
        return registPasswordEt.getText().toString();
    }

    @Override
    public void countDown() {

    }

    private void requestPermission() {

        if (Build.VERSION.SDK_INT < 23) {
            mLoginPresenter.regist(getBundle().getString(BundleKeyConstant.PHONENUMBER),
                    registPasswordEt.getText().toString(), vfcEt.getText().toString());
        } else {
            if (PermissionUtils.hasPermissions(this, Manifest.permission.READ_PHONE_STATE)) {
                Log.i(TAG, "requestPermission: hasPermissions ");
                mLoginPresenter.regist(getBundle().getString(BundleKeyConstant.PHONENUMBER),
                        registPasswordEt.getText().toString(), vfcEt.getText().toString());

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

        if(requestCode== PermissionUtils.REQUEST_PERMISSION_READ_PHONE_STATE){
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_GRANTED) {
                mLoginPresenter.regist(getBundle().getString(BundleKeyConstant.PHONENUMBER),
                        registPasswordEt.getText().toString(), vfcEt.getText().toString());


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
