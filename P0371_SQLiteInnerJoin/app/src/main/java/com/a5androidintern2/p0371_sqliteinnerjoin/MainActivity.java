package com.a5androidintern2.p0371_sqliteinnerjoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "myLogs";

    //массивы с данными из таблиц.
    int[] position_id = {1, 2, 3, 4};
    String[] position_name = {"Директор", "Программист", "Бухгалтер", "Охранник"};
    int[] position_salary = {80000, 60000, 40000, 20000};

    String[] people_name = {"Максим", "Сергей", "Руслан", "Наталья", "Иван", "Мария", "Светлана", "Григорий"};
    int[] people_posid = {2, 3, 2, 2, 3, 1, 2, 4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //подключаемся к БД.
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        //объявляем курсор.
        Cursor cursor;

        //выводим в Log данные по должностям.
        Log.d(LOG_TAG, "---Table position---");
        cursor = sqLiteDatabase.query("position", null, null, null, null, null, null);

        //метод выполняет вывод в LOG данных из cursor-а.
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        //выводим в Log данные по людям.
        Log.d(LOG_TAG, "---Table people---");
        cursor = sqLiteDatabase.query("people", null, null, null, null, null, null);

        //метод выполняет вывод в LOG данных из cursor-а.
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        //выводим в Log результат объединения таблиц должности и люди.
        //используем метод rawQuery(SQL-запрос, список аргументов для условия WHERE).
        Log.d(LOG_TAG, "---INNER JOIN with rawQuery---");

        //формируем запрос на объединение 2-х таблиц
        //и вывода имени, должности и зарплаты.
        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id "
                + "where salary > ?";

        //используем метод rawQuery(SQL-запрос, список аргументов для условия WHERE).
        cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[] {"40000"});

        //метод выполняет вывод в LOG данных из cursor-а.
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        //выводим в Log результат объединения таблиц должности и люди.
        //используем метод query(SQL-запрос, список аргументов для условия WHERE).
        Log.d(LOG_TAG, "---INNER JOIN with query---");
        String table = "people as PL inner join position as PS on PL.posid = PS.id";
        String[] columns = {"PL.name as Name", "PS.name as Position", "salary as Salary"};
        String selection = "salary < ?";
        String[] selectionArgs = {"40000"};
        cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, null, null, null);

        //метод выполняет вывод в LOG данных из cursor-а.
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        //в конце закрываем БД.
        dbHelper.close();
    }

    void logCursor(Cursor cursor) {
        //метод выполняет вывод в LOG данных из cursor-а.
        //метод получает на вход курсор и выводит в LOG всё содержимое cursor-а.
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : cursor.getColumnNames()) {
                        str = str.concat(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);
                } while (cursor.moveToNext());
            }
        } else Log.d(LOG_TAG, "Cursor is null");
    }

    class DbHelper extends SQLiteOpenHelper {
        //вложенный (внутренний) класс.

        public DbHelper(Context context) {
            super(context, "myDb", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //выводим сообщение о создании таблицы.
            Log.d(LOG_TAG, "---onCreate database---");

            ContentValues contentValues = new ContentValues();

            //создаём таблицу должностей.
            sqLiteDatabase.execSQL("create table position ("
                    + "id integer primary key, "
                    + "name text, "
                    + "salary integer"
                    + ");");

            //заполняем таблицу должностей.
            for (int i = 0; i < position_id.length; i++) {
                contentValues.clear();
                contentValues.put("id", position_id[i]);
                contentValues.put("name", position_name[i]);
                contentValues.put("salary", position_salary[i]);
                sqLiteDatabase.insert("position", null, contentValues);
            }

            //создаём таблицу людей.
            sqLiteDatabase.execSQL("create table people ("
                    + "id integer primary key autoincrement, "
                    + "name text, "
                    + "posid integer"
                    + ");");

            //заполняем таблицу людей.
            for (int i = 0; i < people_name.length; i++) {
                contentValues.clear();
                contentValues.put("name", people_name[i]);
                contentValues.put("posid", people_posid[i]);
                sqLiteDatabase.insert("people", null, contentValues);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}