package com.ttd.wanandroid.model;

import com.ttd.sdk.helper.RetrofitCreateHelper;
import com.ttd.sdk.helper.RxHelper;
import com.ttd.wanandroid.api.Api;
import com.ttd.wanandroid.bean.BaseBean;
import com.ttd.wanandroid.bean.UserBean;
import com.ttd.wanandroid.contract.ArticleDetailContract;

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
}
