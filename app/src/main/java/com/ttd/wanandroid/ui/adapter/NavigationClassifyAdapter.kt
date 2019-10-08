package com.ttd.wanandroid.ui.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ttd.wanandroid.R
import com.ttd.wanandroid.bean.NavigationBean

/**
 * Created by wt on 2018/7/18.
 */
class NavigationClassifyAdapter : BaseQuickAdapter<NavigationBean.Classify, BaseViewHolder> {
    var navigationBean: NavigationBean? = null

    constructor(layoutResId: Int) : super(layoutResId)

    constructor(layoutResId: Int, data: NavigationBean?) : super(layoutResId, data!!.classifies) {
        this.navigationBean = data
    }

    override fun convert(helper: BaseViewHolder?, item: NavigationBean.Classify?) {
        val tvClassify = helper!!.getView<TextView>(R.id.tv_classify)
        tvClassify.text = item!!.name
        val vChecked = helper.getView<View>(R.id.v_checked)

        if (helper.adapterPosition == navigationBean!!.position) {
            vChecked.visibility = View.VISIBLE
//            tvClassify.setBackgroundResource(R.color.green_0D5302)
        } else {
            vChecked.visibility = View.GONE
//            tvClassify.setBackgroundResource(R.color.green_259B24)
        }
    }
}