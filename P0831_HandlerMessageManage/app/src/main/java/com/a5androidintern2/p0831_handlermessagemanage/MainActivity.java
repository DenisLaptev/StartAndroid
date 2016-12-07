package com.a5androidintern2.p0831_handlermessagemanage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    Handler h;

    //создаём объект hc типа Handler.Callback.
    Handler.Callback hc = new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            //метод обрабатывает сообщения.

            //в данном случае читаем атрибут what и выводим в лог.
            Log.d(LOG_TAG, "what = " + msg.what);
            return false;
        }
    };

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //хендлер будет теперь обрабатывать сообщения не сам, а перепоручит это объекту hc.
        h = new Handler(hc);
        sendMessages();
    }

    void sendMessages() {

        //метод кладет три сообщения в очередь сообщений.
        Log.d(LOG_TAG, "send messages");
        h.sendEmptyMessageDelayed(1, 1000); //what = 1, обработка через 1000 мс.
        h.sendEmptyMessageDelayed(2, 2000); //what = 2, обработка через 2000 мс.
        h.sendEmptyMessageDelayed(3, 3000); //what = 3, обработка через 3000 мс.

/*
        //метод удалит ВСЕ сообщения с what = 2.
        h.removeMessages(2);
*/

    }
}
