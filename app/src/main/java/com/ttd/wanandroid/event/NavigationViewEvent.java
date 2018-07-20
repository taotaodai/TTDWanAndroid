package com.ttd.wanandroid.event;

/**
 * Created by lml on 2018/7/17.
 */

public class NavigationViewEvent extends BaseEvent {

    public static final int CODE_CLOSE = 2;
    public static final int CODE_OPEN = 3;

    public NavigationViewEvent(int code, int position, Object replace) {
        super(code, position, replace);
    }

    public NavigationViewEvent(int code, Object replace) {
        super(code, replace);
    }

    public NavigationViewEvent(int code) {
        super(code);
    }

}
