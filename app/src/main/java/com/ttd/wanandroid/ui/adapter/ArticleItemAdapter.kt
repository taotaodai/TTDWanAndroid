package com.ttd.wanandroid.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import com.ttd.sdk.utils.DateOrTimeUtil
import com.ttd.wanandroid.R
import com.ttd.wanandroid.adapter.BaseCompatAdapter
import com.ttd.wanandroid.bean.Article
import com.ttd.wanandroid.bean.ArticleBean
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

        tvTitle.text = item!!.title
        tvAuthor.text = String.format("作者：%s", item.author)
        tvReleaseTime.text = String.format("时间：%s", DateOrTimeUtil.getTimeFormatText(Date(item.publishTime)))
    }

}