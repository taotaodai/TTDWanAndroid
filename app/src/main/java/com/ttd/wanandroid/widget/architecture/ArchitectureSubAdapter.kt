package com.ttd.wanandroid.widget.architecture

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ttd.wanandroid.R
import com.ttd.wanandroid.bean.ArchitectureBean

/**
 * Created by wt on 2018/7/17.
 */
class ArchitectureSubAdapter : BaseQuickAdapter<ArchitectureBean.Architecture, BaseViewHolder> {
    var data: ArchitectureBean.Architecture? = null

    constructor(layoutResId: Int, data: ArchitectureBean.Architecture?) : super(layoutResId, data!!.children) {
        this.data = data
    }

    override fun convert(helper: BaseViewHolder?, item: ArchitectureBean.Architecture?) {
        helper!!.setText(R.id.tv_name, item!!.name)
        if (data!!.position == helper.layoutPosition) {
            helper.itemView.setBackgroundResource(R.color.gray_8A8A8A)
        }else{
            helper.itemView.setBackgroundResource(R.color.white)
        }
    }
}