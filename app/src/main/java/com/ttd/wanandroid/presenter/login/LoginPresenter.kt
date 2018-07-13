package com.ttd.wanandroid.presenter.login

import com.ttd.wanandroid.constant.Constant
import com.ttd.wanandroid.contract.login.LoginContract
import com.ttd.wanandroid.model.login.LoginModel

/**
 * Created by wt on 2018/7/13.
 */
class LoginPresenter: LoginContract.LoginPresenter() {
    override fun login(username: String?, password: String?) {
//        val map = HashMap<String,String>()
//        map["username"] = username!!
//        map["password"] = password!!

        mRxManager.register(mIModel.login(username,password).subscribe({
            if(it.errorCode == Constant.ServerRespCode.OK){
                mIView.showLoginSuccess()
            }
        },{

        }))
    }

    override fun getModel(): LoginContract.ILoginModel {
        return LoginModel()
    }

    override fun onStart() {
    }

}