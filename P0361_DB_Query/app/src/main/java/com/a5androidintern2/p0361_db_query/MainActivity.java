package com.a5androidintern2.p0361_db_query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    //создаём 3 массива данных name, people, region.
    String[] name = { "Китай", "США", "Бразилия", "Россия", "Япония",
            "Германия", "Египет", "Италия", "Франция", "Канада" };
    int[] people = { 1400, 311, 195, 142, 128, 82, 80, 60, 66, 35 };
    String[] region = { "Азия", "Америка", "Америка", "Европа", "Азия",
            "Европа", "Африка", "Европа", "Европа", "Америка" };

    Button btnAll, btnFunc, btnPeople, btnSort, btnGroup, btnHaving;
    EditText etFunc, etPeople, etRegionPeople;
    RadioGroup rgSort;

    //создаём объект DBHelper dbHelper для управления БД.
    DBHelper dbHelper;
    SQLiteDatabase db;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = (Button) findViewById(R.id.btnAll);
        btnAll.setOnClickListener(this);

        btnFunc = (Button) findViewById(R.id.btnFunc);
        btnFunc.setOnClickListener(this);

        btnPeople = (Button) findViewById(R.id.btnPeople);
        btnPeople.setOnClickListener(this);

        btnSort = (Button) findViewById(R.id.btnSort);
        btnSort.setOnClickListener(this);

        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(this);

        btnHaving = (Button) findViewById(R.id.btnHaving);
        btnHaving.setOnClickListener(this);

        etFunc = (EditText) findViewById(R.id.etFunc);
        etPeople = (EditText) findViewById(R.id.etPeople);
        etRegionPeople = (EditText) findViewById(R.id.etRegionPeople);

        rgSort = (RadioGroup) findViewById(R.id.rgSort);

        dbHelper = new DBHelper(this);




        //подключаемся к БД.
        db = dbHelper.getWritableDatabase();

        //проверка существования записей.
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        if (c.getCount() == 0) {
            ContentValues cv = new ContentValues();
            //заполним таблицу, если она была пустая.
            for (int i = 0; i < 10; i++) {
                cv.put("name", name[i]);
                cv.put("people", people[i]);
                cv.put("region", region[i]);
                Log.d(LOG_TAG, "id = " + db.insert("mytable", null, cv));
            }
        }
        //закрываем соединение курсора.
        c.close();
        //закрываем соединение с DBHelper dbHelper.
        dbHelper.close();
        //эмулируем нажатие кнопки btnAll
        onClick(btnAll);

    }

    public void onClick(View v) {

        //подключаемся к БД.
        db = dbHelper.getWritableDatabase();

        //считываем данные с полей EditText на экране.
        String sFunc = etFunc.getText().toString();
        String sPeople = etPeople.getText().toString();
        String sRegionPeople = etRegionPeople.getText().toString();

        //описываем переменные, которые будем использовать в методе query().
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        //описываем курсор
        Cursor c = null;

        //определяем, какая кнопка была нажата.
        switch (v.getId()) {

            case R.id.btnAll:
                //Выводим на экран все записи.
                Log.d(LOG_TAG, "--- Все записи ---");

                //получаем объект курсор.
                c = db.query("mytable", null, null, null, null, null, null);
                break;

            case R.id.btnFunc:
                //Выводим на экран все значение агрегатной функции или любого поля.
                Log.d(LOG_TAG, "--- Функция " + sFunc + " ---");
                columns = new String[] { sFunc };

                //получаем объект курсор.
                c = db.query("mytable", columns, null, null, null, null, null);
                break;

            case R.id.btnPeople:
                //Выводим на экран страны с населением больше, чем.
                Log.d(LOG_TAG, "--- Население больше " + sPeople + " ---");
                selection = "people > ?";
                selectionArgs = new String[] { sPeople };

                //получаем объект курсор.
                c = db.query("mytable", null, selection, selectionArgs, null, null, null);
                break;

            case R.id.btnGroup:
                //группировка стран по регионам, и вывод населения в регионах.
                Log.d(LOG_TAG, "--- Население по региону ---");

                //выводим на экран эти столбцы.
                columns = new String[] { "region", "sum(people) as people" };
                groupBy = "region";//группируем по столбцу "region".

                //получаем объект курсор.
                c = db.query("mytable", columns, null, null, groupBy, null, null);
                break;

            case R.id.btnHaving:
                //вывод региона с населением больше указанного числа.
                Log.d(LOG_TAG, "--- Регионы с населением больше " + sRegionPeople
                        + " ---");
                columns = new String[] { "region", "sum(people) as people" };
                groupBy = "region";

                //условие.
                having = "sum(people) > " + sRegionPeople;

                //получаем объект курсор.
                c = db.query("mytable", columns, null, null, groupBy, having, null);
                break;

            case R.id.btnSort:
                //сортировка стран.

                switch (rgSort.getCheckedRadioButtonId()) {
                    //определяем, какой RadioButton был включён.

                    case R.id.rName:
                        //сортировка по названию страны.
                        Log.d(LOG_TAG, "--- Сортировка по наименованию ---");
                        orderBy = "name";
                        break;

                    case R.id.rPeople:
                        //сортировка по населению страны.
                        Log.d(LOG_TAG, "--- Сортировка по населению ---");
                        orderBy = "people";
                        break;

                    case R.id.rRegion:
                        //сортировка по названию региона.
                        Log.d(LOG_TAG, "--- Сортировка по региону ---");
                        orderBy = "region";
                        break;
                }

                //получаем объект курсор, с указанной сортировкой.
                c = db.query("mytable", null, null, null, null, null, orderBy);
                break;
        }

        if (c != null) {
            if (c.moveToFirst()) {
                String str;

                //запускаем перебор записей в цикле.
                do {
                    str = "";
                    for (String cn : c.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);

                } while (c.moveToNext());
            }
            c.close();
        } else
            Log.d(LOG_TAG, "Cursor is null");

        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {
        //вложенный (внутренний) класс.

        public DBHelper(Context context) {
            //конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            //создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement," + "name text,"
                    + "people integer," + "region text" + ");");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}