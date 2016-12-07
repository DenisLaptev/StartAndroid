package com.a5androidintern2.p0671_progressdialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class MainActivity extends Activity {

    ProgressDialog pd;
    Handler h;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnDefault:
                //используем круговой индикатор, когда не понятно, сколько времени понадобится
                //или когда сложно отобразить проценты выполнения.
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");

                //добавляем кнопку.
                pd.setButton(Dialog.BUTTON_POSITIVE, "OK", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                pd.show();
                break;
            case R.id.btnHoriz:
                //при запуске этого диалога вначале идёт анимация ожидания,
                //потом идёт анимация с заполнением
                //первичной ("прогресс") и вторичной ("кеширование") полосы.
                //когда полоса "прогресс" заполнена, диалог закрывается.
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");

                //при желании можно аналогично добавить кнопку.

                //меняем стиль на индикатор.
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //устанавливаем максимум.
                pd.setMax(2148);
                //включаем анимацию ожидания.
                pd.setIndeterminate(true);
                pd.show();
                h = new Handler() {
                    public void handleMessage(Message msg) {
                        //выключаем анимацию ожидания
                        pd.setIndeterminate(false);
                        if (pd.getProgress() < pd.getMax()) {
                            //увеличиваем значения индикаторов

                            //есть первичный и вторичный индикаторы, как на YouTube (прогресс, кеширование).
                            pd.incrementProgressBy(50);
                            pd.incrementSecondaryProgressBy(75);
                            h.sendEmptyMessageDelayed(0, 100);
                        } else {
                            pd.dismiss();
                        }
                    }
                };
                h.sendEmptyMessageDelayed(0, 2000);
                break;
            default:
                break;
        }
    }
}
