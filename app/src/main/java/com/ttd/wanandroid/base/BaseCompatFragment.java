package com.ttd.wanandroid.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ttd.sdk.GlobalApplication;
import com.ttd.wanandroid.R;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * Created by Horrarndoo on 2017/9/26.
 * <p>
 */

public abstract class BaseCompatFragment extends SupportFragment {

    protected String TAG;
    protected Context mContext;
    protected Activity mActivity;
    protected GlobalApplication mApplication;
    protected View vLoading;
    protected View vEmpty;
    protected View vServerAbnormal;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vLoading = LayoutInflater.from(getContext()).inflate(R.layout.view_loading_shape, null);
        vEmpty = LayoutInflater.from(getContext()).inflate(R.layout.view_empty, null);
        vServerAbnormal = LayoutInflater.from(getContext()).inflate(R.layout.view_server_abnormal,null);
    }


    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        } else {
            //            return inflater.inflate(getLayoutId(), null);
            return inflater.inflate(getLayoutId(), container, false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = getClass().getSimpleName();
        getBundle(getArguments());
        initData();
        initUI(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @LayoutRes
    public abstract int getLayoutId();

    public View getLayoutView() {
        return null;
    }

    /**
     * 得到Activity传进来的值
     */
    public void getBundle(Bundle bundle) {
    }

    /**
     * 初始化UI
     */
    public abstract void initUI(View view, @Nullable Bundle savedInstanceState);

    /**
     * 在监听器之前把数据准备好
     */
    public void initData() {
        mApplication = (GlobalApplication) mActivity.getApplication();
    }

}
