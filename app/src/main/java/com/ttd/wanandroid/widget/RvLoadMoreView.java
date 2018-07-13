package com.ttd.wanandroid.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.ttd.wanandroid.R;

/**
 * Created by wt on 2018/6/1.
 */

public class RvLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.footer_view_loadmore;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
