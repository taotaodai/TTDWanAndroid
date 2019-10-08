package com.ttd.wanandroid.utils

import android.content.Context
import android.text.TextUtils
import com.thoughtworks.xstream.XStream
import com.ttd.sdk.utils.AppUtils
import com.ttd.sdk.utils.SpUtils
import com.ttd.wanandroid.R
import com.ttd.wanandroid.bean.SkinListBean
import com.ttd.wanandroid.constant.SharedPreferencesConstant
import skin.support.SkinCompatManager
import skin.support.content.res.SkinCompatUserThemeManager

/**
 * Created by wt on 2018/7/10.
 */
class ThemeUtils {
    companion object {
        var themes: MutableList<SkinListBean.Skin>? = null
//        fun getCurrentSkinFile(): String {
//            val skin = SkinCompatManager.getInstance().curSkinName
////            if(TextUtils.isEmpty(skin)){
////                return "默认"
////            }
//            return skin
//        }

        fun getCurrentSkin(): SkinListBean.Skin {
            if (themes == null){
                initThemes(AppUtils.getContext())
            }
            for (skin in themes!!) {
                if (skin.isCurrentSkin) {
                    return skin
                }
            }
            return themes!![0]
        }

        fun initThemes(context: Context): MutableList<SkinListBean.Skin>? {
            val xStream = XStream()
            xStream.processAnnotations(SkinListBean::class.java)
            val skins: SkinListBean = xStream.fromXML(context.assets.open("skin_data.xml")) as SkinListBean
            val themes = skins.skins
            val currentSkin = getTheme()
            for (item in themes) {
                if (currentSkin == item.alias) {
                    item.isCurrentSkin = true
                    break
                }
            }
            this.themes = themes
            return themes
        }

        fun isDefaultSkin(skin: SkinListBean.Skin): Boolean {
            if (TextUtils.isEmpty(skin.alias)) {
                return true
            }
            return false
        }

        fun setNightMode(isNightMode: Boolean) {
            SpUtils.putBoolean(AppUtils.getContext(), SharedPreferencesConstant.Key.NIGHT_MODE, isNightMode)
        }

        fun getNightMode(): Boolean {
            return SpUtils.getBoolean(AppUtils.getContext(), SharedPreferencesConstant.Key.NIGHT_MODE, false)
        }

        /**
         * 切换日夜间模式
         */
        fun setNightMode(){
            val isNightMode = ThemeUtils.getNightMode()
            if (isNightMode) {
                if (ThemeUtils.isDefaultTheme()) {
                    SkinCompatManager.getInstance().restoreDefaultTheme()
                    ThemeUtils.setNightMode(false)
                    setNightMode(false)
                } else {
                    SkinCompatManager.getInstance().loadSkin(ThemeUtils.getTheme(), object : SkinCompatManager.SkinLoaderListener {
                        override fun onStart() {
                        }
                        override fun onSuccess() {
                            ThemeUtils.setNightMode(false)
                            setNightMode(false)
                        }

                        override fun onFailed(errMsg: String) {
                        }
                    }, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN)
                }
            } else {
                SkinCompatManager.getInstance().loadSkin("night", object : SkinCompatManager.SkinLoaderListener {
                    override fun onStart() {
                    }

                    override fun onSuccess() {
                        ThemeUtils.setNightMode(true)
                        setNightMode(true)
                    }

                    override fun onFailed(errMsg: String) {
                    }
                }, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN)

            }
        }

        fun setTheme(theme:String){
            SpUtils.putString(AppUtils.getContext(),SharedPreferencesConstant.Key.CURRENT_THEME,theme)
        }

        fun getTheme():String{
            return SpUtils.getString(AppUtils.getContext(),SharedPreferencesConstant.Key.CURRENT_THEME,"")
        }

        fun isDefaultTheme():Boolean{
            return TextUtils.isEmpty(getTheme())
        }


        fun setDynamicResOfNight(){
            SkinCompatUserThemeManager.get().addColorState(R.color.md_background_color, "#FF1A1A1A")
            SkinCompatManager.getInstance().notifyUpdateSkin()
            SkinCompatUserThemeManager.get().apply()
        }

        fun setDynamicResOfDay(){
            SkinCompatUserThemeManager.get().addColorState(R.color.md_background_color, "#FFFFFFFF")
            SkinCompatManager.getInstance().notifyUpdateSkin()
            SkinCompatUserThemeManager.get().apply()
        }
    }


}