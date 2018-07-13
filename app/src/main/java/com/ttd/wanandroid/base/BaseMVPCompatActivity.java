package com.ttd.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ttd.sdk.utils.ToastUtils;
import com.ttd.wanandroid.presenter.BasePresenter;


/**
 * Created by Horrarndoo on 2017/4/6.
 * <p>
 * Mvp Activity基类
 */
public abstract class BaseMVPCompatActivity<P extends BasePresenter, M extends IBaseModel> extends
        BaseCompatActivity implements IBaseView{
    /**
     * presenter 具体的presenter由子类确定
     */
    protected P mPresenter;

    /**
     * model 具体的model由子类确定
     */
    private M mIMode;

    /**
     * 初始化数据
     * <p>
     * 子类可以复写此方法初始化子类数据
     */
    @Override
    protected void initData() {
        super.initData();
        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mIMode = (M) mPresenter.getModel();
            if (mIMode != null) {
                mPresenter.attachMV(mIMode, this);
            }
            //Logger.d("attach M V success.");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
            //Logger.d("detach M V success.");
        }
    }

    public void startNewActivity(@NonNull Class<?> clz) {
        startActivity(clz);
    }

    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        startActivity(clz, bundle);
    }

    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        startActivityForResult(clz, bundle, requestCode);
    }
    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    protected abstract BasePresenter initPresenter();
}
