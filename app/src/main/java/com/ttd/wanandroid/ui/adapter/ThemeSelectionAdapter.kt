package com.ttd.wanandroid.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ttd.wanandroid.R
import com.ttd.wanandroid.bean.SkinListBean

/**
 * Created by wt on 2018/7/10.
 */
class ThemeSelectionAdapter : BaseQuickAdapter<SkinListBean.Skin, BaseViewHolder> {

    constructor(layoutResId: Int, data: MutableList<SkinListBean.Skin>?) : super(layoutResId, data)

    @SuppressLint("ResourceType")
    override fun convert(helper: BaseViewHolder?, item: SkinListBean.Skin?) {
        val iv = helper!!.getView<ImageView>(R.id.iv_theme)
        val tv = helper.getView<TextView>(R.id.tv_theme)
        val ivChecked = helper.getView<ImageView>(R.id.iv_checked)
        iv.setBackgroundColor(Color.parseColor(item!!.color))
        tv.text = item.name
        if (item.isCurrentSkin) {
            ivChecked.visibility = View.VISIBLE
        } else {
            ivChecked.visibility = View.GONE
        }


    }

}