package com.ttd.wanandroid.model.architecture;

import com.ttd.sdk.helper.RetrofitCreateHelper;
import com.ttd.sdk.helper.RxHelper;
import com.ttd.wanandroid.api.Api;
import com.ttd.wanandroid.bean.ArchitectureBean;
import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.contract.architecture.ArchitectureContract;

import io.reactivex.Observable;

/**
 * Created by wt on 2018/7/16.
 */

public class ArchitectureModel implements ArchitectureContract.IArchitectureModel {
    @Override
    public Observable<ArchitectureBean> getArchitectureTree() {
        return RetrofitCreateHelper.init(false).createApi(Api.class, Api.HOST).getArchitectureTree()
                .compose(RxHelper.<ArchitectureBean>rxSchedulerHelper());
    }

    @Override
    public Observable<ArticleBean> getArticlesById(int page, int id) {
        return RetrofitCreateHelper.init(false).createApi(Api.class, Api.HOST).getArticlesById(page, id)
                .compose(RxHelper.<ArticleBean>rxSchedulerHelper());
    }
}
