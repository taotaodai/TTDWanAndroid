package com.ttd.wanandroid.ui.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseMVPCompatFragment
import com.ttd.wanandroid.bean.Article
import com.ttd.wanandroid.bean.ArticleBean
import com.ttd.wanandroid.contract.architecture.ArchitectureContract
import com.ttd.wanandroid.event.ArchitectureEvent
import com.ttd.wanandroid.event.BaseEvent
import com.ttd.wanandroid.presenter.BasePresenter
import com.ttd.wanandroid.presenter.architecture.ArchitecturePresenter
import com.ttd.wanandroid.ui.ArticleDetailActivity
import com.ttd.wanandroid.ui.adapter.ArticleItemAdapter
import com.ttd.wanandroid.widget.architecture.ArchitectureSelectionWindow
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by wt on 2018/7/2.
 */
class ArchitectureFragment : BaseMVPCompatFragment<ArchitectureContract.ArchitecturePresenter, ArchitectureContract.IArchitectureModel>(), ArchitectureContract.IArchitectureView {
    override fun showSubtile(title: String?) {
        tbArchitecture!!.subtitle = title
    }

    override fun showMoreArticles(articleBean: ArticleBean?) {
        if (srlArchitecture!!.isRefreshing){
            srlArchitecture!!.isRefreshing = false
        }
        adapter!!.loadMoreComplete()
        adapter?.addData(articleBean!!.articleData.articles)
    }

    var tbArchitecture: Toolbar? = null
    var rvArchitecture: RecyclerView? = null
    var srlArchitecture: SwipeRefreshLayout? = null
    var adapter: ArticleItemAdapter? = null

    override fun initPresenter(): BasePresenter<*, *> {
        return ArchitecturePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_architecture
    }

    override fun initUI(view: View?, savedInstanceState: Bundle?) {
        EventBus.getDefault().register(this)
        tbArchitecture = view?.findViewById(R.id.tb_architecture)
        (activity as AppCompatActivity).setSupportActionBar(tbArchitecture)
        setHasOptionsMenu(true)
        srlArchitecture = view?.findViewById(R.id.srl_article)
        rvArchitecture = view?.findViewById(R.id.rv_article)
        srlArchitecture?.setOnRefreshListener {
            mPresenter.loadArticleList()
        }
        rvArchitecture!!.layoutManager = LinearLayoutManager(mContext)
        adapter = ArticleItemAdapter(R.layout.adapter_article_item)
        rvArchitecture!!.adapter = adapter
        mPresenter.loadArchitectureTree()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)

        showModelessLoading()
    }

    override fun showModelessLoading() {
        adapter!!.emptyView = vLoading
    }

    override fun dismissModelessLoading() {
    }

    override fun showArticles(articleBean: ArticleBean?) {
        if (srlArchitecture!!.isRefreshing) {
            srlArchitecture!!.isRefreshing = false
        }
        adapter = ArticleItemAdapter(R.layout.adapter_article_item, articleBean!!.articleData.articles)
        rvArchitecture!!.adapter = adapter
        adapter!!.setOnLoadMoreListener({
            mPresenter.loadMoreArticles()
        }, rvArchitecture)

        adapter!!.setOnItemClickListener { _, _, position ->
            val item = adapter!!.data[position]

            val bundle = Bundle()
            item.position = position
            bundle.putSerializable(Article::class.java.simpleName, item)
            startNewActivity(ArticleDetailActivity::class.java, bundle)
        }
    }

    var pwArchitecture: ArchitectureSelectionWindow? = null
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater!!.inflate(R.menu.menu_architecture, menu)
        tbArchitecture!!.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_architecture_expand -> {
                    if (pwArchitecture == null){
                        pwArchitecture = ArchitectureSelectionWindow(mActivity, (mPresenter as ArchitecturePresenter).architectureData!!)
                        pwArchitecture!!.init()
                    }
                    pwArchitecture!!.show(tbArchitecture!!)
                }
            }
            true
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun architectureEvent(event: ArchitectureEvent){
        when(event.code){
            BaseEvent.CODE_REFRESH ->{
                pwArchitecture!!.hide()
                srlArchitecture!!.isRefreshing = true
                mPresenter.loadArticleList()
            }
        }
    }
}