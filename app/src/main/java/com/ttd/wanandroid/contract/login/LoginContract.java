package com.ttd.wanandroid.contract.login;

import com.ttd.wanandroid.base.IBaseModel;
import com.ttd.wanandroid.base.IBaseView;
import com.ttd.wanandroid.bean.UserBean;
import com.ttd.wanandroid.presenter.BasePresenter;

import io.reactivex.Observable;

/**
 * Created by wt on 2018/7/13.
 */

public interface LoginContract {
    abstract class LoginPresenter extends BasePresenter<ILoginModel,ILoginView> {
        public abstract void login(String username,String password);
    }

    interface ILoginModel extends IBaseModel{
        Observable<UserBean> login(String username, String password);
    }

    interface ILoginView extends IBaseView{
        void showLoginSuccess();
    }
}
