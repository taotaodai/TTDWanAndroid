package com.ttd.wanandroid.event;

/**
 * Created by wt on 2018/7/20.
 */

public class NavigationEvent extends BaseEvent{
    private int headerPosition;

    public static final int CODE_CHANGE_CLASSIFY = 101;

    public int getHeaderPosition() {
        return headerPosition;
    }

    public void setHeaderPosition(int headerPosition) {
        this.headerPosition = headerPosition;
    }

    public NavigationEvent(int code, int position, Object replace) {
        super(code, position, replace);
    }

    public NavigationEvent(int code, Object replace) {
        super(code, replace);
    }

    public NavigationEvent(int code) {
        super(code);
    }

    public NavigationEvent(int code,int headerPosition) {
        super(code);
        this.headerPosition = headerPosition;
    }
}
