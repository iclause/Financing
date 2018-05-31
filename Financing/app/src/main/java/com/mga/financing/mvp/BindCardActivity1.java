package com.mga.financing.mvp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.utils.RegexUtils;
import com.mga.financing.utils.UserInfoManager;

/**
 * Created by mga on 2018/5/30 14:45.
 */

public class BindCardActivity1 extends BaseActivity {
    private TextView nextTv;
    private EditText iciEt;
    private TextView supportBankTv;
    private TextView idTv;
    private TextView nameTv;
    private String account;
    private AlertDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_card1;
    }

    @Override
    protected void setupAppBar() {
        super.setupAppBar();
        mAppbarLeftBackIcon.setVisibility(View.VISIBLE);
        mAppBarTitle.setText(R.string.bind_card);
    }

    @Override
    protected void initView() {
        nameTv = (TextView) findViewById(R.id.card_name_tv);
        idTv = (TextView) findViewById(R.id.user_id_tv);
        supportBankTv = (TextView) findViewById(R.id.support_bank_tv);
        iciEt = (EditText) findViewById(R.id.input_card_id_et);
        nextTv = (TextView) findViewById(R.id.next_tv2);

        supportBankTv.setOnClickListener(this);
        nextTv.setOnClickListener(this);
        iciEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             if(RegexUtils.isBankCard(s.toString())){
                 nextTv.setEnabled(true);
                 nextTv.setBackgroundResource(R.color.orange_btn);
             }else{
                 nextTv.setEnabled(false);
                 nextTv.setBackgroundResource(R.color.grey_btn);
             }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initialize() {
        super.initialize();
        checkIsBindIdCard();
        showBasicUi();
    }

    private void showBasicUi() {
        account=UserInfoManager.readAccount(this);
//     显示个人身份信息
        nameTv.setText(UserInfoManager.readName(this,account));
        idTv.setText(UserInfoManager.readIdCard(this,account));
    }

    private void checkIsBindIdCard() {

        if (!RegexUtils.isIdCard(UserInfoManager.readIdCard(this, UserInfoManager.readAccount(this)))) {
            //跳至实名认证界面
            logi("checkIsnotBindIdCard");
            dialogShow();
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.next_tv2:
                toOtherLayout(BindCardActivity2.class,getBundle());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dialog!=null&&dialog.isShowing()) dialog.dismiss();
        checkIsBindIdCard();
        showBasicUi();
    }

    /**
     * 自定义布局
     * setView()只会覆盖AlertDialog的Title与Button之间的那部分，而setContentView()则会覆盖全部，
     * setContentView()必须放在show()的后面
     */
    private void dialogShow() {
        logi("idcard dialogShow");
        AlertDialog.Builder builder = new Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.m_alertdialog, null);
        TextView content = (TextView) v.findViewById(R.id.dialog_content);
        content.setText(getResources().getString(R.string.please_auth));
        Button btn_sure = (Button) v.findViewById(R.id.dialog_btn_sure);
        Button btn_cancel = (Button) v.findViewById(R.id.dialog_btn_cancel);
        builder.setCancelable(false);
        //builer.setView(v);//这里如果使用builer.setView(v)，自定义布局只会覆盖title和button之间的那部分
        dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(v);//自定义布局应该在这里添加，要在dialog.show()的后面
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        btn_sure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//               去实名
                toOtherLayout(BindIdCardActivity.class, getBundle());
                dialog.dismiss();

            }
        });

        btn_cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                暂不绑卡
                finish();
                dialog.dismiss();
            }
        });
    }
}
