package com.ttd.wanandroid.base;

/**
 * Created by wt on 2017/5/2.
 * fragment base view接口
 */

public interface IBaseView {
    void showModelessLoading();

    void dismissModelessLoading();


    /**
     * 显示toast消息
     *
     * @param msg 要显示的toast消息字符串
     */
    void showToast(String msg);
}
