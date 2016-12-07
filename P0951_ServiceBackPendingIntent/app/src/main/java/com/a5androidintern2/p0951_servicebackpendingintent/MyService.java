package com.a5androidintern2.p0951_servicebackpendingintent;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";
    ExecutorService es;

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");

        //создаем экзекьютор с двумя потоками.
        //Т.е. когда сервис получит три задачи, он сразу начнет выполнять две из них,
        //а третья будет ждать свободного потока.
        es = Executors.newFixedThreadPool(2);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");


        //вытаскиваем из Intent-а параметр времени для паузы и PendingIntent.
        int time = intent.getIntExtra(MainActivity.PARAM_TIME, 1);
        PendingIntent pi = intent.getParcelableExtra(MainActivity.PARAM_PINTENT);


        //Создаем MyRun и передаем ему эти данные.
        MyRun mr = new MyRun(time, startId, pi);

        //Передаем MyRun экзекьютору на выполнение.
        es.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    class MyRun implements Runnable {

        int time;
        int startId;
        PendingIntent pi;

        public MyRun(int time, int startId, PendingIntent pi) {
            this.time = time;
            this.startId = startId;
            this.pi = pi;
            Log.d(LOG_TAG, "MyRun#" + startId + " create");
        }

        public void run() {
            Log.d(LOG_TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                //сообщаем об старте задачи.
                pi.send(MainActivity.STATUS_START);

                //начинаем выполнение задачи. (эмулируем работу, поставив паузу).
                TimeUnit.SECONDS.sleep(time);

                //сообщаем об окончании задачи.
                //создаем Intent с результатом работыю
                Intent intent = new Intent().putExtra(MainActivity.PARAM_RESULT, time * 100);

                //.send(контекст, тип сообщения, интент)
                pi.send(MyService.this, MainActivity.STATUS_FINISH, intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (CanceledException e) {
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
