package com.ttd.wanandroid.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.IdRes
import com.ttd.sdk.wrappers.statusbar.ImmersionBarImp
import com.ttd.sdk.wrappers.statusbar.StatusBarOptions
import me.yokeyword.fragmentation.SupportActivity

/**
 * Created by wt on 2018/6/30.
 */
open class BaseActivity : SupportActivity() {
    lateinit var immersionBarImp: ImmersionBarImp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        initStatusBar()
    }

    protected open fun initStatusBar() {
        immersionBarImp = ImmersionBarImp()
        val options: StatusBarOptions = StatusBarOptions.Builder()
//                .setStatusBarColor(R.color.colorPrimary)
                .build()
        immersionBarImp.initImmersionBar(this, options)
    }

    open fun initStatusBarByView(@IdRes id: Int) {
        immersionBarImp = ImmersionBarImp()
        val options: StatusBarOptions = StatusBarOptions.Builder()
                .setStatusBarViewId(id)
                .build()
        immersionBarImp.initImmersionBar(this, options)

    }

    open fun initStatusBar(@IdRes titleBarId: Int){
        immersionBarImp = ImmersionBarImp()
        val options: StatusBarOptions = StatusBarOptions.Builder()
                .setTitleBarId(titleBarId)
                .build()
        immersionBarImp.initImmersionBar(this, options)
    }

    open fun initViews(){

    }
}