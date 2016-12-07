package com.a5androidintern2.p0581_timepickerdialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {

    //DIALOG_TIME - это id диалога.
    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 35;
    TextView tvTime;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //находим TextView.
        tvTime = (TextView) findViewById(R.id.tvTime);
    }

    public void onclick(View view) {

        //DIALOG_TIME - это id диалога.
        showDialog(DIALOG_TIME);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {

/*
    используем конструктор:
    TimePickerDialog (Context context, TimePickerDialog.OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView),

    где

    context – контекст
    callBack – это обработчик с интерфйесом TimePickerDialog.OnTimeSetListener,
                метод которого срабатывает при нажатии кнопки ОК на диалоге
    hourOfDay – час, который покажет диалог
    minute – минута, которую покажет диалог
    is24HourView – формат времени 24 часа (иначе AM/PM).
*/


            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    OnTimeSetListener myCallBack = new OnTimeSetListener() {
        //myCallBack – объект, реализующий интерфейс TimePickerDialog.OnTimeSetListener.
        //У него только один метод – onTimeSet,
        //который предоставляет нам TimePicker из диалога, и час и минуту,
        //которые он показывает. Т.е. то, что мы ввели в диалоге.
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            tvTime.setText("Time is " + myHour + " hours " + myMinute + " minutes");
        }
    };
}
