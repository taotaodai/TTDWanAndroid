package com.ttd.wanandroid.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gyf.barlibrary.ImmersionBar
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseCompatActivity
import com.ttd.wanandroid.bean.SkinListBean
import com.ttd.wanandroid.ui.adapter.ThemeSelectionAdapter
import com.ttd.wanandroid.utils.SkinUtils
import com.ttd.wanandroid.widget.skin.SkinCompatCircleImageView
import skin.support.SkinCompatManager

/**
 * Created by wt on 2018/7/9.
 */
class ThemeSettingActivity : BaseCompatActivity() {

    lateinit var tbTheme: Toolbar
    lateinit var civTheme: SkinCompatCircleImageView
    lateinit var tvTheme: TextView
    lateinit var rvTheme: RecyclerView
    lateinit var adapter: ThemeSelectionAdapter

    var themes: MutableList<SkinListBean.Skin> = mutableListOf()

    override fun initView(savedInstanceState: Bundle?) {
        initTitleBar(tbTheme, "")
        civTheme = findViewById(R.id.civ_theme)
        tvTheme = findViewById(R.id.tv_theme)
        rvTheme = findViewById(R.id.rv_pure_color)
        rvTheme.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        themes = SkinUtils.initThemes(this)!!
        showCurrentSkin(SkinUtils.getCurrentSkin(themes))

        adapter = ThemeSelectionAdapter(R.layout.adapter_theme_item, themes)
        rvTheme.adapter = adapter
        adapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            val item = themes[position]
            if (!item.isCurrentSkin) {
                item.isCurrentSkin = true
                for (temp in themes) {
                    if (temp != item) {
                        temp.isCurrentSkin = false
                    }
                }
                changeSkin(item)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_theme_setting
    }

    override fun initStatusBar() {
//        initStatusBar(R.id.tb_theme_setting)
        tbTheme = findViewById(R.id.tb_theme_setting)
        ImmersionBar.with(this)
                .titleBar(tbTheme, false)
                .transparentBar()
                .init()
    }

    fun showCurrentSkin(skin: SkinListBean.Skin) {
        tvTheme.text = skin.name
    }


    fun changeSkin(skin: SkinListBean.Skin) {

        if (SkinUtils.isDefaultSkin(skin.file)) {
            SkinCompatManager.getInstance().restoreDefaultTheme()
            showCurrentSkin(skin)
        } else {
            SkinCompatManager.getInstance().loadSkin(skin.file, object : SkinCompatManager.SkinLoaderListener {
                override fun onStart() {}

                override fun onSuccess() {
                    showCurrentSkin(skin)
                    Toast.makeText(this@ThemeSettingActivity, "换肤成功", Toast.LENGTH_SHORT).show()
                }

                override fun onFailed(s: String) {}
            }, SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS)

        }
    }

}