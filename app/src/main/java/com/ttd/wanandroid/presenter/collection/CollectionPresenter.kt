package com.ttd.wanandroid.presenter.collection

import com.ttd.wanandroid.contract.collection.CollectionContract
import com.ttd.wanandroid.model.collection.CollectionModel

/**
 * author wt
 * createDate 2018/8/9
 */
class CollectionPresenter : CollectionContract.CollectionPresenter() {
    override fun refresh() {
        page = 0
        loadArticles()
    }

    override fun loadMoreArticles() {
        mRxManager.register(mIModel.getArticles(++page).subscribe({
            mIView.showMoreArticles(it)
        }, {

        }))
    }

    var page: Int = 0
    override fun loadArticles() {
        mRxManager.register(mIModel.getArticles(page).subscribe({
            mIView.showArticles(it)
        }, {

        }))
    }

    override fun getModel(): CollectionContract.ICollectionModel {
        return CollectionModel()
    }

    override fun onStart() {
    }

}