package com.ttd.wanandroid.presenter.article

import android.support.v4.app.FragmentActivity
import android.webkit.WebView
import com.ttd.wanandroid.contract.ArticleDetailContract
import com.ttd.wanandroid.model.ArticleDetailModel

/**
 * Created by wt on 2018/7/12.
 */
class ArticleDetailPresenter : ArticleDetailContract.ArticleDetailPresenter() {
    override fun collectArticle(id: Int) {
        mRxManager.register(mIModel.collectArticle(id).subscribe({
            mIView.showCollectResult(true)
        },{

        }))
    }

    override fun loadArticleDetail(url: String?) {
        mIView.showArticleDetail(url)
    }


    override fun getModel(): ArticleDetailContract.IArticleDetailModel {
        return ArticleDetailModel()
    }

    override fun saveImageClicked(activity: FragmentActivity?, imgUrl: String?) {
    }

    override fun gotoImageBrowseClicked(imgUrl: String?) {
    }

    override fun onStart() {
    }

    override fun imageLongClicked(hitTestResult: WebView.HitTestResult?) {
    }

}