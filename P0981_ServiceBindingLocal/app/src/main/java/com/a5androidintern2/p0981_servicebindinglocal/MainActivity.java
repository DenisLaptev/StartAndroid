package com.a5androidintern2.p0981_servicebindinglocal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;
    MyService myService;
    TextView tvInterval;
    long interval;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInterval = (TextView) findViewById(R.id.tvInterval);

        //создаем Intent для доступа к сервису.
        intent = new Intent(this, MyService.class);

        //создаем ServiceConnection.
        sConn = new ServiceConnection() {

            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");

                //берем binder, преобразуем его к MyService.MyBinder,
                //вызываем метод getService и получаем наш сервис MyService.
                myService = ((MyService.MyBinder) binder).getService();
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }

    @Override
    protected void onStart() {
        //подключаемся к сервису.
        super.onStart();
        bindService(intent, sConn, 0);
    }

    @Override
    protected void onStop() {
        //отключаемся от сервиса.
        super.onStop();
        if (!bound) return;
        unbindService(sConn);
        bound = false;
    }

    public void onClickStart(View v) {
        //запускаем сервис.
        startService(intent);
    }

    public void onClickUp(View v) {
        //проверяем, что соединение с сервисом есть,
        //и вызываем метод сервиса для увеличения интервала.
        if (!bound) {
            return;
        }

        //изменяем на 500.
        interval = myService.upInterval(500);
        tvInterval.setText("interval = " + interval);
    }

    public void onClickDown(View v) {
        //проверяем, что соединение с сервисом есть,
        //и вызываем метод сервиса для понижения интервала.
        if (!bound) {
            return;
        }

        //изменяем на 500.
        interval = myService.downInterval(500);
        tvInterval.setText("interval = " + interval);
    }
}
