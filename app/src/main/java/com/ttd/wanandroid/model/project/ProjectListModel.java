package com.ttd.wanandroid.model.project;

import com.ttd.sdk.helper.RetrofitCreateHelper;
import com.ttd.sdk.helper.RxHelper;
import com.ttd.wanandroid.api.Api;
import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.contract.project.ProjectListContract;

import io.reactivex.Observable;

/**
 * author wt
 * createDate 2018/7/30
 */

public class ProjectListModel implements ProjectListContract.IProjectListModel{
    @Override
    public Observable<ArticleBean> getProjects(int page, int id) {
        return RetrofitCreateHelper.init(false).createApi(Api.class,Api.HOST).getProjectList(page,id).compose(RxHelper.rxSchedulerHelper());
    }
}
