package com.ttd.wanandroid.contract.search;

import com.ttd.wanandroid.base.BaseModel;
import com.ttd.wanandroid.base.IBaseModel;
import com.ttd.wanandroid.base.IBaseView;
import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.bean.HotKeyBean;
import com.ttd.wanandroid.presenter.BasePresenter;

import io.reactivex.Observable;


/**
 * author wt
 * createDate 2018/8/22
 */
public interface SearchContract {
    abstract class SearchPresenter extends BasePresenter<ISearchModel,ISearchView>{
        abstract void loadHotKeys();

        abstract void loadArticles();
    }

    interface ISearchModel extends IBaseModel{
        Observable<HotKeyBean> getHotkeyList();

        Observable<ArticleBean> getArticles(int page,String key);
    }

    interface ISearchView extends IBaseView{

    }
}
