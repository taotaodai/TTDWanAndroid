package com.ttd.wanandroid.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

/**
 * Created by wt on 2018/6/30.
 */
class SplashActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent()
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
        },1000)
    }
}