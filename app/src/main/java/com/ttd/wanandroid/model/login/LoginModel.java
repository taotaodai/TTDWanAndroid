package com.ttd.wanandroid.model.login;

import com.ttd.sdk.helper.RetrofitCreateHelper;
import com.ttd.sdk.helper.RxHelper;
import com.ttd.wanandroid.api.Api;
import com.ttd.wanandroid.bean.UserBean;
import com.ttd.wanandroid.contract.login.LoginContract;

import io.reactivex.Observable;

/**
 * Created by lml on 2018/7/13.
 */

public class LoginModel implements LoginContract.ILoginModel{
    @Override
    public Observable<UserBean> login(String username,String password) {
        return RetrofitCreateHelper.init(true).createApi(Api.class, Api.HOST).login(username,password).compose(RxHelper.rxSchedulerHelper());
    }
}
