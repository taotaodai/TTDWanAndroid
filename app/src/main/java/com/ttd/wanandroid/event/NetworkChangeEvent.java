package com.ttd.wanandroid.event;

/**
 * author wt
 * createDate 2018/8/3
 */

public class NetworkChangeEvent {
    public boolean isConnected; //是否存在网络

    public NetworkChangeEvent(boolean isConnected) {
        this.isConnected = isConnected;
    }
}
