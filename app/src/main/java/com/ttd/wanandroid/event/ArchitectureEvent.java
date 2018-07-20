package com.ttd.wanandroid.event;

/**
 * Created by wt on 2018/7/17.
 */

public class ArchitectureEvent extends BaseEvent{


    public ArchitectureEvent(int code, int position, Object replace) {
        super(code, position, replace);
    }

    public ArchitectureEvent(int code, Object replace) {
        super(code, replace);
    }

    public ArchitectureEvent(int code) {
        super(code);
    }

}
