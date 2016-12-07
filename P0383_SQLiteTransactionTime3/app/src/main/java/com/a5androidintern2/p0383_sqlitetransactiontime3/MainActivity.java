package com.a5androidintern2.p0383_sqlitetransactiontime3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //добавим константы названия БД и названия таблицы.
    public static final String DB_NAME = "MyDB" ;
    public static final String TABLE_NAME = "MyTable" ;

    //объявим переменную типа SQLiteDatabase (БД).
    public SQLiteDatabase database;
    TextView tvTime ;
    Button btnInsert ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_main);

        initDB();

        //находим экранные компоненты по id.
        tvTime = (TextView) findViewById(R.id. tvTime );
        btnInsert = (Button) findViewById(R.id. btnInsert );
        btnInsert.setOnClickListener( this);

    }

    private void initDB(){
        //наш метод создания БД.

        //создаём БД.
        database = this.openOrCreateDatabase( DB_NAME , MODE_PRIVATE , null );

        //создаём таблицу с 3-мя столбцами: FirstNumber, SecondNumber, Result.
        database.execSQL( "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(FirstNumber INT, SecondNumber INT, Result INT);" );

        //очищаем все поля таблицы.
        database.delete( TABLE_NAME, null , null );
    }

    @Override
    public void onClick(View view) {

        //очищаем таблицу.
        database.delete( TABLE_NAME, null , null );

        //переменная хранит моент нажатия кнопки (начало действия).
        long startTime = System. currentTimeMillis();

        //вставляем запись в БД.
        insertRecords();

        //переменная хранит моент конца действия.
        long diff = System. currentTimeMillis() - startTime;

        //выводим результат в текстовое поле, преобразовав время в строковый формат.
        tvTime.setText( "Time: " + Long. toString(diff) + " ms");
    }




    private void insertRecords(){
        //усовершенствованный метод вставки данных в БД с использованием транзакций.

        //строка хранит SQL-запрос.
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?);";

        //создаём SQLiteStatement statement, которому передаём скомпилированный SQL-запрос.
        SQLiteStatement statement = database.compileStatement(sql);

        //метод вызывается по началу транзакции.
        database .beginTransaction();
        try {
            for ( int i = 0; i < 1000 ; i++){

                //очищаем все текущие привязки.
                statement.clearBindings();

                //привязываем значения данных к столбцам.
                statement.bindLong( 1, i);
                statement.bindLong( 2, i);
                statement.bindLong( 3, i*i);

                //выполняем запрос.
                statement.execute();
            }

            //метод вызывается по окончании транзакции.
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }










    @Override
    protected void onDestroy() {
        //переопределим метод onDestroy(), в котором будем закрывать соединение с БД.
        database.close();
        super.onDestroy();
    }
}
