package com.ttd.wanandroid.presenter.architecture

import com.ttd.wanandroid.bean.ArchitectureBean
import com.ttd.wanandroid.contract.architecture.ArchitectureContract
import com.ttd.wanandroid.model.architecture.ArchitectureModel

/**
 * Created by wt on 2018/7/16.
 */
class ArchitecturePresenter : ArchitectureContract.ArchitecturePresenter() {
    override fun loadMoreArticles() {
        val id = getArticleClassifyId(architectureData!!.architectureList[architectureData!!.position])
        mRxManager.register(mIModel.getArticlesById(++page, id)
                .subscribe({
                    mIView.showMoreArticles(it)
                    if (it.articleData.articles.size == 0){
                        mIView.showNoMoreView(false)
                    }
                }, {
                    print("~~~")
                }))
    }

    var page = 0

    var architectureData: ArchitectureBean? = null

    override fun getModel(): ArchitectureContract.IArchitectureModel {
        return ArchitectureModel()
    }

    override fun loadArticleList() {
        page = 0
        val id = getArticleClassifyId(architectureData!!.architectureList[architectureData!!.position])
        mRxManager.register(mIModel.getArticlesById(page, id)
                .subscribe({
                    mIView.showArticles(it)
                    mIView.showSubtile(getSubTitle(architectureData!!.architectureList[architectureData!!.position], ""))
                }, {
                    print("~~~")
                }))
    }

    override fun loadArchitectureTree() {
        mRxManager.register(mIModel.architectureTree.subscribe({
            architectureData = it
            loadArticleList()
        }, {
            print("~~~~")
        }))
    }

    fun getArticleClassifyId(architecture: ArchitectureBean.Architecture): Int {
        return if (architecture.children.isEmpty()) {
            architecture.id
        } else {
            getArticleClassifyId(architecture.children[architecture.position])
        }
    }

    private val NEXT_LEVEL = " -> "
    fun getSubTitle(architecture: ArchitectureBean.Architecture, title: String): String {
        val sb = StringBuilder(title)
        sb.append(architecture.name)
        sb.append(NEXT_LEVEL)
        return if (architecture.children.isEmpty()) {
            sb.setLength(sb.length - NEXT_LEVEL.length)
            sb.toString()
        } else {
            getSubTitle(architecture.children[architecture.position], sb.toString())
        }
    }

    override fun onStart() {
    }

}