package com.a5androidintern2.p0341_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etName;
    EditText etEmail;
    Button btnAdd;
    Button btnRead;
    Button btnClear;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.main_etName);
        etEmail = (EditText) findViewById(R.id.main_etEmail);

        btnAdd = (Button) findViewById(R.id.main_btnAdd);
        btnRead = (Button) findViewById(R.id.main_btnRead);
        btnClear = (Button) findViewById(R.id.main_btnClear);

        btnAdd.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View view) {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();

        //класс SQLiteDatabase предназначен для управления БД SQLite.
        //если БД не существует, dbHelper вызовет метод onCreate(),
        //если версия БД изменилась, dbHelper вызовет метод onUpgrade().

        //в любом случае вернётся существующая, толькочто созданная или обновлённая БД.
        SQLiteDatabase database = dbHelper.getWritableDatabase();


        //класс ContentValues используется для добавления новых строк в таблицу.
        //каждый объект этого класса представляет собой одну строку таблицы и
        //выглядит, как массив с именами столбцов и значениями, которые им соответствуют.
        ContentValues contentValues = new ContentValues();

        switch (view.getId()) {

            case R.id.main_btnAdd:

                //добавляем пары ключ-значение.
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_EMAIL, email);
                //id заполнится автоматически.

                //вставляем подготовленные строки в таблицу.
                //второй аргумент используется для вставки пустой строки,
                //сейчас он нам не нужен, поэтому он = null.
                database.insert(DBHelper.TABLE_NAME, null, contentValues);

                Log.d("myLog","Information is added");
                break;
            case R.id.main_btnRead:
                //группировку и сортировку не используем, поэтому используем везде null.

                //метод query() возвращает объект типа Cursor,
                //его можно рассматривать как набор строк с данными.
                Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

                //метод cursor.moveToFirst() делает 1-ю запись в cursor активной
                //и проверяет, есть ли в cursor что-то.
                if (cursor.moveToFirst()) {
                    //получаем порядковые номера столбцов по их именам.
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);

                    //с помощью метода .moveToNext() перебираем все строки в cursor-е.
                    do {
                        Log.d("myLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", email = " + cursor.getString(emailIndex));
                    } while (cursor.moveToNext());

                } else {
                    Log.d("myLog", "0 rows");
                }
                //в конце закрываем cursor. Освобождаем ресурс.
                cursor.close();
                break;
            case R.id.main_btnClear:
                //отсутствуют условия удаления, поэтому стоят null.
                database.delete(DBHelper.TABLE_NAME, null, null);
                break;
        }
        //закрываем соединение с БД.
        dbHelper.close();
    }
}
