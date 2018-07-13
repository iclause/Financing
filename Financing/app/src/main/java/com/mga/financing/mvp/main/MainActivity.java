package com.mga.financing.mvp.main;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.adapter.MFragmentPagerAdapter;
import com.mga.financing.bean.LoginBean;
import com.mga.financing.base.presenter.BasePresenter;
import com.mga.financing.base.view.BaseActivity;
import com.mga.financing.mvp.main.tab0.Tab0Fragment;
import com.mga.financing.mvp.main.tab1.Tab1Fragment;
import com.mga.financing.mvp.main.tab2.Tab2Fragment;
import com.mga.financing.mvp.main.tab3.Tab3Fragment;
import com.mga.financing.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

import static com.mga.financing.R.drawable.tab1_enable_iv;
import static com.mga.financing.R.drawable.tab1_unable_iv;
import static com.mga.financing.R.drawable.tab3_enable_iv;
import static com.mga.financing.R.drawable.tab3_unable_iv;

public class MainActivity extends BaseActivity implements MainContact.View {

    private RelativeLayout tab0Rl;
    private ImageView tab0IV;
    private TextView tab0TV;
    private RelativeLayout tab1Rl;
    private ImageView tab1IV;
    private TextView tab1TV;
    private RelativeLayout tab2Rl;
    private ImageView tab2IV;
    private TextView tab2TV;
    private RelativeLayout tab3Rl;
    private ImageView tab3IV;
    private TextView tab3TV;
    private ViewPager viewPager;
    private List<Fragment> mFragments;
    private Tab0Fragment tab0Fragment;
    private Tab1Fragment tab1Fragment;
    private Tab2Fragment tab2Fragment;
    private Tab3Fragment tab3Fragment;
    private MFragmentPagerAdapter mFragmentPagerAdapter;
    private int currentIndex = 0;
    private int[] tab_unselected_images = {R.drawable.tab0_unable_iv,
            tab1_unable_iv, R.drawable.tab2_unable_iv, tab3_unable_iv
    };
    private int[] tab_selected_images = {R.drawable.tab0_enable_iv,
            tab1_enable_iv, R.drawable.tab2_enable_iv, tab3_enable_iv
    };
    private ImageView[] tab_Ivs = new ImageView[4];
    private TextView[] tab_Tvs = new TextView[4];
    private boolean isExit = false;
    private long exitTime=0;
    int i=1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tab0Rl = (RelativeLayout) findViewById(R.id.tab_0_rl);
        tab0IV = (ImageView) findViewById(R.id.tab_0_iv);
        tab0TV = (TextView) findViewById(R.id.tab_0_tv);
        tab0Rl.setOnClickListener(this);
        tab1Rl = (RelativeLayout) findViewById(R.id.tab_1_rl);
        tab1IV = (ImageView) findViewById(R.id.tab_1_iv);
        tab1TV = (TextView) findViewById(R.id.tab_1_tv);
        tab1Rl.setOnClickListener(this);
        tab2Rl = (RelativeLayout) findViewById(R.id.tab_2_rl);
        tab2IV = (ImageView) findViewById(R.id.tab_2_iv);
        tab2TV = (TextView) findViewById(R.id.tab_2_tv);
        tab2Rl.setOnClickListener(this);
        tab3Rl = (RelativeLayout) findViewById(R.id.tab_3_rl);
        tab3IV = (ImageView) findViewById(R.id.tab_3_iv);
        tab3TV = (TextView) findViewById(R.id.tab_3_tv);
        tab3Rl.setOnClickListener(this);
        tab_Ivs[0] = tab0IV;
        tab_Ivs[1] = tab1IV;
        tab_Ivs[2] = tab2IV;
        tab_Ivs[3] = tab3IV;
        tab_Tvs[0] = tab0TV;
        tab_Tvs[1] = tab1TV;
        tab_Tvs[2] = tab2TV;
        tab_Tvs[3] = tab3TV;
        initFragments();
        viewPager = (ViewPager) findViewById(R.id.container);
        mFragmentPagerAdapter = new MFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(mFragmentPagerAdapter);
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setOldMenu(currentIndex);
                currentIndex = position;

                setNewMenu(currentIndex);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        test();
        test();
    }

    private void test() {
        List<LoginBean> list=new ArrayList<>();
        if(list!=null){
            logi("list!=null");
            logi("listsize0="+list.size());

        }else{
            logi("list==null");
        }
        int j=i++;
        logi("j="+j);

    }

    private void initFragments() {
        if (mFragments == null) {
            mFragments = new ArrayList<Fragment>();
        }
        if (tab0Fragment == null) {
            tab0Fragment = new Tab0Fragment();
        }
        if (tab1Fragment == null) {
            tab1Fragment = new Tab1Fragment();
        }
        if (tab2Fragment == null) {
            tab2Fragment = new Tab2Fragment();
        }
        if (tab3Fragment == null) {
            tab3Fragment = new Tab3Fragment();
        }
        mFragments.add(tab0Fragment);
        mFragments.add(tab1Fragment);
        mFragments.add(tab2Fragment);
        mFragments.add(tab3Fragment);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tab_0_rl:
                gotoTab(0);
                break;
            case R.id.tab_1_rl:
                gotoTab(1);
                break;
            case R.id.tab_2_rl:
                gotoTab(2);
                break;
            case R.id.tab_3_rl:
                gotoTab(3);
                break;

        }
    }

    private void setOldMenu(int hitItem) {
        tab_Ivs[hitItem].setImageResource(tab_unselected_images[hitItem]);
        tab_Tvs[hitItem].setTextColor(getResources().getColor(R.color.grey_middle1_text));
        mFragments.get(currentIndex).onHiddenChanged(true);
        logi("设置未选中项item =" + hitItem + " iv=" + tab_Ivs[hitItem].toString() + "tv" + tab_Tvs[hitItem].toString());
    }

    private void setNewMenu(int hitItem) {
        tab_Ivs[hitItem].setImageResource(tab_selected_images[hitItem]);
        tab_Tvs[hitItem].setTextColor(getResources().getColor(R.color.orange_loginbtn_enable));
        mFragments.get(currentIndex).onHiddenChanged(false);
        logi("设置选中项item =" + hitItem + " iv=" + tab_Ivs[hitItem].toString() + "tv" + tab_Tvs[hitItem].toString());
    }

    private void gotoTab(int item) {

        logi("goToTab:" + currentIndex);
        if (currentIndex == item) {
            return;
        }
        setOldMenu(currentIndex);
        currentIndex = item;
        showTab(currentIndex); // 显示目标fragment tab
        setNewMenu(currentIndex);
//        showTitle(currentIndex);
    }

    private void showTab(int idx) {
        viewPager.setCurrentItem(idx, false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(this);
        }
    }
}
