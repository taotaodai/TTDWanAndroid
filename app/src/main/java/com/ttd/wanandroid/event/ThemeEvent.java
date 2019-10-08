package com.ttd.wanandroid.event;

/**
 * author wt
 * createDate 2018/8/8
 */

public class ThemeEvent extends BaseEvent{
    public static final int CODE_NIGHT_MODE = 101;
    public static final int CODE_DAY_MODE = 102;

    public ThemeEvent(int code, int position, Object replace) {
        super(code, position, replace);
    }

    public ThemeEvent(int code, Object replace) {
        super(code, replace);
    }

    public ThemeEvent(int code) {
        super(code);
    }
}
