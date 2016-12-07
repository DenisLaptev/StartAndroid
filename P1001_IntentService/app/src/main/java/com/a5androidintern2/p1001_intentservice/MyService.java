package com.a5androidintern2.p1001_intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends IntentService {

    final String LOG_TAG = "myLogs";

    public MyService() {
        //имя "myname" будет использовано для наименования потока.
        super("myname");
    }

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //в методе кодим обработку Intent-ов.

        //достаём из интента "time" и "label".
        int tm = intent.getIntExtra("time", 0);
        String label = intent.getStringExtra("label");

        //выводим в лог label в начале.
        Log.d(LOG_TAG, "onHandleIntent start " + label);
        try {
            //запускаем паузу на time секунд.
            TimeUnit.SECONDS.sleep(tm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //выводим в лог label в конце.
        Log.d(LOG_TAG, "onHandleIntent end " + label);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}