package com.ttd.wanandroid.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ttd.wanandroid.R
import com.ttd.wanandroid.adapter.SectionsPagerAdapter
import com.ttd.wanandroid.base.BaseCompatActivity
import com.ttd.wanandroid.utils.BottomNavigationViewHelper
import me.yokeyword.fragmentation.SupportFragment

/**
 * Created by wt on 2018/6/30.
 */
class MainActivity : BaseCompatActivity(), BottomNavigationBar.OnTabSelectedListener {

    var fragments: List<SupportFragment> = listOf()
    lateinit var dlMain: DrawerLayout
    lateinit var vpMain: ViewPager
    lateinit var bnvMain: BottomNavigationView
    lateinit var nvMain: NavigationView

    override fun initView(savedInstanceState: Bundle?) {
        dlMain = findViewById(R.id.dl_main)
        nvMain = findViewById(R.id.nv_menu)
//        nvMain.itemIconTintList = null
//        nvMain.itemTextColor = null
        nvMain.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item_theme -> {
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
                    showHideFragment(fragments[0])
                }
                R.id.menu_item_system -> {
                    showHideFragment(fragments[1])
                }
                R.id.menu_item_navigation -> {
                    showHideFragment(fragments[2])
                }
                R.id.menu_item_project -> {
                    showHideFragment(fragments[3])
                }
            }
            true
        }
    }

    private fun initFragments() {
        vpMain = findViewById(R.id.vp_main)
        fragments += HomeFragment()
        fragments += SystemFragment()
        fragments += NavigationFragment()
        fragments += ProjectFragment()

        vpMain.adapter = SectionsPagerAdapter(supportFragmentManager, fragments)
        vpMain.currentItem = 0
    }

    override fun onTabReselected(position: Int) {
    }

    override fun onTabUnselected(position: Int) {
    }

    override fun onTabSelected(position: Int) {
        vpMain.currentItem = position
    }

    override fun initStatusBar() {
//        initStatusBarByView(R.id.v_status_bar)
    }

    fun gotoThemeSetting() {
        dlMain.closeDrawer(GravityCompat.START)
        ActivityCompat.startActivity(this@MainActivity, Intent(this@MainActivity, ThemeSettingActivity::class.java), null)
    }

}