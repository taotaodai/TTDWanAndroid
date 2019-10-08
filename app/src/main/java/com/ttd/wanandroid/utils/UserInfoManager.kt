package com.ttd.wanandroid.utils

import com.ttd.sdk.utils.AppUtils
import com.ttd.sdk.utils.SpUtils
import com.ttd.wanandroid.constant.SharedPreferencesConstant

/**
 * Created by wt on 2018/7/16.
 */
class UserInfoManager {
    companion object {
        fun isLoggedIn(): Boolean {
            val cookie = getCookie()
            if (cookie.contains("loginUserName") && cookie.contains("loginUserPassword")) {
                return true
            }
            return false
        }

        fun getUserName(): String? {
            val cookie = getCookie()
            val cookies = cookie.split(";")
            val map: MutableMap<String, String> = HashMap()
            for (c in cookies) {
                val pair = c.split("=")
                if (pair.size == 2) {
                    map[pair[0]] = pair[1]
                }
            }

            return if (map.containsKey("loginUserName")) {
                map["loginUserName"]
            } else {
                ""
            }
        }

        fun logout(){
            cleanCookie()
        }

        private fun getCookie(): String {
            return SpUtils.getString(AppUtils.getContext(), SharedPreferencesConstant.Key.COOKIE, "")
        }

        private fun cleanCookie(){
            SpUtils.putString(AppUtils.getContext(), SharedPreferencesConstant.Key.COOKIE,"")
        }
    }
}