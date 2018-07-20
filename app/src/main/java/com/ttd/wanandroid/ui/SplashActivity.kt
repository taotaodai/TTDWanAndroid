package com.ttd.wanandroid.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseCompatActivity

/**
 * Created by wt on 2018/6/30.
 */
class SplashActivity : BaseCompatActivity(){
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent()
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },1000)
    }
}