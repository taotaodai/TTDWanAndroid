package com.ttd.wanandroid.contract.project;

import com.ttd.wanandroid.base.IBaseModel;
import com.ttd.wanandroid.base.IBaseView;
import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.bean.ProjectBean;
import com.ttd.wanandroid.presenter.BasePresenter;

import io.reactivex.Observable;

/**
 * author wt
 * createDate 2018/7/30
 */

public interface ProjectListContract {
    abstract class ProjectListPresenter extends BasePresenter<IProjectListModel,IProjectListView> {
        public abstract void loadProjects(ProjectBean.Project project);

        public abstract void loadMoreArticles(ProjectBean.Project project);
    }

    interface IProjectListModel extends IBaseModel{
        Observable<ArticleBean> getProjects(int page,int id);
    }

    interface IProjectListView extends IBaseView{
        void showProjects(ArticleBean articleBean);

        void showMoreProjects(ArticleBean articleBean);
    }

}
