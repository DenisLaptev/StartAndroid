package com.a5androidintern2.p0911_asynctaskrotate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    MyTask mt;
    TextView tv;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //метод .hashCode() возвращает hashCode объекта.
        Log.d("qwe", "create MainActivity: " + this.hashCode());

        tv = (TextView) findViewById(R.id.tv);


        //При создании Activity мы просим
        //систему вернуть (getLastNonConfigurationInstance) нам
        //сохраненный в методе onRetainNonConfigurationInstance объект
        //и приводим его к MyTask.
        //Если Activity создается не после поворота экрана,
        //то мы получим null, а значит, создаем сами MyTask.

        //Таким образом, при повороте экрана мы возвращаем себе старый MyTask.


        mt = (MyTask) getLastNonConfigurationInstance();
        if (mt == null) {
            mt = new MyTask();
            mt.execute();
        }

        //передаём ссылку на текущее новое активити.
        mt.link(this);
        Log.d("qwe", "create MyTask: " + mt.hashCode());
    }


    public Object onRetainNonConfigurationInstance() {
        //обнуляем ссылку на старое активити.
        mt.unlink();

        //возвращаем MyTask mt. Это для того, чтобы при повороте экрана
        //не создавался новый MyTask mt, и мы будем работать с
        //нашим MyTask-ом.
        return mt;
    }
/*
    class MyTask extends AsyncTask<String, Integer, Void> {
        //вложенный нестатический класс (внутренний класс).
*/

    static class MyTask extends AsyncTask<String, Integer, Void> {
        //вложенный статический класс.

        //эта переменная будет ссылаться
        //на объект внешнего класса - на MainActivity.
        MainActivity activity;

        void link(MainActivity act){
            //с помощью этого метода MyTask получает ссылку на MainActivity.
            //чтоб можно было обращаться из MyTask к объектам (элементам)
            //MainActivity.
            activity = act;
        }

        void unlink(){
            //с помощью этого метода обнуляем ссылку на MainActivity.
            activity = null;
        }

        @Override
        protected Void doInBackground(String... params) {
            //AsyncTask в цикле выполняет паузы (1с)
            //и в TextView на экране пишет номер (i) итерации цикла.
            try {
                for (int i = 1; i <= 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.d("qwe", "i = " + i
                            + ", MyTask: " + this.hashCode()
                            + ", MainActivity: " + activity.hashCode());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            activity.tv.setText("i = " + values[0]);
        }
    }
}
