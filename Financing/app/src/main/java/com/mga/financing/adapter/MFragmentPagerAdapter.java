package com.mga.financing.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mfragments;

    public MFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mfragments = fragments;
    }

    @Override
    public int getCount() {
        if (mfragments != null) {
            return mfragments.size();
        } else
            return 0;
    }


    @Override
    public Fragment getItem(int position) {
        return mfragments.get(position);
    }

}
