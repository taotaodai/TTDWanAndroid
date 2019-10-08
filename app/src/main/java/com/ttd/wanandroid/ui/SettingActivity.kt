package com.ttd.wanandroid.ui

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseCompatActivity
import com.ttd.wanandroid.event.BaseEvent
import com.ttd.wanandroid.event.ThemeEvent
import com.ttd.wanandroid.event.UserInfoEvent
import com.ttd.wanandroid.utils.GlideCacheUtil
import com.ttd.wanandroid.utils.ThemeUtils
import com.ttd.wanandroid.utils.UserInfoManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * author wt
 * createDate 2018/8/6
 */
class SettingActivity : BaseCompatActivity() {
    var vTheme: View? = null
    var vCurrentTheme: View? = null
    var vCleanCache: View? = null
    var tvCacheSize: TextView? = null
    var vQuit: View? = null

    override fun initView(savedInstanceState: Bundle?) {
        initTitleBar(tbSetting, "设置")
        vTheme = findViewById(R.id.v_change_theme)
        vCurrentTheme = findViewById(R.id.v_current_theme)
        vCleanCache = findViewById(R.id.v_clean_cache)
        tvCacheSize = findViewById(R.id.tv_cache_size)
        vQuit = findViewById(R.id.v_quit)

        vTheme!!.setOnClickListener {
            startActivity(ThemeSettingActivity::class.java)
        }
        vCleanCache!!.setOnClickListener {
            GlideCacheUtil.getInstance().clearImageAllCache(this)
            showCacheSize()
        }
        if (UserInfoManager.isLoggedIn()) {
            vQuit!!.visibility = View.VISIBLE
            vQuit!!.setOnClickListener {
                MaterialDialog.Builder(this)
                        .title("退出登录")
                        .content("是否退出登录？")
                        .positiveText("确定")
                        .onPositive { _, _ ->
                            UserInfoManager.logout()
                            EventBus.getDefault().post(UserInfoEvent(BaseEvent.CODE_REFRESH))
                            finish()
                        }
                        .negativeText("取消")
                        .show()

            }
        } else {
            vQuit!!.visibility = View.GONE
        }


        showCurrentTheme()
        showCacheSize()
    }

    var tbSetting: Toolbar? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initStatusBar() {
        tbSetting = findViewById(R.id.tb_setting)
        initStatusBar(tbSetting)
    }

    /**
     * 当前主题
     */
    fun showCurrentTheme() {
        vCurrentTheme!!.setBackgroundColor(Color.parseColor(ThemeUtils.getCurrentSkin().color))
    }

    fun showCacheSize() {
        tvCacheSize!!.text = GlideCacheUtil.getInstance().getCacheSize(this)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun themeEvent(event: ThemeEvent) {
        when (event.code) {
            ThemeEvent.CODE_REFRESH -> {
                showCurrentTheme()
            }
        }
    }
}