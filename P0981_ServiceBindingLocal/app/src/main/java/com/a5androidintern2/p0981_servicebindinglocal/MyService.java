package com.a5androidintern2.p0981_servicebindinglocal;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";

    MyBinder binder = new MyBinder();

    //используем таймер – Timer.
    //Он позволяет повторять какое-либо действие через заданный промежуток времени.
    Timer timer;

    //TimerTask – это задача, которую Timer будет периодически выполнять.
    //В методе run – кодим действия этой задачи.
    TimerTask tTask;
    long interval = 1000;

    public void onCreate() {
        //мы создаем таймер и выполняем метод schedule, в котором стартует задача.
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        timer = new Timer();
        schedule();
    }

    void schedule() {
        //Метод schedule проверяет, что задача уже создана и отменяет ее.
        if (tTask != null) {
            //Чтобы отменить выполнение задачи,
            //необходимо вызвать метод cancel для TimerTask.
            //Отмененную задачу нельзя больше запланировать,
            //и если снова надо ее включить – необходимо создать новый
            //экземпляр TimerTask и скормить его таймеру.
            tTask.cancel();
        }
        if (interval > 0) {
            tTask = new TimerTask() {
                public void run() {
                    Log.d(LOG_TAG, "run");
                }
            };
            //для объекта Timer вызываем метод
            //schedule(задача TimerTask, время через которое начнется выполнение, период повтора).

            //Метод schedule проверяет, что задача уже создана и отменяет ее.
            //Далее планирует новую, с отложенным на 1000 мс запуском
            //и периодом = interval. Т.е. можно сказать,
            //что этот метод перезапускает задачу с использованием текущего интервала
            //повтора (interval), а если задача еще не создана, то создает ее.
            //Сама задача просто выводит в лог текст run.
            //Если interval = 0, то ничего не делаем.
            timer.schedule(tTask, 1000, interval);
        }
    }

    long upInterval(long gap) {
        //Метод upInterval получает на вход значение, увеличивает interval
        //на это значение и перезапускает задачу.
        //Соответственно задача после этого будет повторяться реже.
        interval = interval + gap;
        schedule();
        return interval;
    }

    long downInterval(long gap) {
        //Метод downInterval получает на вход значение,
        //уменьшает interval на это значение (но так, чтоб не меньше 0)
        //и перезапускает задачу.
        //Соответственно задача после этого будет повторяться чаще.
        interval = interval - gap;
        if (interval < 0) {
            interval = 0;
        }
        schedule();
        return interval;
    }

    public IBinder onBind(Intent arg0) {
        //onBind возвращает binder. Это объект класса MyBinder.
        Log.d(LOG_TAG, "MyService onBind");
        return binder;
    }

    class MyBinder extends Binder {
        //MyBinder расширяет стандартный Binder,
        //мы добавляем в него один метод getService.
        //Этот метод возвращает наш сервис MyService.
        MyService getService() {
            //метод возвращает наш сервис MyService.
            return MyService.this;
        }
    }
}
