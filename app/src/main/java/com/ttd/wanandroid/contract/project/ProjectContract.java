package com.ttd.wanandroid.contract.project;

import com.ttd.wanandroid.base.IBaseModel;
import com.ttd.wanandroid.base.IBaseView;
import com.ttd.wanandroid.bean.ProjectBean;
import com.ttd.wanandroid.presenter.BasePresenter;

import io.reactivex.Observable;

/**
 * author wt
 * createDate 2018/7/30
 */

public interface ProjectContract {
    abstract class ProjectPresenter extends BasePresenter<IProjectModel,IProjectView> {
        public abstract void loadProjectClassify();
    }

    interface IProjectModel extends IBaseModel{
        Observable<ProjectBean> getProjectClassify();
    }

    interface IProjectView extends IBaseView{
        void showTabs(ProjectBean projectBean);
    }
}
