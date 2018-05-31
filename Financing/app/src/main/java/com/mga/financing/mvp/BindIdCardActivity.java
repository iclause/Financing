package com.mga.financing.mvp;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.utils.RegexUtils;
import com.mga.financing.utils.UserInfoManager;

/**
 * Created by mga on 2018/5/31 15:44.
 */

public class BindIdCardActivity extends BaseActivity implements OnCheckedChangeListener {

    private EditText idEt;
    private EditText nameEt;
    private CheckBox cb;
    private TextView gwpTv;
    private TextView subBtn;

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText(getResources().getString(R.string.auth_title));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_idcard;
    }

    @Override
    protected void initView() {
        nameEt=(EditText)findViewById(R.id.input_name_et);
        idEt=(EditText)findViewById(R.id.input_id_et);
        cb=(CheckBox)findViewById(R.id.checkbox);
        subBtn=(TextView)findViewById(R.id.submit_tv);
        gwpTv=(TextView)findViewById(R.id.golden_wallet_protocal_tv);
        gwpTv.setOnClickListener(this);
        subBtn.setOnClickListener(this);
        cb.setOnCheckedChangeListener(this);


    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            subBtn.setBackgroundResource(R.color.orange_btn);
            subBtn.setEnabled(true);
        }else {
            subBtn.setBackgroundResource(R.color.grey_btn);
            subBtn.setEnabled(false);

        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.submit_tv:
                if(TextUtils.isEmpty(nameEt.getText().toString())){
                    showToast("请输入有效的姓名");
                    return;
                }
                if(!RegexUtils.isIdCard(idEt.getText().toString())){
                    showToast("请输入有效的身份证号");
                    return;
                }

                //模拟校验完毕,储存个人身份信息
                if(TextUtils.isEmpty(UserInfoManager.readAccount(this))) {
                    showToast("请登录账号");
                    return;
                }
                UserInfoManager.saveIdCard(this,UserInfoManager.readAccount(this),idEt.getText().toString());
                UserInfoManager.saveName(this,UserInfoManager.readAccount(this),nameEt.getText().toString());

                finish();

                break;
            case R.id.golden_wallet_protocal_tv:

                break;
        }
    }
}
