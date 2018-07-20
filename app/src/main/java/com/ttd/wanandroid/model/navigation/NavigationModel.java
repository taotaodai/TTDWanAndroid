package com.ttd.wanandroid.model.navigation;

import com.ttd.sdk.helper.RetrofitCreateHelper;
import com.ttd.sdk.helper.RxHelper;
import com.ttd.wanandroid.api.Api;
import com.ttd.wanandroid.bean.NavigationBean;
import com.ttd.wanandroid.contract.navigation.NavigationContract;

import io.reactivex.Observable;

/**
 * Created by wt on 2018/7/17.
 */

public class NavigationModel implements NavigationContract.INavigationModel{
    @Override
    public Observable<NavigationBean> getNavigationData() {
        return RetrofitCreateHelper.init(false).createApi(Api.class,Api.HOST).getNavigationData().compose(RxHelper.rxSchedulerHelper());
    }
}
