package com.ttd.wanandroid.ui

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseMVPCompatActivity
import com.ttd.wanandroid.contract.login.LoginContract
import com.ttd.wanandroid.presenter.BasePresenter
import com.ttd.wanandroid.presenter.login.LoginPresenter

/**
 * Created by wt on 2018/7/13.
 */
class LoginActivity : BaseMVPCompatActivity<LoginContract.LoginPresenter, LoginContract.ILoginModel>(), LoginContract.ILoginView {
    override fun showLoginSuccess() {
        showToast("登录成功")
        finish()
    }

    var btnLogin: Button? = null
    var etUsername: EditText? = null
    var etPassword: EditText? = null
    var tbLogin: Toolbar? = null

    override fun showModelessLoading() {
    }

    override fun dismissModelessLoading() {
    }

    override fun initPresenter(): BasePresenter<*, *> {
        return LoginPresenter()
    }

    override fun initView(savedInstanceState: Bundle?) {
        tbLogin = findViewById(R.id.tb_login)
        initStatusBar(R.id.tb_login)
        super.initTitleBar(tbLogin,"")
        tbLogin?.setNavigationIcon(R.drawable.ic_cross)

        btnLogin = findViewById(R.id.btn_login)
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin?.setOnClickListener {
            mPresenter.login(etUsername?.text.toString(), etPassword?.text.toString())
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }


}