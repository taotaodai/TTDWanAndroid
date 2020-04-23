package com.ttd.wanandroid.ui.fragment

import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseMVPCompatFragment
import com.ttd.wanandroid.bean.Article
import com.ttd.wanandroid.bean.ArticleBean
import com.ttd.wanandroid.bean.BannerBean
import com.ttd.wanandroid.contract.home.HomeContract
import com.ttd.wanandroid.event.ArticleEvent
import com.ttd.wanandroid.event.BaseEvent
import com.ttd.wanandroid.event.NavigationViewEvent
import com.ttd.wanandroid.presenter.BasePresenter
import com.ttd.wanandroid.presenter.home.HomePresenter
import com.ttd.wanandroid.ui.ArticleDetailActivity
import com.ttd.wanandroid.ui.MainActivity
import com.ttd.wanandroid.ui.adapter.ArticleItemAdapter
import com.zhengsr.viewpagerlib.bean.PageBean
import com.zhengsr.viewpagerlib.indicator.TransIndicator
import com.zhengsr.viewpagerlib.indicator.ZoomIndicator
import com.zhengsr.viewpagerlib.view.BannerViewPager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by wt on 2018/7/2.
 */
class HomeFragment : BaseMVPCompatFragment<HomeContract.HomePresenter, HomeContract.IHomeModel>(), HomeContract.IHomeView {
    override fun showRefresh(articleBean: ArticleBean?) {
        if (srlHome.isRefreshing) {
            srlHome.isRefreshing = false
        }
        adapter!!.initFooter()
        adapter!!.replaceData(articleBean!!.articleData.articles)
    }

    override fun showBanner(bannerBean: BannerBean?) {

        val pageBean: PageBean = PageBean.Builder<BannerBean.Banner>()
                .setDataObjects(bannerBean!!.banners)
                .setIndicator(ziBanner)
                .builder()
//        bvpBanner!!.setPageTransformer(false, MzTransformer())
        bvpBanner!!.setPageListener(pageBean, R.layout.adapter_banner, { view, t ->
            val bean = t as BannerBean.Banner
            val ivBanner = view.findViewById<ImageView>(R.id.iv_banner)
            ivBanner.setOnClickListener {
                val bundle = Bundle()
                val article = Article()
                article.link = bean.url
                article.title = bean.title
                bundle.putSerializable(Article::class.java.simpleName, article)
                startNewActivity(ArticleDetailActivity::class.java, bundle)
            }
            Glide.with(mActivity).load(bean.imagePath).into(ivBanner)
        })
        bvpBanner!!.currentItem = 0
    }

    private var vBanner: View? = null
    private fun initBannerView() {
        vBanner = LayoutInflater.from(mContext).inflate(R.layout.view_header_banner, null)
        bvpBanner = vBanner!!.findViewById(R.id.bvp_banner)
        ziBanner = vBanner!!.findViewById(R.id.zi_banner)
//        tiBanner = view.findViewById(R.id.ti_banner)
    }

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
        mPresenter.loadBanner()
        mPresenter.loadArticles()
    }

    override fun showModelessLoading() {
        adapter!!.emptyView = vLoading
    }

    override fun dismissModelessLoading() {
    }

    lateinit var parent: MainActivity
    lateinit var tbHome: Toolbar
    lateinit var rvHome: androidx.recyclerview.widget.RecyclerView
    lateinit var srlHome: androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    var bvpBanner: BannerViewPager? = null
    var ziBanner: ZoomIndicator? = null
    var tiBanner: TransIndicator? = null
    var adapter: ArticleItemAdapter? = null

    override fun showArticles(articleBean: ArticleBean?) {
        if (srlHome.isRefreshing) {
            srlHome.isRefreshing = false
        }
        adapter = ArticleItemAdapter(R.layout.adapter_article_item, articleBean!!.articleData.articles)
        adapter!!.addHeaderView(vBanner)
        adapter!!.setOnLoadMoreListener({
            //            adapter!!.loadMoreComplete()
            mPresenter.loadMoreArticles()
        }, rvHome)

        adapter!!.setOnItemClickListener { _, _, position ->
            val item = adapter!!.data[position]

            val bundle = Bundle()
            item.position = position
            bundle.putSerializable(Article::class.java.simpleName, item)
            startNewActivity(ArticleDetailActivity::class.java, bundle)
        }
        rvHome.adapter = adapter
    }

    override fun showMoreArticles(articleBean: ArticleBean?) {
        adapter!!.loadMoreComplete()
        adapter!!.addData(articleBean!!.articleData.articles)
    }

    override fun initPresenter(): BasePresenter<*, *> {
        return HomePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initUI(view: View?, savedInstanceState: Bundle?) {
        tbHome = view!!.findViewById(R.id.tb_home)
        tbHome.inflateMenu(R.menu.menu_home)
        tbHome.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.item_search ->{
                    Toast.makeText(mContext,"搜索",Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
//        (activity as AppCompatActivity).setSupportActionBar(tbHome)
        tbHome.setNavigationOnClickListener {
            EventBus.getDefault().post(NavigationViewEvent(NavigationViewEvent.CODE_OPEN))
        }
        parent = activity as MainActivity

        srlHome = view.findViewById(R.id.srl_article)
        srlHome.setOnRefreshListener {
            mPresenter.refresh()
        }
        rvHome = view.findViewById(R.id.rv_article_home)
        adapter = ArticleItemAdapter(R.layout.adapter_article_item)
        rvHome.adapter = adapter
        rvHome.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        initBannerView()
//        rvHome.addItemDecoration(RecycleViewDivider(activity,LinearLayoutManager.HORIZONTAL,))

    }

//    fun pushApiDemo() {
//        /**
//         * 推送
//         */
//        PushBuilder.init()
//                .addNecessaryParams("2", "android测试测试", "guest000263")
//                .setCreateuser("guest000197")
//                .setSubtitle("副标题")
//                .applyTargetParams(TargetParams("www.baidu.com", HashMap()))
//                .build().execute(object : DisposeDataListener {
//                    override fun onSuccess(responseObj: Any) {
//                        Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show()
//                    }
//
//                    override fun onFailure(e: OkHttpException) {
//                        Toast.makeText(context, e.emsg.toString(), Toast.LENGTH_SHORT).show()
//                    }
//                })
//        /**
//         * 取消推送
//         */
//        DeleteBuilder.init()
//                .addNecessaryParams("2", "14")
//                .build().excuteDelete(object : DisposeDataListener {
//                    override fun onSuccess(responseObj: Any) {
//                        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show()
//                    }
//
//                    override fun onFailure(e: OkHttpException) {
//                        Toast.makeText(context, e.emsg.toString(), Toast.LENGTH_SHORT).show()
//                    }
//                })
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun articleEvent(event: ArticleEvent<Article>) {
        when (event.code) {
            BaseEvent.CODE_REPLACE -> {
                adapter?.setData(event.position, event.replace)
            }
        }
    }

}