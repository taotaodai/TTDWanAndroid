package com.ttd.wanandroid.ui.fragment

import android.os.Bundle
import android.preference.PreferenceFragment
import com.ttd.wanandroid.R

/**
 * author wt
 * createDate 2018/8/6
 */
class SettingFragment:PreferenceFragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.setting_preferences)
    }
}