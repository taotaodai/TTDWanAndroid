package com.ttd.wanandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wt on 2017/5/25.
 */

public class AttendanceFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private String[] title;

    public AttendanceFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] title) {
        super(fm);
        this.fragments = fragments;
        this.title=title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
