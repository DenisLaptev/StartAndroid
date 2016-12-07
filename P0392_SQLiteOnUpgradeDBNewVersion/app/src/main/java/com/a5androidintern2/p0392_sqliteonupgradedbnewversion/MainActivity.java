package com.a5androidintern2.p0392_sqliteonupgradedbnewversion;

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
    public static final int DB_VERSION = 2; // версия БД.

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

/*
    private void writeStaff(SQLiteDatabase db) {
        //метод выбирает данные из таблицы people
        //и вызывает метод logCursor для вывода данных в LOG.
        Cursor c = db.rawQuery("select * from people", null);
        logCursor(c, "Table people");
        c.close();
    }
*/

    //мы переписали метод writeStaff.
    private void writeStaff(SQLiteDatabase db) {
        //метод выбирает данные из таблиц people, position и их объединение
        //и вызывает метод logCursor для вывода данных в LOG.
        Cursor c = db.rawQuery("select * from people", null);
        logCursor(c, "Table people");
        c.close();

        c = db.rawQuery("select * from position", null);
        logCursor(c, "Table position");
        c.close();

        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id ";
        c = db.rawQuery(sqlQuery, null);
        logCursor(c, "inner join");
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
            //в SQLite нельзя просто удалить столбец.
            //Приходится создавать временную БД,
            //перекидывать туда данные,
            //удалять оригинал,
            //создавать снова новую таблицу с нужной структурой,
            //скидывать туда данные из временной таблицы
            //и удалять временную таблицу.

            //Пусть приложение обновилось.
            //И теперь при запуске оно попытается подключиться к БД версии 2,
            //но увидит, что существующая версия = 1.
            //тогда запустится метод onUpgrade.
            //
            //Таим образом, в случае ОБНОВЛЕНИЯ приложения мето onUpgrade обновляет БД.

            //Если пользователь установил новое приложение в первый раз,
            //то приложение также попытается подключиться к БД версии 2.
            //Но т.к. приложение только что установлено, то БД еще не существует.
            //Приложение создаст БД и присвоит ей версию номер 2,
            // т.к. оно умеет работать именно с такой версией.
            // При создании будет вызван метод onCreate в DBHelper.
            // Значит, в нем мы должны прописать код,
            // который будет создавать нам БД версии 2 –
            // т.е. обновленную таблицу people и новую таблицу position.





            Log.d(LOG_TAG, " --- onUpgrade database from " + oldVersion
                    + " to " + newVersion + " version --- ");

            if (oldVersion == 1 && newVersion == 2) {

                ContentValues cv = new ContentValues();

                //данные для таблицы должностей
                int[] position_id = { 1, 2, 3, 4 };
                String[] position_name = { "Директор", "Программер",
                        "Бухгалтер", "Охранник" };
                int[] position_salary = { 15000, 13000, 10000, 8000 };



                //используем тразакцию. Надо, чтобы на БД накатились все обновления.
                //А в случае ошибки в се изменения должны быть отменены.
                db.beginTransaction();
                try {
                    // создаем таблицу должностей
                    db.execSQL("create table position ("
                            + "id integer primary key,"
                            + "name text, salary integer);");

                    // заполняем ее
                    for (int i = 0; i < position_id.length; i++) {
                        cv.clear();
                        cv.put("id", position_id[i]);
                        cv.put("name", position_name[i]);
                        cv.put("salary", position_salary[i]);
                        db.insert("position", null, cv);
                    }

                    db.execSQL("alter table people add column posid integer;");

                    for (int i = 0; i < position_id.length; i++) {
                        cv.clear();
                        cv.put("posid", position_id[i]);
                        db.update("people", cv, "position = ?",
                                new String[] { position_name[i] });
                    }

                    db.execSQL("create temporary table people_tmp ("
                            + "id integer, name text, position text, posid integer);");

                    db.execSQL("insert into people_tmp select id, name, position, posid from people;");
                    db.execSQL("drop table people;");

                    db.execSQL("create table people ("
                            + "id integer primary key autoincrement,"
                            + "name text, posid integer);");

                    db.execSQL("insert into people select id, name, posid from people_tmp;");
                    db.execSQL("drop table people_tmp;");

                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
            }
        }
    }
}