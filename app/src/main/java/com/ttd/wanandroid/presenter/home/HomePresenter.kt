package com.ttd.wanandroid.presenter.home

import com.ttd.wanandroid.contract.home.HomeContract
import com.ttd.wanandroid.model.home.HomeModel

/**
 * Created by wt on 2018/7/11.
 */
class HomePresenter : HomeContract.HomePresenter() {

    var page: Int = 0
    //    lateinit var articleBean: ArticleBean
//    var articles: List<ArticleBean.ArticleData.Article> = mutableListOf()
    override fun getModel(): HomeContract.IHomeModel {
        return HomeModel()
    }

    override fun onStart() {
    }

    override fun loadBanner() {
        mRxManager.register(mIModel.banner.subscribe({
            it.banners
        }, {
            print(it.message)
        }))
    }

    override fun loadArticles() {
//        if (articles.isEmpty()){
//            mIView.showEmptyView()
//        }
        page = 0
        mRxManager.register(mIModel.getArticles(page).subscribe({
            //            initArticleData(it)
            mIView.showArticles(it)
        }, {
            print(it.message)
        }))
    }

    override fun loadMoreArticles() {
        mRxManager.register(mIModel.getArticles(++page).subscribe({
            //            initArticleData(it)
            mIView.showMoreArticles(it)
        }, {
            print(it.message)
        }))
    }

//    private fun initArticleData(articleBean: ArticleBean) {
//        this.articleBean = articleBean
//        articles = articleBean.articleData.articles
//    }

}