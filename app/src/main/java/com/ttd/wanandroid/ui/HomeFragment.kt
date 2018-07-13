package com.ttd.wanandroid.ui

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.chinajey.yiyuntong.push.exception.OkHttpException
import com.chinajey.yiyuntong.push.listener.DisposeDataListener
import com.chinajey.yiyuntong.push.options.DeleteBuilder
import com.chinajey.yiyuntong.push.options.PushBuilder
import com.chinajey.yiyuntong.push.request.TargetParams
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseMVPCompatFragment
import com.ttd.wanandroid.bean.ArticleBean
import com.ttd.wanandroid.constant.BundleKeyConstant
import com.ttd.wanandroid.contract.home.HomeContract
import com.ttd.wanandroid.event.ArticleEvent
import com.ttd.wanandroid.event.BaseEvent
import com.ttd.wanandroid.presenter.BasePresenter
import com.ttd.wanandroid.presenter.home.HomePresenter
import com.ttd.wanandroid.ui.adapter.ArticleItemAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by wt on 2018/7/2.
 */
class HomeFragment : BaseMVPCompatFragment<HomeContract.HomePresenter, HomeContract.IHomeModel>(), HomeContract.IHomeView {
    override fun showEmptyView() {
    }

    override fun showNoMoreView(gone: Boolean) {
    }

    override fun showRefresh() {
    }

    override fun showRefreshView(refreshing: Boolean) {
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

    lateinit var parent: MainActivity
    lateinit var tbHome: Toolbar
    lateinit var rvHome: RecyclerView
    lateinit var srlHome: SwipeRefreshLayout
    var adapter: ArticleItemAdapter? = null

    override fun showArticles(articleBean: ArticleBean?) {
        if (srlHome.isRefreshing) {
            srlHome.isRefreshing = false
        }
        adapter = ArticleItemAdapter(R.layout.adapter_article_item, articleBean!!.articleData.articles)
        rvHome.adapter = adapter
        adapter!!.setOnLoadMoreListener({
            adapter!!.loadMoreComplete()
            mPresenter.loadMoreArticles()
        }, rvHome)

        adapter!!.setOnItemClickListener { _, _, position ->
            val item = adapter!!.data[position]

            val bundle = Bundle()
            item.position = position
            bundle.putSerializable(ArticleBean.ArticleData.Article::class.java.simpleName,item)
//            bundle.putString(BundleKeyConstant.ARG_KEY_ARTICLE_DETAIL_TITLE, item.title)
//            bundle.putString(BundleKeyConstant.ARG_KEY_ARTICLE_DETAIL_URL, item.link)
            startNewActivity(ArticleDetailActivity::class.java, bundle)
        }
    }

    override fun showMoreArticles(articleBean: ArticleBean?) {
        adapter!!.addData(articleBean!!.articleData.articles)
    }

    override fun initPresenter(): BasePresenter<*, *> {
        return HomePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initUI(view: View?, savedInstanceState: Bundle?) {
//        mPresenter.loadBanner()

        tbHome = view!!.findViewById(R.id.tb_home)
        tbHome.setNavigationOnClickListener {
            if (!parent.dlMain.isDrawerOpen(GravityCompat.START)) {
                parent.dlMain.openDrawer(GravityCompat.START)
            }
        }
        parent = activity as MainActivity
        parent.initStatusBar(R.id.tb_home)

        srlHome = view.findViewById(R.id.srl_article)
        srlHome.setOnRefreshListener {
            mPresenter.loadArticles()
        }
        rvHome = view.findViewById(R.id.rv_article)
        rvHome.layoutManager = LinearLayoutManager(activity)
        adapter = ArticleItemAdapter(R.layout.adapter_article_item)
        rvHome.adapter = adapter
        mPresenter.loadArticles()
//        rvHome.addItemDecoration(RecycleViewDivider(activity,LinearLayoutManager.HORIZONTAL,))

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun pushApiDemo() {
        /**
         * 推送
         */
        PushBuilder.init()
                .addNecessaryParams("2", "android测试测试", "guest000263")
                .setCreateuser("guest000197")
                .setSubtitle("副标题")
                .applyTargetParams(TargetParams("www.baidu.com", HashMap()))
                .build().execute(object : DisposeDataListener {
                    override fun onSuccess(responseObj: Any) {
                        Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(e: OkHttpException) {
                        Toast.makeText(context, e.emsg.toString(), Toast.LENGTH_SHORT).show()
                    }
                })
        /**
         * 取消推送
         */
        DeleteBuilder.init()
                .addNecessaryParams("2", "14")
                .build().excuteDelete(object : DisposeDataListener {
                    override fun onSuccess(responseObj: Any) {
                        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(e: OkHttpException) {
                        Toast.makeText(context, e.emsg.toString(), Toast.LENGTH_SHORT).show()
                    }
                })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().register(this)
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun articleEvent(event:ArticleEvent<ArticleBean.ArticleData.Article>){
        when(event.code){
            BaseEvent.CODE_REPLACE -> {
                adapter?.setData(event.position,event.replace)
            }
        }
    }
}