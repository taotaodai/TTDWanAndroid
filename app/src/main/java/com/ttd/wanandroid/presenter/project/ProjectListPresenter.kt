package com.ttd.wanandroid.presenter.project

import com.ttd.wanandroid.bean.ProjectBean
import com.ttd.wanandroid.contract.project.ProjectListContract
import com.ttd.wanandroid.model.project.ProjectListModel

/**
 * author wt
 * createDate 2018/7/30
 */
class ProjectListPresenter : ProjectListContract.ProjectListPresenter() {
    override fun loadMoreArticles(project: ProjectBean.Project?) {
        mRxManager.register(mIModel.getProjects(++page, project!!.id).subscribe({
            mIView.showMoreProjects(it)
        }, {

        }))
    }

    var page: Int = 0
    override fun getModel(): ProjectListContract.IProjectListModel {
        return ProjectListModel()
    }

    override fun loadProjects(project: ProjectBean.Project) {
        page = 0
        mRxManager.register(mIModel.getProjects(page, project.id).subscribe({
            mIView.showProjects(it)
        }, {

        }))
    }

    override fun onStart() {
    }

}