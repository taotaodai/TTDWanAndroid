package com.ttd.wanandroid.ui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseMVPCompatActivity
import com.ttd.wanandroid.bean.Article
import com.ttd.wanandroid.bean.ArticleBean
import com.ttd.wanandroid.contract.collection.CollectionContract
import com.ttd.wanandroid.presenter.BasePresenter
import com.ttd.wanandroid.presenter.collection.CollectionPresenter
import com.ttd.wanandroid.ui.adapter.ArticleItemAdapter

/**
 * author wt
 * createDate 2018/8/9
 */
class CollectionArticlesActivity : BaseMVPCompatActivity<CollectionContract.CollectionPresenter, CollectionContract.ICollectionModel>(),CollectionContract.ICollectionView {

    lateinit var rvCollection: RecyclerView
    lateinit var srlCollection: SwipeRefreshLayout
    var adapter: ArticleItemAdapter? = null
    override fun showArticles(articleBean: ArticleBean?) {
        if (srlCollection.isRefreshing) {
            srlCollection.isRefreshing = false
        }
        adapter = ArticleItemAdapter(R.layout.adapter_article_item, articleBean!!.articleData.articles)
        adapter!!.setOnLoadMoreListener({
            mPresenter.loadMoreArticles()
        }, rvCollection)

        adapter!!.setOnItemClickListener { _, _, position ->
            val item = adapter!!.data[position]

            val bundle = Bundle()
            item.position = position
            bundle.putSerializable(Article::class.java.simpleName, item)
            startNewActivity(ArticleDetailActivity::class.java, bundle)
        }
        rvCollection.adapter = adapter
    }

    override fun showMoreArticles(articleBean: ArticleBean?) {
        val data = articleBean!!.articleData.articles
        if(data.size == 0){
            adapter!!.loadMoreEnd()
        }else{
            adapter!!.loadMoreComplete()
            adapter!!.addData(data)
        }
    }

    override fun showModelessLoading() {
    }

    override fun dismissModelessLoading() {
    }

    override fun initPresenter(): BasePresenter<*, *> {
        return CollectionPresenter()
    }

    var tbArticles: Toolbar? = null
    override fun initView(savedInstanceState: Bundle?) {
        initTitleBar(tbArticles,"我的收藏")
        srlCollection = findViewById(R.id.srl_article)
        srlCollection.setOnRefreshListener {
            mPresenter.refresh()
        }
        rvCollection = findViewById(R.id.rv_article_architecture)
        adapter = ArticleItemAdapter(R.layout.adapter_article_item)
        rvCollection.adapter = adapter
        rvCollection.layoutManager = LinearLayoutManager(this)

        mPresenter.loadArticles()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_collected_articles
    }

    override fun initStatusBar() {
        tbArticles = findViewById(R.id.tb_articles)
        initStatusBar(tbArticles)
    }


}