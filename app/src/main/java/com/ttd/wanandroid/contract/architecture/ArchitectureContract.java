package com.ttd.wanandroid.contract.architecture;

import com.ttd.wanandroid.base.BaseModel;
import com.ttd.wanandroid.base.IBaseModel;
import com.ttd.wanandroid.base.IBaseView;
import com.ttd.wanandroid.bean.ArchitectureBean;
import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.presenter.BasePresenter;

import io.reactivex.Observable;

/**
 * Created by wt on 2018/7/16.
 */

public interface ArchitectureContract {
    abstract class ArchitecturePresenter extends BasePresenter<IArchitectureModel,IArchitectureView> {
        public abstract void loadArchitectureTree();

        public abstract void loadArticleList();

        public abstract void loadMoreArticles();
    }

    interface IArchitectureModel extends IBaseModel{
        Observable<ArchitectureBean> getArchitectureTree();

        Observable<ArticleBean> getArticlesById(int page,int id);

    }

    interface IArchitectureView extends IBaseView{
        void showArticles(ArticleBean articleBean);

        void showMoreArticles(ArticleBean articleBean);

        void showSubtile(String title);
    }
}
