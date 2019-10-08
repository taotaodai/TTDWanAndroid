package com.ttd.wanandroid.ui

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.chinajey.yiyuntong.openapi.OpenAPI
import com.chinajey.yiyuntong.openapi.listener.AuthListener
import com.google.gson.Gson
import com.ttd.wanandroid.R
import com.ttd.wanandroid.base.BaseMVPCompatActivity
import com.ttd.wanandroid.contract.login.LoginContract
import com.ttd.wanandroid.event.BaseEvent
import com.ttd.wanandroid.event.UserInfoEvent
import com.ttd.wanandroid.presenter.BasePresenter
import com.ttd.wanandroid.presenter.login.LoginPresenter
import org.greenrobot.eventbus.EventBus

/**
 * Created by wt on 2018/7/13.
 */
class LoginActivity : BaseMVPCompatActivity<LoginContract.LoginPresenter, LoginContract.ILoginModel>(), LoginContract.ILoginView {
    override fun showLoginSuccess() {
        showToast("登录成功")
        EventBus.getDefault().post(UserInfoEvent(BaseEvent.CODE_REFRESH))
        finish()
    }

    var btnLogin: View? = null
    var etUsername: EditText? = null
    var etPassword: EditText? = null
    var tbLogin: Toolbar? = null
    var loginDialog: MaterialDialog.Builder? = null

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
        super.initTitleBar(tbLogin, "")
        tbLogin?.setNavigationIcon(R.drawable.ic_cross)

        btnLogin = findViewById(R.id.btn_login)
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin?.setOnClickListener {
            if (loginDialog == null) {
                loginDialog = MaterialDialog.Builder(this)
            }
            loginDialog!!.progress(true, 0)
                    .content("正在登录...")
                    .cancelable(false)
                    .show()
            mPresenter.login(etUsername?.text.toString(), etPassword?.text.toString())

        }

        val btnAuth = findViewById<Button>(R.id.btn_auth)
        val btnUserinfo = findViewById<Button>(R.id.btn_get_userinfo)
        btnAuth.setOnClickListener {
            OpenAPI.get(this).auth(this, object : AuthListener {
                override fun onStart() {
                }

                override fun onComplete(data: MutableMap<String, Any>?) {
                    if(data?.get("code") == 10000){
                        Toast.makeText(this@LoginActivity,"登录成功", Toast.LENGTH_LONG).show()
                        token = (data?.get("access_token") as String?)!!
                        openId = (data?.get("openid") as String?)!!
                    }
                }
                override fun onError(var3: Throwable?) {
                }

            })
        }

        btnUserinfo.setOnClickListener {
            OpenAPI.get(this).getUserInfo(token, openId,object : AuthListener {
                override fun onStart() {
                }

                override fun onComplete(data: MutableMap<String, Any>?) {
                    Toast.makeText(this@LoginActivity, Gson().toJson(data.toString()), Toast.LENGTH_LONG).show()
                }

                override fun onError(var3: Throwable?) {
                }

            })
        }
    }
    var openId = ""
    var token = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }


}