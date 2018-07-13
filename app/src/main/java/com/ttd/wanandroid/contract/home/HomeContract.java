package com.ttd.wanandroid.contract.home;

import com.ttd.wanandroid.base.IBaseListView;
import com.ttd.wanandroid.base.IBaseModel;
import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.bean.BannerBean;
import com.ttd.wanandroid.presenter.BasePresenter;

import io.reactivex.Observable;

/**
 * Created by wt on 2018/7/11.
 */

public interface HomeContract {
    abstract class HomePresenter extends BasePresenter<IHomeModel, IHomeView> {
        /**
         * 加载首页广告横幅
         */
        public abstract void loadBanner();

        /**
         * 加载首页推荐博文
         */
        public abstract void loadArticles();

        public abstract void loadMoreArticles();
    }

    interface IHomeModel extends IBaseModel{
        Observable<BannerBean> getBanner();

        Observable<ArticleBean> getArticles(int page);
    }

    interface IHomeView extends IBaseListView{
        void showArticles(ArticleBean articleBean);

        void showMoreArticles(ArticleBean articleBean);
    }
}
