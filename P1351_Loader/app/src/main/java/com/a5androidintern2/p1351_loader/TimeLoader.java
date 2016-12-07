package com.a5androidintern2.p1351_loader;

import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeLoader extends Loader<String> {

    final String LOG_TAG = "myLogs";
    final int PAUSE = 10;

    public final static String ARGS_TIME_FORMAT = "time_format";
    public final static String TIME_FORMAT_SHORT = "h:mm:ss a";
    public final static String TIME_FORMAT_LONG = "yyyy.MM.dd G 'at' HH:mm:ss";

    GetTimeTask getTimeTask;
    String format;

    public TimeLoader(Context context, Bundle args) {
        super(context);

        Log.d(LOG_TAG, hashCode() + " create TimeLoader");
        if (args != null) {
            format = args.getString(ARGS_TIME_FORMAT);
        }
        if (TextUtils.isEmpty(format)) {
            format = TIME_FORMAT_SHORT;
        }
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(LOG_TAG, hashCode() + " onStartLoading");
        if (takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        //вызывается при остановке (onStop) Activity или фрагмента,
        //к которому будет привязан Loader.
        super.onStopLoading();
        Log.d(LOG_TAG, hashCode() + " onStopLoading");
    }

    @Override
    protected void onForceLoad() {
        //в этом методе кодим работу лоадера.
        super.onForceLoad();
        Log.d(LOG_TAG, hashCode() + " onForceLoad");
        if (getTimeTask != null) {
            getTimeTask.cancel(true);
        }
        //Запускаем здесь GetTimeTask,
        //который будет нам время получать асинхронно.
        getTimeTask = new GetTimeTask();
        getTimeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, format);
    }

    @Override
    protected void onAbandon() {
        //метод означающий, что лоадер становится неактивным.
        super.onAbandon();
        Log.d(LOG_TAG, hashCode() + " onAbandon");
    }

    @Override
    protected void onReset() {
        //означает уничтожение лоадера,
        //вызывается при закрытии (onDestroy) Activity
        //или фрагмента, к которому будет привязан Loader.
        //Не вызывается, если onDestroy был вызван,
        //например при смене ориентации.
        super.onReset();
        Log.d(LOG_TAG, hashCode() + " onReset");
    }

    void getResultFromTask(String result) {
        deliverResult(result);//стандартный метод лоадера,
        //который оповещает слушателя, подключенного к лоадеру,
        //что работа окончена и передает ему данные.
    }

    class GetTimeTask extends AsyncTask<String, Void, String> {
        //GetTimeTask – это AsyncTask, который берет на вход
        //формат даты и через определенную паузу возвращает
        //(с помощью getResultFromTask)
        //в лоадер текущее время в этом формате.
        @Override
        protected String doInBackground(String... params) {
            Log.d(LOG_TAG, TimeLoader.this.hashCode() + " doInBackground");
            try {
                TimeUnit.SECONDS.sleep(PAUSE);
            } catch (InterruptedException e) {
                return null;
            }

            SimpleDateFormat sdf = new SimpleDateFormat(params[0],
                    Locale.getDefault());
            return sdf.format(new Date());
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(LOG_TAG, TimeLoader.this.hashCode() + " onPostExecute "
                    + result);
            getResultFromTask(result);
        }
    }
}
