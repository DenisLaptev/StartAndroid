package com.a5androidintern2.p0921_servicesimple;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    //севис надо прописывать в манифесте.

    final String LOG_TAG = "myLogs";

    public void onCreate() {
        //метод срабатывает при создании сервиса.
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        //метод срабатывает, когда сервис запущен методом startService().
        Log.d(LOG_TAG, "onStartCommand");
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        //метод срабатывает при уничтожении сервиса.
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public IBinder onBind(Intent intent) {
        //этот метод нам пока не интересен, но реализовать его мы обязаны.
        Log.d(LOG_TAG, "onBind");
        return null;
    }


    void someTask() {
        //метод, соответствующий работе для сервиса.
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i<=15; i++) {
                    Log.d(LOG_TAG, "i = " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //метод аналогичен методу stopService,
                //он останавливает сервис, в котором был вызван.
                stopSelf();
            }
        }).start();
    }
}