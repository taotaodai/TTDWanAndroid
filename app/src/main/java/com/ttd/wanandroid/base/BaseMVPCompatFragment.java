package com.ttd.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.ttd.sdk.utils.ToastUtils;
import com.ttd.wanandroid.presenter.BasePresenter;


/**
 * Created by Horrarndoo on 2017/9/6.
 * <p>
 * Mvp Fragment基类
 * <p>
 * 实现IBaseView方法、绑定butterknife
 */

public abstract class BaseMVPCompatFragment<P extends BasePresenter, M extends IBaseModel> extends
        BaseCompatFragment {
    public P mPresenter;
    public M mIMode;

    /**
     * 在监听器之前把数据准备好
     */
    @Override
    public void initData() {
        super.initData();

        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mIMode = (M) mPresenter.getModel();
            if (mIMode != null) {
                mPresenter.attachMV(mIMode, this);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
        }
    }

    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg, Toast.LENGTH_SHORT);
    }

    public void startNewActivity(@NonNull Class<?> clz) {
        ((BaseCompatActivity) mActivity).startActivity(clz);
    }

    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        ((BaseCompatActivity) mActivity).startActivity(clz, bundle);
    }

    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        ((BaseCompatActivity) mActivity).startActivityForResult(clz, bundle, requestCode);
    }

    protected abstract BasePresenter initPresenter();
}