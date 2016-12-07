package com.a5androidintern2.p0991_servicenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    NotificationManager nm;

    @Override
    public void onCreate() {
        super.onCreate();

        //получаем менеджер уведомлений – NotificationManager.
        //Он нам понадобится, чтобы отправить уведомление.
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            //запускаем паузу на 5 секунд (эмулируем закачку файла)
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //и после этого отправляем уведомление.
        sendNotif();
        return super.onStartCommand(intent, flags, startId);
    }

    void sendNotif() {
        //1-я часть

        //создаем Notification. В конструкторе указываем иконку и текст,
        //которые будут видны в статус-баре.
        //Также мы здесь указываем время. Обычно это текущее время.
        //Но можно указать и прошлое и будущее.
        //По этому времени уведомления будут отсортированы в статус-баре
        //и в его раскрытой части.
        //Notification notif = new Notification(R.drawable.ic_launcher, "Text in status bar", System.currentTimeMillis());

        //3-я часть

        //создаем Intent, который мы бы использовали для вызова нашего Activity.
        Intent intent = new Intent(this, MainActivity.class);

        //помещаем в интент имя загруженного файла.
        //Activity его достанет и поместит в TextView.
        intent.putExtra(MainActivity.FILE_NAME, "somefile");


        //оборачиваем этот Intent в PendingIntent, с помощью метода getActivity.
        //На вход ему передаем контекст и Intent.
        //Второй параметр не используется (так написано в хелпе).
        //А четвертый – это флаги, влияющие на поведение PendingIntent.
        //Они не относятся к теме урока, мы их не используем.

        //Теперь этот созданный PendingIntent содержит информацию о том,
        //что надо вызывать Activity, а также объект Intent,
        //который для этой цели надо использовать.
        //Это будет использовано при нажатии на уведомлении.
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //2-я часть

        //метод .setLatestEventInfo(контекст, текст-заголовок, подробный текст, PendingIntent).
        //Теперь, когда мы откроем статус-бар, мы увидим два этих
        //текста (заголовок и подробный).
        //А, когда нажмем на уведомление,
        //система использует PendingIntent для запуска Activity.


        //notif.setLatestEventInfo(this, "Notification's title", "Notification's text", pIntent);
        Notification.Builder builder = new Notification.Builder(this)
                .setContentIntent(pIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSubText("SubText")
                .setUsesChronometer(false)
                .setContentTitle("Notification's title")
                .setContentText("Notification's text")
                .setOngoing(false)
                .setTicker("this is ticker text");


        Notification notif = null;
        notif = builder.build();


        //ставим флаг, чтобы уведомление пропало после нажатия.
        notif.flags |= Notification.FLAG_AUTO_CANCEL;

        //отправляем.
        //Далее вызываем метод notify для менеджера уведомлений и
        //передаем туда ID и созданное уведомление.
        nm.notify(1, notif);
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}




















