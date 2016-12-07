package com.a5androidintern2.p0901_asynctaskstatus;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        switch (v.getId()) {
            case R.id.btnStart:
                startTask();
                break;
            case R.id.btnStatus:
                showStatus();
                break;
            default:
                break;
        }
    }

    private void startTask() {
    //создаём новую задачу, но пока не запускаем её.
    //Перед запуском будет статус PENDING.
    //после запуска будет статус RUNNING,
    //после завершения будет статус FINISHED.
        mt = new MyTask();
        mt.execute();

        //после отмены будет статус RUNNING.
        //Статус почему-то RUNNING, как будто задача в работе.
        //Не особо логично, конечно, но вот такая особенность реализации.
        //Почему нельзя было ввести еще один статус CANCELED – я не знаю.
        //Разработчикам Android виднее.
        mt.cancel(false);
    }



    private void showStatus() {
        //получаем статус и выводим его в подсказке.
        if (mt != null) {
            //чтобы отличить,
            //задача запущена или отменена (в обоих случаях статус будет RUNNING),
            //можно использовать метод isCancelled.
            if (mt.isCancelled()) {
                Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, mt.getStatus().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            //гоняем в цикле паузы.
            try {
                for (int i = 0; i < 5; i++) {
                    if (isCancelled()) return null;
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tvInfo.setText("End");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            tvInfo.setText("Cancel");
        }
    }
}
