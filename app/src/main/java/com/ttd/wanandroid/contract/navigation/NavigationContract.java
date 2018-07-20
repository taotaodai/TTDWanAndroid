package com.ttd.wanandroid.contract.navigation;

import com.ttd.wanandroid.base.IBaseModel;
import com.ttd.wanandroid.base.IBaseView;
import com.ttd.wanandroid.bean.NavigationBean;
import com.ttd.wanandroid.presenter.BasePresenter;

import io.reactivex.Observable;

/**
 * Created by wt on 2018/7/17.
 */

public interface NavigationContract {
    abstract class NavigationPresenter extends BasePresenter<INavigationModel,INavigationView>{
        public abstract void loadNavigationData();
    }

    interface INavigationModel extends IBaseModel{
        Observable<NavigationBean> getNavigationData();
    }

    interface INavigationView extends IBaseView{
        void showNavigations(NavigationBean navigationBean);
    }
}
