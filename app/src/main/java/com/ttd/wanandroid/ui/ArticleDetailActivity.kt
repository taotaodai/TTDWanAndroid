package com.ttd.wanandroid.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import com.bumptech.glide.Glide
import com.ttd.wanandroid.R
import com.ttd.wanandroid.bean.Article
import com.ttd.wanandroid.contract.article.ArticleDetailContract
import com.ttd.wanandroid.event.ArticleEvent
import com.ttd.wanandroid.event.BaseEvent
import com.ttd.wanandroid.presenter.BasePresenter
import com.ttd.wanandroid.presenter.article.ArticleDetailPresenter
import org.greenrobot.eventbus.EventBus

/**
 * Created by wt on 2018/7/12.
 */
class ArticleDetailActivity : BaseWebViewLoadActivity<ArticleDetailContract.ArticleDetailPresenter, ArticleDetailContract.IArticleDetailModel>()
        , ArticleDetailContract.IArticleDetailView {

    override fun gotoLogin() {
        startActivity(LoginActivity::class.java)
    }

    override fun showCollectResult(collect: Boolean) {
        article?.isCollect = collect

        EventBus.getDefault().post(ArticleEvent(BaseEvent.CODE_REPLACE, article!!.position, article))
        if (collect) {
            showToast("收藏成功")
        } else {
            showToast("取消收藏")
        }
        showCollectIcon(collect)
    }

    fun showCollectIcon(collect: Boolean) {
        if (collect) {
            toolbar.menu.findItem(R.id.item_article_collect).setIcon(R.drawable.ic_collect_red)
        } else {
            toolbar.menu.findItem(R.id.item_article_collect).setIcon(R.drawable.ic_collect_white)
        }
    }

    override fun showArticleDetail(url: String?) {
        flNetView.visibility = View.GONE
        nswvDetailContent.loadUrl(url)
//        Glide.with(mContext).load(article?.envelopePic).placeholder(R.drawable.bg_article_default).crossFade().into(ivDetail)
        Glide.with(mContext).load(R.drawable.bg_article_default).crossFade().into(ivDetail)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        initTitleBar(toolbar, "")
    }

    //    private var mTitle: String? = null
//    private var mUrl: String? = null
    private var article: Article? = null

    override fun initData() {
        super.initData()
        val bundle = intent.extras
        if (bundle != null) {
            article = bundle.getSerializable(Article::class.java.simpleName) as Article?
//            mUrl = bundle.getString(BundleKeyConstant.ARG_KEY_ARTICLE_DETAIL_URL)
//            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_ARTICLE_DETAIL_TITLE)
        }
    }

    override fun showModelessLoading() {
    }

    override fun dismissModelessLoading() {
    }

    override fun initPresenter(): BasePresenter<*, *> {
        return ArticleDetailPresenter()
    }

    override fun loadDetail() {
        mPresenter.loadArticleDetail(article?.link)
    }

    override fun getToolbarTitle(): String? {
        return article?.title
    }

    override fun initTitleBar(toolbar: Toolbar?, title: String?) {

//        toolbar?.inflateMenu(R.menu.menu_article_detail)
//        toolbar?.overflowIcon = resources.getDrawable(R.drawable.ic_collect_white)

        super.initTitleBar(toolbar, title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_article_detail, menu)
        showCollectIcon(article!!.isCollect)
        toolbar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_article_collect -> {
//                    startActivity(LoginActivity::class.java)
                    mPresenter.collectArticle(article)
                }
//                R.id.item_article_login -> {
//                    startActivity(LoginActivity::class.java)
//                }
            }
            true
        }
        return true
    }


}