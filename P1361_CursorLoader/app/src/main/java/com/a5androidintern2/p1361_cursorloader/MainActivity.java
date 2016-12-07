package com.a5androidintern2.p1361_cursorloader;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<Cursor> {

    private static final int CM_DELETE_ID = 1;
    ListView lvData;
    DB db;
    SimpleCursorAdapter scAdapter;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //открываем подключение к БД.
        db = new DB(this);
        db.open();

        //формируем столбцы сопоставления.
        String[] from = new String[] { DB.COLUMN_IMG, DB.COLUMN_TXT };
        int[] to = new int[] { R.id.ivImg, R.id.tvText };

        //создаем адаптер и настраиваем список.
        scAdapter = new SimpleCursorAdapter(this, R.layout.item, null, from, to, 0);
        lvData = (ListView) findViewById(R.id.lvData);
        lvData.setAdapter(scAdapter);

        //добавляем контекстное меню к списку.
        registerForContextMenu(lvData);

        //создаем лоадер для чтения данных.
        getSupportLoaderManager().initLoader(0, null, this);
    }

    //обработка нажатия кнопки.
    public void onButtonClick(View view) {
        //добавляем запись.
        db.addRec("sometext " + (scAdapter.getCount() + 1), R.drawable.ic_launcher);
        //получаем новый курсор с данными.
        getSupportLoaderManager().getLoader(0).forceLoad();
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        //создание контекстного меню.

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
    }

    public boolean onContextItemSelected(MenuItem item) {
        //реализуем удаление записи из БД.
        //И после удаления снова просим лоадер дать нам новый курсор с данными.

        if (item.getItemId() == CM_DELETE_ID) {
            //получаем из пункта контекстного меню данные по пункту списка.
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item
                    .getMenuInfo();
            //извлекаем id записи и удаляем соответствующую запись в БД.
            db.delRec(acmi.id);
            //получаем новый курсор с данными.
            getSupportLoaderManager().getLoader(0).forceLoad();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    protected void onDestroy() {
        super.onDestroy();
        //закрываем подключение к БД при выходе.
        db.close();
    }


    //Далее идут колбэк-методы интерфейса LoaderCallbacks.

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bndl) {
        //создаем Loader и даем ему на вход объект для работы с БД.
        return new MyCursorLoader(this, db);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        //получаем результат работы лоадера – новый курсор с данными.
        //Этот курсор мы отдаем адаптеру методом swapCursor.
        scAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    static class MyCursorLoader extends CursorLoader {
        //MyCursorLoader – наш лоадер, наследник класса CursorLoader.
        //У него мы переопределяем метод loadInBackground,
        //в котором просто получаем курсор с данными БД.
        //Ну и я 3-х секундной паузой сэмулировал долгое чтение БД
        //для наглядности асинхронной работы.

        DB db;

        public MyCursorLoader(Context context, DB db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = db.getAllData();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return cursor;
        }

    }
}
