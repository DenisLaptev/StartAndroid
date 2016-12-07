package com.a5androidintern2.p1132_actionmode2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    ActionMode actionMode;
    ListView lvActionMode;
    final String LOG_TAG = "myLogs";

    String[] data = { "one", "two", "three", "four", "five" };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //создаём адаптер.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, data);
        lvActionMode = (ListView) findViewById(R.id.lvActionMode);

        //присваиваем адаптер списку.
        lvActionMode.setAdapter(adapter);

        //для списка включаем режим выбора CHOICE_MODE_MULTIPLE_MODAL
        lvActionMode.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvActionMode.setMultiChoiceModeListener(new MultiChoiceModeListener() {
            //устанавливаем объект обработчик, реализующий AbsListView.MultiChoiceModeListener.

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

                //после нажатия, меню закрывается.
                mode.finish();
                return false;
            }

            public void onDestroyActionMode(ActionMode mode) {
                //вызывается при закрытии ActionMode.
            }

            public void onItemCheckedStateChanged(ActionMode mode,
                                                  int position, long id, boolean checked) {
                //выводим в лог инфу о выбираемых пунктах списка.
                Log.d(LOG_TAG, "position = " + position + ", checked = "
                        + checked);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

