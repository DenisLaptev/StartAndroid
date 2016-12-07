package com.a5androidintern2.p0891_asynctaskcancel;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    MyTask mt;
    TextView tvInfo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                mt = new MyTask();
                mt.execute();
                break;
            case R.id.btnCancel:
                //по нажатию кнопки btnCancel выполняется метод cancelTask().
                cancelTask();
                break;
            default:
                break;
        }
    }

    private void cancelTask() {
        //по нажатию кнопки btnCancel выполняется метод cancelTask().
        if (mt == null) {
            return;
        }
        //метод cancel на вход принимает boolean-параметр,
        //который указывает, может ли система прервать выполнение потока.

        //Как только мы выполним метод cancel для AsyncTask,
        //isCancelled будет возвращать true.
        //А это значит, что мы должны завершить метод doInBackground.
        Log.d(LOG_TAG, "cancel result: " + mt.cancel(false));
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
            Log.d(LOG_TAG, "Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            //В doInBackground в цикле гоняем паузы и
            //выводим в лог результат метода isCancelled.
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);

                    if (isCancelled()) {
                        return null;
                    }

                    Log.d(LOG_TAG, "isCancelled: " + isCancelled());
                }
            } catch (InterruptedException e) {
                Log.d(LOG_TAG, "Interrupted");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Метод onCancelled вызывается системой вместо onPostExecute,
            //если задача была отменена.
            super.onPostExecute(result);
            tvInfo.setText("End");
            Log.d(LOG_TAG, "End");
        }

        @Override
        protected void onCancelled() {
            //Метод onCancelled вызывается системой вместо onPostExecute,
            //если задача была отменена.
            super.onCancelled();
            tvInfo.setText("Cancel");
            Log.d(LOG_TAG, "Cancel");

        }
    }
}
