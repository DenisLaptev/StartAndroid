package com.a5androidintern2.p0841_handlerrunnable;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    ProgressBar pbCount;
    TextView tvInfo;
    CheckBox chbInfo;
    int cnt;

    final String LOG_TAG = "myLogs";
    final int max = 100;

    Handler h;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h = new Handler();

        pbCount = (ProgressBar) findViewById(R.id.pbCount);
        pbCount.setMax(max);
        pbCount.setProgress(0);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        chbInfo = (CheckBox) findViewById(R.id.chbInfo);
        //прописываем обработчик для CheckBox.
        chbInfo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tvInfo.setVisibility(View.VISIBLE);
                    //показываем информацию.
                    //в очередь отправляется задание showInfo.
                    h.post(showInfo);
                } else {
                    tvInfo.setVisibility(View.GONE);
                    //отменяем показ информации.
                    //из очереди убирается задание showInfo.
                    h.removeCallbacks(showInfo);
                }
            }
        });

        Thread t = new Thread(new Runnable() {
            //в новом потоке эмулируем какое-либо действие - запускаем счетчик с паузами.

            public void run() {
                try {
                    for (cnt = 1; cnt < max; cnt++) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        //обновляем ProgressBar.

                        //с каждой итерацией цикла запускаем в работу команду updateProgress,
                        //которая обновляет ProgressBar.
                        h.post(updateProgress);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }

    //обновление ProgressBar.
    Runnable updateProgress = new Runnable() {
        //код, который обновляет значение ProgressBar.
        public void run() {
            pbCount.setProgress(cnt);
        }
    };

    //показ информации.
    Runnable showInfo = new Runnable() {
        //код, который обновляет TextView
        public void run() {
            Log.d(LOG_TAG, "showInfo");
            tvInfo.setText("Count = " + cnt);
            //планирует сам себя через 1000 милисекунд.
            h.postDelayed(showInfo, 1000);
        }
    };
}
