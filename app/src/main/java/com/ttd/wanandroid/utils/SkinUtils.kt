package com.ttd.wanandroid.utils

import android.content.Context
import android.text.TextUtils
import com.thoughtworks.xstream.XStream
import com.ttd.wanandroid.bean.SkinListBean
import skin.support.SkinCompatManager

/**
 * Created by wt on 2018/7/10.
 */
class SkinUtils {
    companion object {
        fun getCurrentSkinFile(): String {
            val skin = SkinCompatManager.getInstance().curSkinName
//            if(TextUtils.isEmpty(skin)){
//                return "默认"
//            }
            return skin
        }

        fun getCurrentSkin(skins: MutableList<SkinListBean.Skin>): SkinListBean.Skin {
            for (skin in skins) {
                if(skin.isCurrentSkin){
                    return skin
                }
            }
            return skins[0]
        }

        fun initThemes(context: Context): MutableList<SkinListBean.Skin>? {
            val xStream = XStream()
            xStream.processAnnotations(SkinListBean::class.java)
            val skins: SkinListBean = xStream.fromXML(context.assets.open("skin_data.xml")) as SkinListBean
            val themes = skins.skins
            val currentSkin = SkinUtils.getCurrentSkinFile()
            for (item in themes) {
                if (currentSkin == item.file) {
                    item.isCurrentSkin = true
                    break
                }
            }
            return themes
        }

        fun isDefaultSkin(skin: String): Boolean {
            if (TextUtils.isEmpty(skin)) {
                return true
            }
            return false
        }
    }


}