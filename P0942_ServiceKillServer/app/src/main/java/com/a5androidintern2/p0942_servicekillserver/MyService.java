package com.a5androidintern2.p0942_servicekillserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand, name = " + intent.getStringExtra("name"));
        readFlags(flags);

        //создаём экземпляр MyRun и отправляем его работать в новый поток.
        MyRun mr = new MyRun(startId);
        new Thread(mr).start();
        //return START_NOT_STICKY;//сервис не будет перезапущен после того, как будет убит.

        //return START_STICKY;//сервис будет перезапущен после того, как будет убит.

        //Для того, чтобы вызов startService,
        //который получил сервер в прошлый раз,
        //не терялся, нужна константа START_REDELIVER_INTENT.
        return START_REDELIVER_INTENT;
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    void readFlags(int flags) {
        //в методе смотрим какие флаги были в flags и отмечаем это в логе.

        if ((flags&START_FLAG_REDELIVERY) == START_FLAG_REDELIVERY) {
            Log.d(LOG_TAG, "START_FLAG_REDELIVERY");
        }
        if ((flags&START_FLAG_RETRY) == START_FLAG_RETRY) {
            Log.d(LOG_TAG, "START_FLAG_RETRY");
        }
    }

    class MyRun implements Runnable {
        //класс эмулирует долгую работу.

        int startId;

        public MyRun(int startId) {
            this.startId = startId;
            Log.d(LOG_TAG, "MyRun#" + startId + " create");
        }

        public void run() {
            Log.d(LOG_TAG, "MyRun#" + startId + " start");
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop();
        }

        void stop() {
            Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelfResult("
                    + startId + ") = " + stopSelfResult(startId));
        }
    }
}
