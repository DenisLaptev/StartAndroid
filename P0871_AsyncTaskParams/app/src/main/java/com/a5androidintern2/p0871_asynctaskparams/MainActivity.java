package com.a5androidintern2.p0871_asynctaskparams;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    MyTask mt;
    TextView tvInfo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void onclick(View v) {
        mt = new MyTask();

        //мы используем String, это первый тип в угловых скобках AsyncTask - входные данные.
        mt.execute("file_path_1", "file_path_2", "file_path_3", "file_path_4");

    }

    class MyTask extends AsyncTask<String, Integer, Void> {
        //AsyncTask<входные данные, промежуточные данные, выходные данные>

        @Override
        protected void onPreExecute() {
            //метод выполняется перед началом выполнения AsyncTask.
            super.onPreExecute();
            tvInfo.setText("Begin");
        }

        @Override
        protected Void doInBackground(String... urls) {
            //здесь используется первый тип (String) из угловых скобок AsyncTask.
            try {
                int cnt = 0;
                for (String url : urls) {
                    //загружаем файл.
                    downloadFile(url);
                    //выводим промежуточные результаты.
                    //при вызове метода publishProgress() срабатывает метод onProgressUpdate().
                    publishProgress(++cnt);
                }
                //разъединяемся.
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            //метод используется для вывода промежуточных результатов.
            //Он выполняется в основном потоке и имеет доступ к UI.
            //здесь используется второй тип (Integer) из угловых скобок AsyncTask.
            //при вызове метода publishProgress() срабатывает метод onProgressUpdate().
            super.onProgressUpdate(values);
            tvInfo.setText("Downloaded " + values[0] + " files");
        }

        @Override
        protected void onPostExecute(Void result) {
            //метод выполняется по завершению задачи.
            super.onPostExecute(result);
            tvInfo.setText("End");
        }


        private void downloadFile(String url) throws InterruptedException {
            //эмуляция загрузки файлов, просто пауза в 2 секунды.
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
