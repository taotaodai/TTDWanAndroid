package com.ttd.wanandroid.model.project;

import com.ttd.sdk.helper.RetrofitCreateHelper;
import com.ttd.sdk.helper.RxHelper;
import com.ttd.wanandroid.api.Api;
import com.ttd.wanandroid.bean.ProjectBean;
import com.ttd.wanandroid.contract.project.ProjectContract;

import io.reactivex.Observable;

/**
 * author wt
 * createDate 2018/7/30
 */

public class ProjectModel implements ProjectContract.IProjectModel{
    @Override
    public Observable<ProjectBean> getProjectClassify() {
        return RetrofitCreateHelper.init(false).createApi(Api.class,Api.HOST).getProjectClassify().compose(RxHelper.rxSchedulerHelper());
    }
}
