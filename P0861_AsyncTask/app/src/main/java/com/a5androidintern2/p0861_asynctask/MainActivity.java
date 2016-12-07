package com.a5androidintern2.p0861_asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

/*
    Официальный хелп дает 4 правила использования AsyncTask:

- объект AsyncTask должен быть создан в UI-потоке
- метод execute должен быть вызван в UI-потоке
- не вызывайте напрямую методы onPreExecute,
  doInBackground, onPostExecute и onProgressUpdate (последний мы пока не проходили)
- AsyncTask может быть запущен (execute) только один раз, иначе будет exception
*/



    MyTask mt;
    TextView tvInfo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void onclick(View v) {
        mt = new MyTask();
        //запуск объекта MyTask mt.
        mt.execute();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        //Мы получили работающую в новом потоке задачу и доступ к UI.
        //При этом сами не создавали потоков и Handler, за нас все сделал AsyncTask.

        @Override
        protected void onPreExecute() {
            //этот метод реализовываем по желанию.
            super.onPreExecute();
            tvInfo.setText("Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            //надо обязательно реализовать этот метод.
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //этот метод реализовываем по желанию.
            super.onPostExecute(result);
            tvInfo.setText("End");
        }
    }
}
