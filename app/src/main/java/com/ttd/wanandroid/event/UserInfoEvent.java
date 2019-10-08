package com.ttd.wanandroid.event;

/**
 * author wt
 * createDate 2018/8/1
 */

public class UserInfoEvent extends BaseEvent{
    public UserInfoEvent(int code, int position, Object replace) {
        super(code, position, replace);
    }

    public UserInfoEvent(int code, Object replace) {
        super(code, replace);
    }

    public UserInfoEvent(int code) {
        super(code);
    }
}
