package com.ttd.wanandroid.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseMVPCompatFragment
import com.ttd.wanandroid.base.IBaseModel
import com.ttd.wanandroid.base.IBaseView
import com.ttd.wanandroid.presenter.BasePresenter
import com.ttd.wanandroid.presenter.home.HomePresenter

/**
 * Created by wt on 2018/7/2.
 */
class SystemFragment : BaseMVPCompatFragment<BasePresenter<IBaseModel,IBaseView>,IBaseModel>(){
    override fun initPresenter(): BasePresenter<*, *> {
        return HomePresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun initUI(view: View?, savedInstanceState: Bundle?) {
    }

}