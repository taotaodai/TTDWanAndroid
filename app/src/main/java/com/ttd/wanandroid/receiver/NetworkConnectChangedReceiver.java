package com.ttd.wanandroid.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ttd.sdk.utils.NetworkConnectionUtils;
import com.ttd.wanandroid.event.NetworkChangeEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * author wt
 * createDate 2018/8/3
 */

public class NetworkConnectChangedReceiver extends BroadcastReceiver {
    private static final String TAG = "NetworkConnectChanged";
    @Override
    public void onReceive(Context context, Intent intent) {
        //**判断当前的网络连接状态是否可用*/
        boolean isConnected = NetworkConnectionUtils.isConnected(context);
        Log.d(TAG, "onReceive: 当前网络 " + isConnected);
        EventBus.getDefault().post(new NetworkChangeEvent(isConnected));
    }
}
