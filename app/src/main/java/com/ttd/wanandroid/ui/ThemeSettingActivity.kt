package com.ttd.wanandroid.ui

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseCompatActivity
import com.ttd.wanandroid.bean.SkinListBean
import com.ttd.wanandroid.event.BaseEvent
import com.ttd.wanandroid.event.ThemeEvent
import com.ttd.wanandroid.ui.adapter.ThemeSelectionAdapter
import com.ttd.wanandroid.utils.ThemeUtils
import org.greenrobot.eventbus.EventBus
import skin.support.SkinCompatManager

/**
 * Created by wt on 2018/7/9.
 */
class ThemeSettingActivity : BaseCompatActivity() {

    lateinit var tbTheme: Toolbar
    lateinit var ivTheme: ImageView
    lateinit var tvTheme: TextView
    lateinit var rvTheme: RecyclerView
    lateinit var adapter: ThemeSelectionAdapter

    var themes: MutableList<SkinListBean.Skin> = mutableListOf()

    override fun initView(savedInstanceState: Bundle?) {
        initTitleBar(tbTheme, "")
        ivTheme = findViewById(R.id.iv_theme)
        tvTheme = findViewById(R.id.tv_theme)
        rvTheme = findViewById(R.id.rv_pure_color)
        rvTheme.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        themes = ThemeUtils.initThemes(this)!!
        showCurrentSkin(ThemeUtils.getCurrentSkin())

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
                changeTheme(item)
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
        initStatusBar(tbTheme)
    }

    fun showCurrentSkin(skin: SkinListBean.Skin) {
        tvTheme.text = skin.name
        ivTheme.setBackgroundColor(Color.parseColor(skin.color))
    }

    fun changeTheme(skin: SkinListBean.Skin) {
        if(ThemeUtils.getNightMode()){
            showCurrentSkin(skin)
            ThemeUtils.setTheme(skin.alias)
            EventBus.getDefault().post(ThemeEvent(BaseEvent.CODE_REFRESH))
        }else{
            if (ThemeUtils.isDefaultSkin(skin)) {
                SkinCompatManager.getInstance().restoreDefaultTheme()
                showCurrentSkin(skin)
                EventBus.getDefault().post(ThemeEvent(BaseEvent.CODE_REFRESH))
            } else {
                SkinCompatManager.getInstance().loadSkin(skin.alias, object : SkinCompatManager.SkinLoaderListener {
                    override fun onStart() {}

                    override fun onSuccess() {
                        showCurrentSkin(skin)
                        ThemeUtils.setTheme(skin.alias)
//                        Toast.makeText(this@ThemeSettingActivity, "换肤成功", Toast.LENGTH_SHORT).show()
                        EventBus.getDefault().post(ThemeEvent(BaseEvent.CODE_REFRESH))
                    }
                    override fun onFailed(s: String) {}
                }, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN)
            }
        }

    }


}