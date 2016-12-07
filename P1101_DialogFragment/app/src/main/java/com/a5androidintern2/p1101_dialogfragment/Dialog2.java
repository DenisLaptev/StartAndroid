package com.a5androidintern2.p1101_dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;

public class Dialog2 extends DialogFragment implements OnClickListener {

    final String LOG_TAG = "myLogs";

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Для создания диалога с помощью билдера используется onCreateDialog.
        //Создаем диалог с заголовком, сообщением и тремя кнопками.
        //Обработчиком для кнопок назначаем текущий фрагмент.
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle("Title!")
                .setMessage(R.string.message_text)
                .setPositiveButton(R.string.yes, this)
                .setNegativeButton(R.string.no, this)
                .setNeutralButton(R.string.maybe, this);
        return adb.create();
    }

    public void onClick(DialogInterface dialog, int which) {
        //определяем, какая кнопка была нажата и выводим соответствующий текст в лог.
        int i = 0;
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                i = R.string.yes;
                break;
            case Dialog.BUTTON_NEGATIVE:
                i = R.string.no;
                break;
            case Dialog.BUTTON_NEUTRAL:
                i = R.string.maybe;
                break;
        }
        if (i > 0)
            Log.d(LOG_TAG, "Dialog 2: " + getResources().getString(i));

        //В случае создания диалога через билдер,
        //диалог сам закроется по нажатию на кнопку, метод dismiss здесь не нужен.
    }

    public void onDismiss(DialogInterface dialog) {
        //Метод onDismiss срабатывает, когда диалог закрывается.
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 2: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        //Метод onCancel срабатывает, когда диалог отменяют кнопкой Назад.
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 2: onCancel");
    }
}
