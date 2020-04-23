package com.ttd.wanandroid.adapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ttd.wanandroid.widget.RvLoadMoreView;

import java.util.List;

/**
 * Created by wt on 2018/6/1.
 */

public abstract class BaseCompatAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T,
        K> {

    public BaseCompatAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        initFooter();
    }

    public BaseCompatAdapter(@Nullable List<T> data) {
        super(data);
        initFooter();
    }

    public BaseCompatAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        initFooter();
    }

    public void initFooter(){
        setLoadMoreView(new RvLoadMoreView());
        setEnableLoadMore(true);
        openLoadAnimation();//开启默认动画载入（仅开启加载新item时开启动画）
    }
}
