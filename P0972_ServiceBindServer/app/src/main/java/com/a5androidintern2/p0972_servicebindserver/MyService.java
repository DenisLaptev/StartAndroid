package com.a5androidintern2.p0972_servicebindserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";

    public void onCreate() {
        //метод вызывается при создании сервиса.
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
    }

    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "MyService onBind");
        //возвращаем объект-заглушку. В этом уроке он не будет использован.
        return new Binder();
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(LOG_TAG, "MyService onRebind");
    }

    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG, "MyService onUnbind");
        return super.onUnbind(intent);
    }

    public void onDestroy() {
        //метод вызывается при уничтожении сервиса.
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }
}
