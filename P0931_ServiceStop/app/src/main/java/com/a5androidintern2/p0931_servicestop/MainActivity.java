package com.a5androidintern2.p0931_servicestop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStart(View v) {
        //По нажатию на кнопку мы отправляем вызов в сервис три раза.
        //И в intent помещаем параметр time.

        //Соответственно в сервисе будет три раза выполнен метод onStartCommand.
        //Будет создано и передано экзекьютору три MyRun-объекта.
        //Он их по очереди начнет выполнять.
        //Это займет у него соответственно 7,2 и 4 секунд
        //(время паузы мы передаем в intent-е).
        //В конце обработки каждого MyRun будет выполняться stopSelf(startId).
        startService(new Intent(this, MyService.class).putExtra("time", 7));
        startService(new Intent(this, MyService.class).putExtra("time", 2));
        startService(new Intent(this, MyService.class).putExtra("time", 4));
    }
}
