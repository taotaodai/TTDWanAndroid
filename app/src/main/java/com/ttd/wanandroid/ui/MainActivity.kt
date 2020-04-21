package com.ttd.wanandroid.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseCompatActivity
import com.ttd.wanandroid.event.BaseEvent
import com.ttd.wanandroid.event.NavigationViewEvent
import com.ttd.wanandroid.event.UserInfoEvent
import com.ttd.wanandroid.ui.fragment.ArchitectureFragment
import com.ttd.wanandroid.ui.fragment.HomeFragment
import com.ttd.wanandroid.ui.fragment.NavigationFragment
import com.ttd.wanandroid.ui.fragment.ProjectFragment
import com.ttd.wanandroid.utils.BottomNavigationViewHelper
import com.ttd.wanandroid.utils.ThemeUtils
import com.ttd.wanandroid.utils.UserInfoManager
import me.yokeyword.fragmentation.SupportFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by wt on 2018/6/30.
 */
class MainActivity : BaseCompatActivity(), BottomNavigationBar.OnTabSelectedListener {

    private var fragments: List<SupportFragment> = listOf()
    private lateinit var dlMain: DrawerLayout
    private lateinit var bnvMain: BottomNavigationView
    private lateinit var nvMain: NavigationView
    private var tvUserName: TextView? = null
    var ivHead: ImageView? = null
    private val FIRST = 0
    private val SECOND = 1
    private val THIRD = 2
    private val FOURTH = 3

    override fun initView(savedInstanceState: Bundle?) {
//        EventBus.getDefault().register(this)
        dlMain = findViewById(R.id.dl_main)

        initNavigationView()
        initBottomNavigation()
        initFragments()

    }

    private fun initNavigationView() {
        nvMain = findViewById(R.id.nv_menu)
        nvMain.setNavigationItemSelectedListener { item ->
            closeNavigationView()
            when (item.itemId) {
                R.id.menu_item_collect -> {
                    if (UserInfoManager.isLoggedIn()) {
                        startActivity(CollectionArticlesActivity::class.java)
                    } else {
                        startActivity(LoginActivity::class.java)
                    }
                }
            //夜间模式
                R.id.menu_item_night -> {
                    setNightMode()
                }
                R.id.menu_item_setting -> {
                    startActivity(SettingActivity::class.java)
                }
//                R.id.menu_item_quit -> {
//                    UserInfoManager.logout()
//                    showUserInfo()
//                }
            }
            true
        }
        val header = nvMain.getHeaderView(0)
        tvUserName = header.findViewById(R.id.tv_username)
        ivHead = header.findViewById(R.id.iv_head)
        ivHead!!.setOnClickListener {
            if (UserInfoManager.isLoggedIn()) {

            } else {
                startActivity(LoginActivity::class.java)
            }
        }
        showUserInfo()
    }

    private fun setNightMode() {
        val item = nvMain.menu.findItem(R.id.menu_item_night)
        if (ThemeUtils.getNightMode()) {
//            ThemeUtils.setDynamicResOfNight()
            item.setIcon(R.drawable.ic_night_mode)
            item.title = getString(R.string.night_mode)
        } else {
            item.setIcon(R.drawable.ic_day_mode)
            item.title = getString(R.string.day_mode)
//            ThemeUtils.setDynamicResOfDay()
        }
        ThemeUtils.setNightMode()
    }

    private fun showUserInfo() {
        val username = UserInfoManager.getUserName()
        if (TextUtils.isEmpty(username)) {
            tvUserName!!.text = "未登录"
            ivHead!!.setImageResource(R.drawable.ic_head_logout)
        } else {
            tvUserName!!.text = username
            ivHead!!.setImageResource(R.drawable.ic_head_login)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @SuppressLint("ResourceType")
    private fun initBottomNavigation() {

        bnvMain = findViewById(R.id.bnv_main)
        bnvMain.selectedItemId = R.id.menu_item_home
        BottomNavigationViewHelper.disableShiftMode(bnvMain)
        bnvMain.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.menu_item_home -> {
                    showHideFragment(fragments[FIRST])
                }
                R.id.menu_item_architecture -> {
                    showHideFragment(fragments[SECOND])
                }
                R.id.menu_item_navigation -> {
                    showHideFragment(fragments[THIRD])
                }
                R.id.menu_item_project -> {
                    showHideFragment(fragments[FOURTH])
                }
            }
            true
        }
    }

    private fun initFragments() {
        fragments += HomeFragment()
        fragments += ArchitectureFragment()
        fragments += NavigationFragment()
        fragments += ProjectFragment()


        loadMultipleRootFragment(R.id.fl_container, FIRST,
                fragments[FIRST], fragments[SECOND], fragments[THIRD], fragments[FOURTH])
    }

    override fun onTabReselected(position: Int) {
    }

    override fun onTabUnselected(position: Int) {
    }

    override fun onTabSelected(position: Int) {

    }

    override fun initStatusBar() {
        initStatusBarByView(R.id.v_status_bar)
    }

    private fun openNavigationView() {
        if (!dlMain.isDrawerOpen(GravityCompat.START)) {
            dlMain.openDrawer(GravityCompat.START)
        }
    }

    private fun closeNavigationView() {
        if (dlMain.isDrawerOpen(GravityCompat.START)) {
            dlMain.closeDrawer(GravityCompat.START)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun navigationViewEvent(event: NavigationViewEvent) {
        when (event.code) {
            NavigationViewEvent.CODE_CLOSE -> {
                closeNavigationView()
            }
            NavigationViewEvent.CODE_OPEN -> {
                openNavigationView()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun userInfoEvent(event: UserInfoEvent) {
        when (event.code) {
            BaseEvent.CODE_REFRESH -> {
                showUserInfo()
            }
        }
    }
}