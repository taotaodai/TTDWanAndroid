package com.ttd.wanandroid.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ttd.wanandroid.R
import com.ttd.wanandroid.bean.Article
import com.ttd.wanandroid.bean.NavigationBean
import com.ttd.wanandroid.event.NavigationEvent
import com.ttd.wanandroid.ui.ArticleDetailActivity
import com.ttd.wanandroid.widget.architecture.SectionedBaseAdapter
import org.greenrobot.eventbus.EventBus

/**
 * Created by wt on 2018/7/18.
 */
class NavigationTagsAdapter : SectionedBaseAdapter<Article> {
    override fun onHeaderChange(firstVisibleItem: Int) {
        EventBus.getDefault().post(NavigationEvent(NavigationEvent.CODE_CHANGE_CLASSIFY, getSectionForPosition(firstVisibleItem)))
    }

    var dataList: MutableList<NavigationBean.Classify>? = null

    constructor(context: Context?, mDatas: MutableList<NavigationBean.Classify>?, itemLayoutId: Int) : super(context, mDatas!![0].articles, itemLayoutId) {
        this.dataList = mDatas
    }

    override fun convert(helper: ViewHolder?, item: Article?, position: Int, parent: ViewGroup?) {
        var tvTag = helper!!.getView<TextView>(R.id.tv_tag)
        tvTag.text = item!!.title
        tvTag.setOnClickListener {
            val intent = Intent(mContext, ArticleDetailActivity::class.java)
            intent.putExtra(Article::class.java.simpleName, item)
            mContext.startActivity(intent)
        }
    }

    override fun getItem(section: Int, position: Int): Article {
        return dataList!![section].articles[position]
    }

    override fun getItemId(section: Int, position: Int): Long {
        return position.toLong()
    }

    override fun getSectionCount(): Int {
        return dataList!!.size
    }

    override fun getCountForSection(section: Int): Int {
        return dataList!![section].articles.size
    }

    override fun getItemView(section: Int, position: Int, convertView: View?, parent: ViewGroup?): View {
        return convertView!!
    }

    override fun getSectionHeaderView(section: Int, convertView: View?, parent: ViewGroup?): View {
        var layout: LinearLayout?
        val item = dataList!![section]
        if (convertView == null) {
            val inflator = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            layout = inflator.inflate(R.layout.adapter_navigation_header, null) as LinearLayout
        } else {
            layout = convertView as LinearLayout
        }
        layout.isClickable = false
        val tvName = layout.findViewById<TextView>(R.id.tv_title)
        tvName.text = item.name

        return layout
    }

}