package com.ttd.wanandroid.utils

import com.ttd.sdk.utils.AppUtils
import com.ttd.sdk.utils.SpUtils
import com.ttd.wanandroid.constant.SharedPreferencesConstant

/**
 * author wt
 * createDate 2018/8/7
 */
class SpManager {
    companion object {
        fun setSaveMode(isSaveMode: Boolean) {
            SpUtils.putBoolean(AppUtils.getContext(), SharedPreferencesConstant.Key.SAVE_MODE, isSaveMode)
        }

        fun getSaveMode(): Boolean {
            return SpUtils.getBoolean(AppUtils.getContext(), SharedPreferencesConstant.Key.SAVE_MODE, false)
        }
    }
}