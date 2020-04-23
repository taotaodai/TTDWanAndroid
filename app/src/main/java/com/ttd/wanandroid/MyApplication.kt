package com.ttd.wanandroid

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.appcompat.app.AppCompatDelegate
import com.ttd.sdk.GlobalApplication
import com.ttd.wanandroid.widget.skin.support.SkinCircleImageViewInflater
import skin.support.SkinCompatManager
import skin.support.constraint.app.SkinConstraintViewInflater
import skin.support.design.app.SkinMaterialViewInflater
import skin.support.flycotablayout.app.SkinFlycoTabLayoutInflater


/**
 * Created by wt on 2018/7/2.
 */
class MyApplication:GlobalApplication(){
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()
        initSkinSupport()
    }

    private fun initSkinSupport() {
        SkinCompatManager.withoutActivity(this)
                .addInflater(SkinMaterialViewInflater())    // material design
                .addInflater(SkinFlycoTabLayoutInflater())  // H07000223/FlycoTabLayout
                .addInflater(SkinConstraintViewInflater())  // ConstraintLayout
                .addInflater(SkinCircleImageViewInflater())
                .setSkinStatusBarColorEnable(false)              // 关闭状态栏换肤
                //                .setSkinWindowBackgroundEnable(false)           // 关闭windowBackground换肤
//                                .setSkinAllActivityEnable(true)                // true: 默认所有的Activity都换肤; false: 只有实现SkinCompatSupportable接口的Activity换肤
                .loadSkin()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

    }
}