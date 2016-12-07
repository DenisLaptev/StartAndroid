package com.a5androidintern2.p0392_sqliteonupgradedbnewversion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by A5 Android Intern 2 on 09.11.2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = "myLogs";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactDb";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIL = "mail";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, " --- onCreate database --- ");

        //данные для таблицы людей.
        String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша",
                "Борис", "Костя", "Игорь" };
        int[] people_posid = { 2, 3, 2, 2, 3, 1, 2, 4 };

        // данные для таблицы должностей.
        int[] position_id = { 1, 2, 3, 4 };
        String[] position_name = { "Директор", "Программер", "Бухгалтер",
                "Охранник" };
        int[] position_salary = { 15000, 13000, 10000, 8000 };

        ContentValues cv = new ContentValues();

        //создаем таблицу должностей.
        db.execSQL("create table position (" + "id integer primary key,"
                + "name text, salary integer" + ");");

        //заполняем таблицу должностей.
        for (int i = 0; i < position_id.length; i++) {
            cv.clear();
            cv.put("id", position_id[i]);
            cv.put("name", position_name[i]);
            cv.put("salary", position_salary[i]);
            db.insert("position", null, cv);
        }

        //создаем таблицу людей.
        db.execSQL("create table people ("
                + "id integer primary key autoincrement,"
                + "name text, posid integer);");

        //заполняем таблицу людей.
        for (int i = 0; i < people_name.length; i++) {
            cv.clear();
            cv.put("name", people_name[i]);
            cv.put("posid", people_posid[i]);
            db.insert("people", null, cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
