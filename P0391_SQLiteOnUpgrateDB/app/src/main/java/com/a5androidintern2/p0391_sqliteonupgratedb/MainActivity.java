package com.a5androidintern2.p0391_sqliteonupgratedb;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    public static final String LOG_TAG = "myLogs";

    public static final String DB_NAME = "staff"; // имя БД.
    public static final int DB_VERSION = 1; // версия БД.

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //создаём объект DBHelper.
        DBHelper dbh = new DBHelper(this);

        //подключаемся к БД.
        SQLiteDatabase db = dbh.getWritableDatabase();

        //выводим в Log версию БД.
        Log.d(LOG_TAG, " --- Staff db v." + db.getVersion() + " --- ");

        //вызываем метод writeStaff.
        writeStaff(db);

        //закрываем соединение с БД.
        dbh.close();
    }


    private void writeStaff(SQLiteDatabase db) {
        //метод выбирает данные из таблицы people
        //и вызывает метод logCursor для вывода данных в LOG.
        Cursor c = db.rawQuery("select * from people", null);
        logCursor(c, "Table people");
        c.close();
    }


    void logCursor(Cursor c, String title) {
        //метод выводид данные из курсора cursor в LOG.

        if (c != null) {
            if (c.moveToFirst()) {
                Log.d(LOG_TAG, title + ". " + c.getCount() + " rows");
                StringBuilder sb = new StringBuilder();
                do {
                    sb.setLength(0);
                    for (String cn : c.getColumnNames()) {
                        sb.append(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, sb.toString());
                } while (c.moveToNext());
            }
        } else
            Log.d(LOG_TAG, title + ". Cursor is null");
    }

    // класс для работы с БД
    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, " --- onCreate database --- ");

            String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша",
                    "Борис", "Костя", "Игорь" };
            String[] people_positions = { "Программер", "Бухгалтер",
                    "Программер", "Программер", "Бухгалтер", "Директор",
                    "Программер", "Охранник" };

            ContentValues cv = new ContentValues();

            //создаем таблицу людей people.
            db.execSQL("create table people ("
                    + "id integer primary key autoincrement,"
                    + "name text, position text);");

            //заполняем таблицу людей people.
            for (int i = 0; i < people_name.length; i++) {
                cv.clear();
                cv.put("name", people_name[i]);
                cv.put("position", people_positions[i]);
                db.insert("people", null, cv);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}