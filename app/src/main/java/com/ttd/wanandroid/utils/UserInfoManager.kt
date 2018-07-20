package com.ttd.wanandroid.utils

import com.ttd.sdk.utils.AppUtils
import com.ttd.sdk.utils.SpUtils
import com.ttd.wanandroid.constant.SharedPreferencesConstant

/**
 * Created by wt on 2018/7/16.
 */
class UserInfoManager{
    companion object {
        fun isLogined(): Boolean{
            val cookie = SpUtils.getString(AppUtils.getContext(),SharedPreferencesConstant.Key.COOKIE,"")
            if (cookie.contains("loginUserName") && cookie.contains("loginUserPassword")){
                return true
            }
            return false
        }
    }
}