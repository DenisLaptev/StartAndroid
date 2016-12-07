package com.a5androidintern2.p0951_servicebackpendingintent;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_PINTENT = "pendingIntent";
    public final static String PARAM_RESULT = "result";

    TextView tvTask1;
    TextView tvTask2;
    TextView tvTask3;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //находим TextView и присваиваем им первоначальный текст.
        tvTask1 = (TextView) findViewById(R.id.tvTask1);
        tvTask1.setText("Task1");
        tvTask2 = (TextView) findViewById(R.id.tvTask2);
        tvTask2.setText("Task2");
        tvTask3 = (TextView) findViewById(R.id.tvTask3);
        tvTask3.setText("Task3");
    }

    public void onClickStart(View v) {
        PendingIntent pi;
        Intent intent;

        //Создаем PendingIntent для Task1.
        //На вход подаём код запроса (можно считать его идентификатором).
        //По этому коду мы потом будем определять, какая именно задача вернула ответ из сервиса.
        //ва остальных параметра – это Intent и флаги.
        //Нам они сейчас не нужны, передаем соответственно null и 0.
        pi = createPendingResult(TASK1_CODE, new Intent(), 0);
        //Создаем Intent для вызова сервиса, кладем туда параметр времени
        //и созданный PendingIntent.
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 7)
                .putExtra(PARAM_PINTENT, pi);
        //стартуем сервис. Отправляем интент в сервис.
        startService(intent);

        pi = createPendingResult(TASK2_CODE, new Intent(), 0);
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 4)
                .putExtra(PARAM_PINTENT, pi);
        startService(intent);

        pi = createPendingResult(TASK3_CODE, new Intent(), 0);
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 6)
                .putExtra(PARAM_PINTENT, pi);
        startService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //проверяем, какой тип сообщения пришёл из сервиса.
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG, "requestCode = " + requestCode + ", resultCode = "
                + resultCode);

        //Если это сообщение о старте задачи, то проверяем какая это задача
        //и заполняем соответствующий TextView.
        //Ловим сообщения о старте задач.
        if (resultCode == STATUS_START) {
            switch (requestCode) {
                case TASK1_CODE:
                    tvTask1.setText("Task1 start");
                    break;
                case TASK2_CODE:
                    tvTask2.setText("Task2 start");
                    break;
                case TASK3_CODE:
                    tvTask3.setText("Task3 start");
                    break;
            }
        }

        //А если о том, что задача завершена (STATUS_FINISH),
        //то читаем из Intent-а результат выполнения, определяем,
        //какая именно задача, и пишем инфу об этом в соответствующий TextView.
        //Ловим сообщения об окончании задач.
        if (resultCode == STATUS_FINISH) {
            int result = data.getIntExtra(PARAM_RESULT, 0);
            switch (requestCode) {
                case TASK1_CODE:
                    tvTask1.setText("Task1 finish, result = " + result);
                    break;
                case TASK2_CODE:
                    tvTask2.setText("Task2 finish, result = " + result);
                    break;
                case TASK3_CODE:
                    tvTask3.setText("Task3 finish, result = " + result);
                    break;
            }
        }
    }
}
