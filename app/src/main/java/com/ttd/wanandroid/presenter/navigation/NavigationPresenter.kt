package com.ttd.wanandroid.presenter.navigation

import com.ttd.wanandroid.bean.NavigationBean
import com.ttd.wanandroid.contract.navigation.NavigationContract
import com.ttd.wanandroid.model.navigation.NavigationModel

/**
 * Created by wt on 2018/7/17.
 */
class NavigationPresenter : NavigationContract.NavigationPresenter() {
    var navigationBean: NavigationBean? = null
    override fun getModel(): NavigationContract.INavigationModel {
        return NavigationModel()
    }

    override fun loadNavigationData() {
        mRxManager.register(mIModel.navigationData.subscribe({
            navigationBean = it
            mIView.showNavigations(it)
        }, {

        }))
    }

    override fun onStart() {
    }

}