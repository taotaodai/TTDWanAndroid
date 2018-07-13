package com.ttd.wanandroid.event;

import com.ttd.wanandroid.bean.ArticleBean;

/**
 * Created by wt on 2018/7/13.
 */

public class ArticleEvent<T> extends BaseEvent<T>{

    public ArticleEvent(int code, int position, T replace) {
        super(code, position, replace);
    }

    public ArticleEvent(int code, T replace) {
        super(code, replace);
    }
}
