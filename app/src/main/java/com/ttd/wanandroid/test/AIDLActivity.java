package com.ttd.wanandroid.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ttd.test.aidl.PersonManager;


/**
 * author wt
 * createDate 2018/8/10
 */

public class AIDLActivity extends AppCompatActivity {
    private PersonManager personManager = null;

    @Override
    protected void onStart() {
        super.onStart();
        attemptToBindService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            personManager = PersonManager.Stub.asInterface(service);
            if (personManager != null) {
                try {
                    Toast.makeText(AIDLActivity.this, personManager.getPerson().getName(), Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    private void attemptToBindService() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        ComponentName componentName = new ComponentName(
                "com.ttd.test",
                "com.ttd.test.aidl.MyService");

//        intent.setAction("com.ttd.test.aidl.MyService");
//        intent.setPackage("com.ttd.test.aidl");
//        intent.setPackage("com.ttd.wanandroid");
        intent.setComponent(componentName);
        this.getApplicationContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
}
