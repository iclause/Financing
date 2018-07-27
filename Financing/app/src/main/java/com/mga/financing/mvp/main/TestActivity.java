package com.mga.financing.mvp.main;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.mga.financing.R;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;

/**
 * Created by mga on 2018/7/25 11:16.
 */

public class TestActivity extends BaseActivity {
    private Button aBt;
    private Button bBt;

    @Override
    protected int getLayoutId() {
        return R.layout.test_layout;
    }

    @Override
    protected void initView() {
        aBt=(Button)findViewById(R.id.abt);
        aBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this,"这是第一次初始化点击a",Toast.LENGTH_LONG).show();
            }
        });
        bBt=(Button)findViewById(R.id.bbt);
        bBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                testB();
//                Toast.makeText(TestActivity.this,"这是第一次初始化点击a",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void testB(){
        aBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this,"在testB中点击a",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
