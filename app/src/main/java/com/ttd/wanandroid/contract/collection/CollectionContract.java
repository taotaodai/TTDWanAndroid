package com.ttd.wanandroid.contract.collection;

import com.ttd.wanandroid.base.IBaseModel;
import com.ttd.wanandroid.base.IBaseView;
import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.presenter.BasePresenter;

import io.reactivex.Observable;

/**
 * author wt
 * createDate 2018/8/9
 */

public interface CollectionContract {
    abstract class CollectionPresenter extends BasePresenter<ICollectionModel, ICollectionView> {
        public abstract void loadArticles();

        public abstract void loadMoreArticles();

        public abstract void refresh();
    }

    interface ICollectionModel extends IBaseModel {
        Observable<ArticleBean> getArticles(int page);
    }

    interface ICollectionView extends IBaseView {
        void showArticles(ArticleBean articleBean);

        void showMoreArticles(ArticleBean articleBean);
    }
}
