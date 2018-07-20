package com.ttd.wanandroid.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ttd.sdk.GlobalApplication;
import com.ttd.sdk.utils.AppUtils;
import com.ttd.sdk.wrappers.statusbar.ImmersionBarFactory;
import com.ttd.sdk.wrappers.statusbar.ImmersionBarImp;
import com.ttd.sdk.wrappers.statusbar.StatusBarOptions;
import com.ttd.wanandroid.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


/**
 * Created by Horrarndoo on 2017/9/7.
 * <p>
 * BaseActivity
 */

public abstract class BaseCompatActivity extends me.yokeyword.fragmentation.SupportActivity {
    protected GlobalApplication mApplication;
    protected Context mContext;//全局上下文对象
    protected boolean isTransAnim;
    protected ImmersionBarImp immersionBarImp;

    static {
        //5.0以下兼容vector
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    public void initStatusBar() {
        immersionBarImp = new ImmersionBarImp();
        StatusBarOptions options ;
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            options = new StatusBarOptions.Builder()
                    .setStatusBarColor(R.color.dark_20)
                    .build();
        }else {
            options = new StatusBarOptions.Builder()
                    .build();
        }
        immersionBarImp.initImmersionBar(this, options);
    }

    protected void initStatusBarByView(@IdRes int id) {
        immersionBarImp = new ImmersionBarImp();
        StatusBarOptions options;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            options = new StatusBarOptions.Builder()
                    .setDarkFont(true)
                    .setStatusBarViewId(id)
                    .setStatusBarColor(R.color.dark_20).build();
        } else {
            options = new StatusBarOptions.Builder()
                    .setDarkFont(true)
                    .setStatusBarViewId(id).build();

        }
        immersionBarImp.initImmersionBar(this, options);
    }

    public void initStatusBar(@IdRes int titleBarId) {
        immersionBarImp = new ImmersionBarImp();
        StatusBarOptions options = new StatusBarOptions.Builder()
                .setTitleBarId(titleBarId)
                .build();
        immersionBarImp.initImmersionBar(this, options);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void init(Bundle savedInstanceState) {
        setContentView(getLayoutId());
        initStatusBar();
        initData();
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView(savedInstanceState);
    }

    protected void initData() {
        mContext = AppUtils.getContext();
        mApplication = (GlobalApplication) getApplication();
//        mWaitPorgressDialog = new WaitPorgressDialog(this);
        isTransAnim = true;
    }

    protected void initTitleBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
//        if (isTransAnim)
//            overridePendingTransition(R.anim.activity_start_zoom_in, R.anim
//                    .activity_start_zoom_out);
    }

    /**
     * [页面跳转]
     *
     * @param clz 要跳转的Activity
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
//        if (isTransAnim)
//            overridePendingTransition(R.anim.activity_start_zoom_in, R.anim
//                    .activity_start_zoom_out);
    }

    /**
     * [页面跳转]
     *
     * @param clz    要跳转的Activity
     * @param intent intent
     */
    public void startActivity(Class<?> clz, Intent intent) {
        intent.setClass(this, clz);
        startActivity(intent);
//        if (isTransAnim)
//            overridePendingTransition(R.anim.activity_start_zoom_in, R.anim
//                    .activity_start_zoom_out);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param clz         要跳转的Activity
     * @param bundle      bundel数据
     * @param requestCode requestCode
     */
    public void startActivityForResult(Class<?> clz, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
//        if (isTransAnim)
//            overridePendingTransition(R.anim.activity_start_zoom_in, R.anim
//                    .activity_start_zoom_out);
    }


    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    /**
     * 初始化view
     * <p>
     * 子类实现 控件绑定、视图初始化等内容
     *
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 获取当前layouty的布局ID,用于设置当前布局
     * <p>
     * 交由子类实现
     *
     * @return layout Id
     */
    protected abstract int getLayoutId();


    /**
     * 隐藏键盘
     *
     * @return 隐藏键盘结果
     * <p>
     * true:隐藏成功
     * <p>
     * false:隐藏失败
     */
    protected boolean hiddenKeyboard() {
        //点击空白位置 隐藏软键盘
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService
                (INPUT_METHOD_SERVICE);
        return mInputMethodManager.hideSoftInputFromWindow(this
                .getCurrentFocus().getWindowToken(), 0);
    }


    /**
     * 是否使用overridePendingTransition过度动画
     *
     * @return 是否使用overridePendingTransition过度动画，默认使用
     */
    protected boolean isTransAnim() {
        return isTransAnim;
    }

    /**
     * 设置是否使用overridePendingTransition过度动画
     */
    protected void setIsTransAnim(boolean b) {
        isTransAnim = b;
    }
}
