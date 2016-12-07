package com.a5androidintern2.p0631_alertdialogitemssingle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    final int DIALOG_ITEMS = 1;
    final int DIALOG_ADAPTER = 2;
    final int DIALOG_CURSOR = 3;
    DB db;
    Cursor cursor;

    String data[] = { "one", "two", "three", "four" };

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //открываем подключение к БД.
        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.btnAdapter:
                showDialog(DIALOG_ADAPTER);
                break;
            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
            default:
                break;
        }
    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id) {
            //массив.
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                //передаём
                //массив данных,
                //позицию выбранного элемента (ничего не выбрано),
                //обработчик события.
                adb.setSingleChoiceItems(data, -1, myClickListener);
                break;
            //адаптер.
            case DIALOG_ADAPTER:
                adb.setTitle(R.string.adapter);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.select_dialog_singlechoice, data);
                //создали адаптер.
                //передаём
                //адаптер,
                //позицию выбранного элемента (ничего не выбрано),
                //обработчик события.

                adb.setSingleChoiceItems(adapter, -1, myClickListener);
                break;
            //курсор.
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);

                //создали курсор.
                //передаём
                //курсор,
                //позицию выбранного элемента (ничего не выбрано),
                //имя поля, значения которого будут показаны в списке,
                //обработчик события.
                adb.setSingleChoiceItems(cursor, -1, DB.COLUMN_TXT, myClickListener);
                break;
        }
        adb.setPositiveButton(R.string.ok, myClickListener);

        //для всех 3-х способов использовали метод .setSingleChoiceItems(),
        //но с разными наборами параметров.
        return adb.create();
    }

    //обработчик нажатия на пункт списка диалога или кнопку.
    OnClickListener myClickListener = new OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            ListView lv = ((AlertDialog) dialog).getListView();
            if (which == Dialog.BUTTON_POSITIVE) {
                //нажатие на кнопку.
                //выводим в лог позицию выбранного элемента.
                Log.d(LOG_TAG, "pos = " + lv.getCheckedItemPosition());
            }
            else {
                //нажатие на пункт списка.
                //выводим в лог позицию нажатого элемента.
                Log.d(LOG_TAG, "which = " + which);
            }
        }
    };


    /*
    Если хотите перед каждым вызовом менять выбранный элемент,
    реализуйте метод onPrepareDialog.
    В нем надо добраться до списка и вызвать метод setItemChecked.

    Пример кода, в котором выбирается третий элемент (нумерация с нуля):

    protected void onPrepareDialog(int id, Dialog dialog) {
        ((AlertDialog) dialog).getListView().setItemChecked(2, true);
    };



    */







    @Override
    protected void onDestroy() {
        //закрываем соединение с БД.
        super.onDestroy();
        db.close();
    }
}
