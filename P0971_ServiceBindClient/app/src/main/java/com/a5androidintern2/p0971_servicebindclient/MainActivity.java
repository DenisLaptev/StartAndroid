package com.a5androidintern2.p0971_servicebindclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    //переменная bound показывает, подключены мы в данный момент к сервису иди нет.
    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //создаём интент, который позволит добраться до сервиса.
        intent = new Intent("com.a5androidintern2.p0972_servicebindserver.MyService");
        intent.setPackage("com.a5androidintern2.p0972_servicebindserver");

        sConn = new ServiceConnection() {
            //объект ServiceConnection позволит нам определить,
            //когда мы подключились к сервису и когда связь с
            //сервисом потеряна (если сервис был убит системой при нехватке памяти).
            public void onServiceConnected(ComponentName name, IBinder binder) {
                //При подключении к сервису сработает метод onServiceConnected.
                //На вход он получает имя компонента-сервиса и
                //объект Binder для взаимодействия с сервисом.
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                bound = true;
            }

            public void onServiceDisconnected(ComponentName name) {
                //При потере связи сработает метод onServiceDisconnected.
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }

    public void onClickStart(View v) {
        //стартуем сервис.
        startService(intent);
    }

    public void onClickStop(View v) {
        //останавливаем сервис.
        stopService(intent);
    }

    public void onClickBind(View v) {
        //соединяемся с сервисом.
        //BIND_AUTO_CREATE - означает, что если сервис,
        //к которому мы пытаемся подключиться, не работает, то он будет запущен.
        bindService(intent, sConn, BIND_AUTO_CREATE);
    }

    public void onClickUnBind(View v) {
        //отсоединяемся от сервиса.
        if (!bound) {
            return;
        }
        unbindService(sConn);
        bound = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        onClickUnBind(null);
    }
}
