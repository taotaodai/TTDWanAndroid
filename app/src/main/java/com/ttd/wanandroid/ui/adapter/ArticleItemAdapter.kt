package com.ttd.wanandroid.ui.adapter

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseViewHolder
import com.ttd.sdk.utils.DateOrTimeUtil
import com.ttd.wanandroid.R
import com.ttd.wanandroid.adapter.BaseCompatAdapter
import com.ttd.wanandroid.bean.Article
import java.util.*

/**
 * Created by wt on 2018/7/11.
 */
class ArticleItemAdapter : BaseCompatAdapter<Article, BaseViewHolder> {

    constructor(layoutResId: Int, data: MutableList<Article>?) : super(layoutResId, data)

    constructor(layoutResId: Int) : super(layoutResId)

    override fun convert(helper: BaseViewHolder?, item: Article?) {
        val tvTitle = helper!!.getView<TextView>(R.id.tv_title)
        val tvAuthor = helper.getView<TextView>(R.id.tv_author)
        val tvReleaseTime = helper.getView<TextView>(R.id.tv_release_time)
        val vIllustration = helper.getView<View>(R.id.v_illustration)
        val ivIllustration = helper.getView<ImageView>(R.id.iv_illustration)
//        val ivCollect = helper.getView<ImageView>(R.id.iv_collect)

        tvTitle.text = item!!.title
        tvAuthor.text = String.format("作者：%s", item.author)
        tvReleaseTime.text = String.format("时间：%s", DateOrTimeUtil.getTimeFormatText(Date(item.publishTime)))

        if (TextUtils.isEmpty(item.envelopePic)) {
            vIllustration.visibility = View.GONE
        } else {
            vIllustration.visibility = View.VISIBLE
            Glide.with(mContext).load(item.envelopePic).placeholder(R.drawable.ic_placeholder).into(ivIllustration)
        }

//        if (item.isCollect){
//            ivCollect.setImageResource(R.drawable.ic_collect)
//        }else{
//            ivCollect.setImageResource(R.drawable.ic_collect_empty)
//        }
    }

}