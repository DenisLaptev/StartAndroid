package com.a5androidintern2.p0341_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by A5 Android Intern 2 on 08.11.2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    //класс для работы с БД.

    //принято константы делать public.
    public static final int DATABASE_VERSION = 1; //версия БД.
    public static final String DATABASE_NAME = "contactDb"; //имя БД.
    public static final String TABLE_NAME = "contacts"; //имя таблицы.

    //добавим константы для заголовков столбцов таблицы.

    //нижнее подчёркивание обязательно. Такая особенность Android.
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //метод вызывается при первом создании БД.
        //т.е. если БД не существует, и её надо создать.
        db.execSQL("create table " + TABLE_NAME +
                "(" +
                KEY_ID + " integer primary key," +
                KEY_NAME + " text," +
                KEY_EMAIL + " text" +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //метод вызывается при изменении БД.
        //имеется в виду, что этот метод срабатывает,
        //если надо обновить приложение и заменить старую БД.
        //если указанный в приложении номер версии БД выше, чем в самой БД.



        //здесь можем удалить старую БД.
        //после чего создать БД с обновлённой структурой.
        db.execSQL("drop table if exists " + TABLE_NAME);

        onCreate(db);

    }

}
