package com.ttd.wanandroid.presenter.project

import com.ttd.wanandroid.contract.project.ProjectContract
import com.ttd.wanandroid.model.project.ProjectModel

/**
 * author wt
 * createDate 2018/7/30
 */
class ProjectPresenter : ProjectContract.ProjectPresenter(){
    override fun loadProjectClassify() {
        mRxManager.register(mIModel.projectClassify.subscribe({
            mIView.showTabs(it)
        },{

        }))
    }

    override fun getModel(): ProjectContract.IProjectModel {
        return ProjectModel()
    }

    override fun onStart() {
    }

}