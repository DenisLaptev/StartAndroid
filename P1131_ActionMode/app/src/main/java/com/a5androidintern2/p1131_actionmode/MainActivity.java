package com.a5androidintern2.p1131_actionmode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ActionMode actionMode;
    final String LOG_TAG = "myLogs1";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(View v) {
        //Чтобы вызвать ActionMode, используется метод startActionMode.
        //На вход он берет объект callback, который будет обрабатывать все события,
        //связанные с ActionMode. На выходе получаем объект ActionMode.

        //проверяем, если ActionMode еще не был вызван, то вызываем.
        if (actionMode == null)
            actionMode = startActionMode(callback);
        else
            //Иначе – убираем ActionMode с помощью его же метода finish.
            actionMode.finish();
    }

    private ActionMode.Callback callback = new ActionMode.Callback() {

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            //метод вызывается при создании ActionMode.
            //Возвращаем true, если ActionMode можно создавать.
            //Здесь мы наполняем ActionMode пунктами меню (через объект Menu).
            mode.getMenuInflater().inflate(R.menu.context, menu);
            return true;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            //метод вызывается при обновлении ActionMode.
            //Например, в случае вызова метода invalidate.
            //Возвращаем true, если ActionMode можно обновить.
            return false;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            //обработка нажатия на какой-либо пункт ActionMode.
            //Будем выводить в лог текст нажатого пункта.
            Log.d(LOG_TAG, "item " + item.getTitle());
            return false;
        }

        public void onDestroyActionMode(ActionMode mode) {
            //вызывается при закрытии ActionMode.

            Log.d(LOG_TAG, "destroy");

            //обнуляем переменную actionMode, чтобы в onClick (см.выше)
            //у нас работала проверка (actionMode == null).
            actionMode = null;
        }

    };

}
