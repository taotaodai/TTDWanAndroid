package com.ttd.wanandroid

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.ttd.sdk.GlobalApplication
import com.ttd.wanandroid.widget.skin.support.SkinCircleImageViewInflater
import skin.support.flycotablayout.app.SkinFlycoTabLayoutInflater
import skin.support.design.app.SkinMaterialViewInflater
import skin.support.SkinCompatManager
import skin.support.app.SkinCompatViewInflater
import skin.support.constraint.app.SkinConstraintViewInflater


/**
 * Created by wt on 2018/7/2.
 */
class MyApplication:GlobalApplication(){
    override fun onCreate() {
        super.onCreate()
        initSkinSupport(this)
    }

    private fun initSkinSupport(application: Application) {
        SkinCompatManager.withoutActivity(this)
                .addInflater(SkinMaterialViewInflater())    // material design
                .addInflater(SkinFlycoTabLayoutInflater())  // H07000223/FlycoTabLayout
                .addInflater(SkinConstraintViewInflater())  // ConstraintLayout
                .addInflater(SkinCircleImageViewInflater())
                .setSkinStatusBarColorEnable(false)              // 关闭状态栏换肤
                //                .setSkinWindowBackgroundEnable(false)           // 关闭windowBackground换肤
                //                .setSkinAllActivityEnable(false)                // true: 默认所有的Activity都换肤; false: 只有实现SkinCompatSupportable接口的Activity换肤
                .loadSkin()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

    }
}