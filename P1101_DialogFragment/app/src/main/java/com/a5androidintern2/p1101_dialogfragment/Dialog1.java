package com.a5androidintern2.p1101_dialogfragment;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Dialog1 extends DialogFragment implements OnClickListener {

    final String LOG_TAG = "myLogs";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //получаем объект Dialog с помощью метода getDialog
        //и устанавливаем заголовок диалога.
        getDialog().setTitle("Title!");

        //создаем view из layout,
        //находим в нем кнопки
        //и ставим им текущий фрагмент в качестве обработчика.
        View v = inflater.inflate(R.layout.dialog1, null);
        v.findViewById(R.id.btnYes).setOnClickListener(this);
        v.findViewById(R.id.btnNo).setOnClickListener(this);
        v.findViewById(R.id.btnMaybe).setOnClickListener(this);
        return v;
    }

    public void onClick(View v) {
        //выводим в лог текст нажатой кнопки.
        Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());

        //сами явно закрываем диалог методом dismiss.
        dismiss();
    }

    public void onDismiss(DialogInterface dialog) {
        //Метод onDismiss срабатывает, когда диалог закрывается.
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        //Метод onCancel срабатывает, когда диалог отменяют кнопкой Назад.
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }
}
