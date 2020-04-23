package com.ttd.wanandroid.presenter.article

import androidx.fragment.app.FragmentActivity
import android.webkit.WebView
import com.ttd.wanandroid.bean.Article
import com.ttd.wanandroid.contract.article.ArticleDetailContract
import com.ttd.wanandroid.model.article.ArticleDetailModel
import com.ttd.wanandroid.utils.UserInfoManager

/**
 * Created by wt on 2018/7/12.
 */
class ArticleDetailPresenter : ArticleDetailContract.ArticleDetailPresenter() {
    override fun collectArticle(article: Article) {
        if (UserInfoManager.isLoggedIn()) {
            if (article.isCollect){
                mRxManager.register(mIModel.uncollectArticle(article.id).subscribe({
                    mIView.showCollectResult(false)
                },{

                }))
            }else{
                mRxManager.register(mIModel.collectArticle(article.id).subscribe({
                    mIView.showCollectResult(true)
                }, {

                }))
            }

        } else {
            mIView.gotoLogin()
        }

    }

    override fun loadArticleDetail(url: String?) {
        mIView.showArticleDetail(url)
    }


    override fun getModel(): ArticleDetailContract.IArticleDetailModel {
        return ArticleDetailModel()
    }

    override fun saveImageClicked(activity: androidx.fragment.app.FragmentActivity?, imgUrl: String?) {
    }

    override fun gotoImageBrowseClicked(imgUrl: String?) {
    }

    override fun onStart() {
    }

    override fun imageLongClicked(hitTestResult: WebView.HitTestResult?) {
    }

}