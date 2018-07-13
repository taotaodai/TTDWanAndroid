package com.ttd.wanandroid.model.home;

import com.ttd.sdk.helper.RetrofitCreateHelper;
import com.ttd.sdk.helper.RxHelper;
import com.ttd.wanandroid.api.Api;
import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.bean.BannerBean;
import com.ttd.wanandroid.contract.home.HomeContract;

import io.reactivex.Observable;

/**
 * Created by wt on 2018/7/11.
 */

public class HomeModel implements HomeContract.IHomeModel {
    @Override
    public Observable<BannerBean> getBanner() {
        return RetrofitCreateHelper.init(false).createApi(Api.class,Api.HOST).getBanner()
                .compose(RxHelper.<BannerBean>rxSchedulerHelper());
    }

    @Override
    public Observable<ArticleBean> getArticles(int page) {
        return RetrofitCreateHelper.init(false).createApi(Api.class,Api.HOST).getArticles(page)
                .compose(RxHelper.<ArticleBean>rxSchedulerHelper());
    }
}
