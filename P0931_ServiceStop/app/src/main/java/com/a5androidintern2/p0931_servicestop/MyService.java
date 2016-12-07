package com.a5androidintern2.p0931_servicestop;

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
    Object someRes;

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");

        //экзекутор. Он будет получать задачи и запускать их в одном потоке.
        //1 - означает, что вызовы происходят в одном потоке, по очереди
        //3 - означает, что вызовы происходят каждый в своём потоке, параллельно
        es = Executors.newFixedThreadPool(3);

        //создаём некоторый объект Object someRes.
        //этот объект будет использоваться сервисом при обработке вызовов.
        someRes = new Object();
    }

    public void onDestroy() {
        //метод обнуляет someRes.

        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
        someRes = null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");

        //читаем из intent параметр "time".
        int time = intent.getIntExtra("time", 1);

        //создаём Runnable-объект MyRun mr. Передаём ему time и startId.
        MyRun mr = new MyRun(time, startId);

        //отдаём объект MyRun mr экзекутору. Экзекутор запустит объект mr в отдельном потоке.
        es.execute(mr);
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    class MyRun implements Runnable {
        //MyRun – это Runnable-объект. Он и будет обрабатывать входящие вызовы сервиса.


        //в лог выводим сообщения о создании, старте и завершении работы.
        int time;//количество секунд паузы.
        int startId;//для метода startSelf().

        public MyRun(int time, int startId) {
            this.time = time;
            this.startId = startId;
            Log.d(LOG_TAG, "MyRun#" + startId + " create");
        }

        public void run() {
            Log.d(LOG_TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Log.d(LOG_TAG, "MyRun#" + startId + " someRes = " + someRes.getClass() );
            } catch (NullPointerException e) {
                Log.d(LOG_TAG, "MyRun#" + startId + " error, null pointer");
            }
            stop();
        }


        void stop() {
            Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelfResult("
                    + startId + ") = " + stopSelfResult(startId));
        }
    }
}