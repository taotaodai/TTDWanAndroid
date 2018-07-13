package com.ttd.wanandroid.event;

/**
 * Created by wt on 2018/7/13.
 */

public class BaseEvent<T> {
    private int code = CODE_REFRESH;
    private int position = 0;
    private T replace;

    public static int getCodeRefresh() {
        return CODE_REFRESH;
    }

    public T getReplace() {
        return replace;
    }

    public static final int CODE_REFRESH = 0;
    public static final int CODE_REPLACE = 1;

    public BaseEvent(int code, int position, T replace) {
        this.code = code;
        this.position = position;
        this.replace = replace;
    }

    public BaseEvent(int code, T replace) {
        this.code = code;
        this.replace = replace;
    }

    public BaseEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public int getPosition() {
        return position;
    }
}
