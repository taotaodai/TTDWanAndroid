package com.ttd.wanandroid.contract;

import com.ttd.wanandroid.bean.BaseBean;

import io.reactivex.Observable;

/**
 * Created by wt on 2018/7/12.
 */

public interface ArticleDetailContract {
    abstract class ArticleDetailPresenter  extends BaseWebViewLoadContract.BaseWebViewLoadPresenter<IArticleDetailModel,IArticleDetailView> {
        public abstract void loadArticleDetail(String url);

        public abstract void collectArticle(int id);
    }

    interface IArticleDetailModel extends BaseWebViewLoadContract.IBaseWebViewLoadModel {
        Observable<BaseBean> collectArticle(int id);
    }

    interface IArticleDetailView extends BaseWebViewLoadContract.IBaseWebViewLoadView {
        void showArticleDetail(String url);

        void showCollectResult(boolean collect);
    }
}
