package com.ttd.wanandroid.base;

/**
 * Created by wt on 2018/5/17.
 */

public interface IBaseListView extends IBaseView {
    void showEmptyView();

    void showNoMoreView(boolean gone);

    void showRefresh();

    void showRefreshView(boolean refreshing);
}
