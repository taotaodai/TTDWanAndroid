package com.ttd.wanandroid.model.collection;

import com.ttd.sdk.helper.RetrofitCreateHelper;
import com.ttd.sdk.helper.RxHelper;
import com.ttd.wanandroid.api.Api;
import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.contract.collection.CollectionContract;

import io.reactivex.Observable;

/**
 * author wt
 * createDate 2018/8/9
 */

public class CollectionModel implements CollectionContract.ICollectionModel{

    @Override
    public Observable<ArticleBean> getArticles(int page) {
        return RetrofitCreateHelper.init(false).createApi(Api.class,Api.HOST).getArticleCollections(page).compose(RxHelper.rxSchedulerHelper());
    }
}
