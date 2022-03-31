package com.ttd.wanandroid.base;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.ImmersionBar;
import com.ttd.sdk.GlobalApplication;
import com.ttd.sdk.utils.AppUtils;
import com.ttd.wanandroid.R;
import com.ttd.wanandroid.event.NetworkChangeEvent;
import com.ttd.wanandroid.event.ThemeEvent;
import com.ttd.wanandroid.receiver.NetworkConnectChangedReceiver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by wt on 2017/9/7.
 */

public abstract class BaseCompatActivity extends me.yokeyword.fragmentation.SupportActivity {
    protected GlobalApplication mApplication;
    protected Context mContext;//全局上下文对象
    protected boolean isTransAnim;
    protected ImmersionBar mImmersionBar;
    protected NetworkConnectChangedReceiver netWorkStateReceiver;
    protected boolean mCheckNetWork = true; //默认检查网络状态

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
        mImmersionBar = ImmersionBar.with(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mImmersionBar.statusBarColor(R.color.dark_20)
                    .init();
        } else {
            mImmersionBar.statusBarDarkFont(false);
            mImmersionBar.init();
        }
    }

    public void initStatusBar(Toolbar toolbar) {
        mImmersionBar = ImmersionBar.with(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mImmersionBar.statusBarColor(R.color.dark_20)
                    .init();
        } else {

            mImmersionBar.titleBar(toolbar, false)
                    .statusBarDarkFont(false)
                    .transparentBar()
                    .init();
        }
    }

    protected void initStatusBarByView(@IdRes int id) {
        mImmersionBar = ImmersionBar.with(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mImmersionBar.statusBarDarkFont(true)
                    .statusBarView(id)
                    .statusBarColor(R.color.dark_20)
                    .init();
        } else {
            mImmersionBar.statusBarDarkFont(false)
                    .statusBarView(id)
                    .init();
        }
    }

    public void initStatusBar(@IdRes int titleBarId) {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.titleBar(titleBarId)
                .statusBarDarkFont(false)
                .init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(netWorkStateReceiver);
        mImmersionBar.destroy();
    }

    @Override
    protected void onResume() {
        netWorkStateReceiver = new NetworkConnectChangedReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        super.onResume();
    }

    private void init(Bundle savedInstanceState) {
        setContentView(getLayoutId());
        initStatusBar();
        initData();
        initTipView();
        EventBus.getDefault().register(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView(savedInstanceState);
    }

    protected void initData() {
        mContext = AppUtils.getContext();
        mApplication = (GlobalApplication) getApplication();
        isTransAnim = true;
    }

    protected void initTitleBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(view -> onBackPressedSupport());
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

    public void setCheckNetWork(boolean checkNetWork) {
        mCheckNetWork = checkNetWork;
    }

    public boolean isCheckNetWork() {
        return mCheckNetWork;
    }

    View mTipView;
    WindowManager mWindowManager;
    WindowManager.LayoutParams mLayoutParams;

    private void initTipView() {
        LayoutInflater inflater = getLayoutInflater();
        //提示View布局
        mTipView = inflater.inflate(R.layout.view_network_tip, null);
        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        //使用非CENTER时，可以通过设置XY的值来改变View的位置
        mLayoutParams.gravity = Gravity.TOP;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkChangeEvent(NetworkChangeEvent event) {
        hasNetWork(event.isConnected);
    }

    private void hasNetWork(boolean has) {
        if (isCheckNetWork()) {
            if (has) {
                if (mTipView != null && mTipView.getParent() != null) {
                    mWindowManager.removeView(mTipView);
                }
            } else {
//                SnackbarUtils.with(getWindow().getDecorView())
//                        .setMessage("网络异常")
//                        .setMessageColor(Color.WHITE)
////                .setBgResource(R.drawable.shape_top_round_rect)
//                        .setAction("aaa", Color.YELLOW, v -> Toast.makeText(mContext,"aaaa",Toast.LENGTH_SHORT).show())
//                        .show();
                if (mTipView.getParent() == null) {
                    mWindowManager.addView(mTipView, mLayoutParams);
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void globalThemeEvent(ThemeEvent event) {
        switch (event.getCode()) {
            case ThemeEvent.CODE_NIGHT_MODE:
                mImmersionBar.statusBarDarkFont(false);
                mImmersionBar.init();
                break;
            case ThemeEvent.CODE_DAY_MODE:
                mImmersionBar.statusBarDarkFont(true);
                mImmersionBar.init();
                break;
            default:
                break;
        }
    }
}
