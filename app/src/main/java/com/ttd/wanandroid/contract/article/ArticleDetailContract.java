package com.ttd.wanandroid.contract.article;

import com.ttd.wanandroid.bean.Article;
import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.bean.BaseBean;
import com.ttd.wanandroid.contract.BaseWebViewLoadContract;

import io.reactivex.Observable;

/**
 * Created by wt on 2018/7/12.
 */

public interface ArticleDetailContract {
    abstract class ArticleDetailPresenter  extends BaseWebViewLoadContract.BaseWebViewLoadPresenter<IArticleDetailModel,IArticleDetailView> {
        public abstract void loadArticleDetail(String url);

        public abstract void collectArticle(Article article);

    }

    interface IArticleDetailModel extends BaseWebViewLoadContract.IBaseWebViewLoadModel {
        Observable<BaseBean> collectArticle(int id);

        Observable<BaseBean> uncollectArticle(int id);
    }

    interface IArticleDetailView extends BaseWebViewLoadContract.IBaseWebViewLoadView {
        void showArticleDetail(String url);

        void showCollectResult(boolean collect);

        void gotoLogin();
    }
}
