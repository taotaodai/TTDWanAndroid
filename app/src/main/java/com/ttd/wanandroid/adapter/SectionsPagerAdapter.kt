package com.ttd.wanandroid.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by wt on 2018/7/2.
 */
class SectionsPagerAdapter : FragmentPagerAdapter {
    var fragments: List<Fragment>
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    constructor(fm: FragmentManager?,fragments:List<Fragment>) : super(fm) {
        this.fragments = fragments
    }

}