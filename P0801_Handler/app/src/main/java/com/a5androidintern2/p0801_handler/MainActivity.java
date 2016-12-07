package com.a5androidintern2.p0801_handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    Handler h;
    TextView tvInfo;
    Button btnStart;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
        btnStart = (Button) findViewById(R.id.btnStart);


        //создаем Handler и в нем реализуем метод обработки сообщений handleMessage.
        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                //обновляем TextView.

                //извлекаем из сообщения атрибут what – это кол-во закачанных файлов.
                tvInfo.setText("Закачано файлов: " + msg.what);

                //Если оно what=10, т.е. все файлы закачаны, мы активируем кнопку Start.
                if (msg.what == 10) btnStart.setEnabled(true);
            };
        };
    }

    public void onclick(View v) {
        //помещаем весь цикл в новый поток и запускаем его.
        //Теперь закачка файлов пойдет в этом новом потоке.
        //А основной поток будет не занят и сможет без проблем
        //прорисовывать экран и реагировать на нажатия.
        switch (v.getId()) {
            case R.id.btnStart:

                //Мы деактивируем кнопку Start перед запуском закачки файлов.
                //Это просто защита, чтобы нельзя было запустить несколько
                //закачек одновременно.
                btnStart.setEnabled(false);
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        for (int i = 1; i <= 10; i++) {
                            //долгий процесс
                            downloadFile();

                            //в процессе закачки, после каждого закачанного файла,
                            //отправляем (sendEmptyMessage) для Handler сообщение
                            //с кол-вом уже закачанных файлов.
                            //Handler это сообщение примет, извлечет из него кол-во
                            //файлов и обновит TextView.
                            h.sendEmptyMessage(i);
                            //пишем лог
                            Log.d(LOG_TAG, "i = " + i);
                        }
                    }
                });
                t.start();
                break;
            case R.id.btnTest:
                Log.d(LOG_TAG, "test");
                break;
            default:
                break;
        }
    }

    void downloadFile() {
        //пауза - 1 секунда.
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
