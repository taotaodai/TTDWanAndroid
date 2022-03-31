package com.ttd.wanandroid.model.article;

import com.ttd.sdk.helper.RetrofitCreateHelper;
import com.ttd.sdk.helper.RxHelper;
import com.ttd.wanandroid.api.Api;
import com.ttd.wanandroid.bean.BaseBean;
import com.ttd.wanandroid.contract.article.ArticleDetailContract;

import io.reactivex.Observable;

/**
 * Created by wt on 2018/7/13.
 */

public class ArticleDetailModel implements ArticleDetailContract.IArticleDetailModel {
    @Override
    public Observable<BaseBean> collectArticle(int id) {
        return RetrofitCreateHelper.init(false).createApi(Api.class,Api.HOST).collect(id).
                compose(RxHelper.<BaseBean>rxSchedulerHelper());
    }

    @Override
    public Observable<BaseBean> uncollectArticle(int id) {
        return RetrofitCreateHelper.init(false).createApi(Api.class,Api.HOST).unCollect(id).
                compose(RxHelper.<BaseBean>rxSchedulerHelper());
    }
}
