package com.ttd.wanandroid.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseCompatActivity
import com.ttd.wanandroid.event.NavigationViewEvent
import com.ttd.wanandroid.ui.fragment.ArchitectureFragment
import com.ttd.wanandroid.ui.fragment.HomeFragment
import com.ttd.wanandroid.ui.fragment.NavigationFragment
import com.ttd.wanandroid.ui.fragment.ProjectFragment
import com.ttd.wanandroid.utils.BottomNavigationViewHelper
import me.yokeyword.fragmentation.SupportFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by wt on 2018/6/30.
 */
class MainActivity : BaseCompatActivity(), BottomNavigationBar.OnTabSelectedListener {

    var fragments: List<SupportFragment> = listOf()
    lateinit var dlMain: DrawerLayout
    lateinit var bnvMain: BottomNavigationView
    lateinit var nvMain: NavigationView

    val FIRST = 0
    val SECOND = 1
    val THIRD = 2
    val FOURTH = 3

    override fun initView(savedInstanceState: Bundle?) {
        EventBus.getDefault().register(this)
        dlMain = findViewById(R.id.dl_main)
        nvMain = findViewById(R.id.nv_menu)
//        nvMain.itemIconTintList = null
//        nvMain.itemTextColor = null
        nvMain.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item_theme -> {
                    closeNavigationView()
                    gotoThemeSetting()
                }
            }
            true
        }

        initBottomNavigation()
        initFragments()
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
//        bnbMain = findViewById(R.id.bnb_main)
////        bnbMain.activeColor = android.R.attr.colorPrimary
//        bnbMain.setMode(BottomNavigationBar.MODE_FIXED)
//        bnbMain.addItem(BottomNavigationItem(R.drawable.ic_bottom_home, "首页"))
//        bnbMain.addItem(BottomNavigationItem(R.drawable.ic_bottom_system, "体系"))
//        bnbMain.addItem(BottomNavigationItem(R.drawable.ic_bottom_navigation, "导航"))
//        bnbMain.addItem(BottomNavigationItem(R.drawable.ic_bottom_project, "项目"))
//        bnbMain.setFirstSelectedPosition(0)
//        bnbMain.initialise()
//        bnbMain.setTabSelectedListener(this)

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

    private fun openNavigationView(){
        if (!dlMain.isDrawerOpen(GravityCompat.START)){
            dlMain.openDrawer(GravityCompat.START)
        }
    }
    private fun closeNavigationView() {
        if (dlMain.isDrawerOpen(GravityCompat.START)){
            dlMain.closeDrawer(GravityCompat.START)
        }
    }

    private fun gotoThemeSetting() {
        ActivityCompat.startActivity(this@MainActivity, Intent(this@MainActivity, ThemeSettingActivity::class.java), null)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun navigationViewEvent(event: NavigationViewEvent) {
        when (event.code) {
            NavigationViewEvent.CODE_CLOSE -> {
                closeNavigationView()
            }
            NavigationViewEvent.CODE_OPEN ->{
                openNavigationView()
            }
        }
    }
}