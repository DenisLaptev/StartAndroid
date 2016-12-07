package com.a5androidintern2.p0331_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //чтобы загрузка текста происходила автоматически при запуске приложения,
    //а сохранение текста происходило автоматически при закрытии приложения,
    //добавим методы loadText() в метод onCreate()
    //и saveText() в метод onDestroy() жизненного цикла MainActivity.

    EditText et;
    Button btnSave;
    Button btnLoad;

    SharedPreferences sPref;

    final static String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.main_editText);
        btnSave = (Button) findViewById(R.id.main_btnSave);
        btnLoad = (Button) findViewById(R.id.main_btnLoad);

        btnSave.setOnClickListener(this);
        btnLoad.setOnClickListener(this);

        //чтобы загрузка текста происходила автоматически при запуске приложения,
        //добавим метод loadText() в метод onCreate().
        loadText();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btnSave:
                //при нажатии на кнопку btnSave вызывается метод saveText().
                //метод saveText() мы создаём сами.
                saveText();
                break;
            case R.id.main_btnLoad:
                loadText();
                break;
        }
    }

    private void saveText() {
        //с помощью метода getPreferences() получаем объект SharedPreferences sPref,
        //который позволяет работать с данными - считывать и записывать.
        //константа MODE_PRIVATE используется для настройки доступа и означает, что
        //после сохранения данные будут видны только этому приложению.


        //можно также использовать метод getSharedPreferences("My Pref", MODE_PRIVATE).
        //этот метод позволяет указать явно .xml-файл ("My Pref"), куда будут сохраняться данные.
        //по умолчанию, это файл data.xml.
        sPref = getPreferences(MODE_PRIVATE);

        //далее, чтобы редактировать данные, необходим объект Editor.
        SharedPreferences.Editor ed = sPref.edit();

        //в метод putString() указываем наименование переменой.
        ed.putString(SAVED_TEXT, et.getText().toString());

        //чтобы данные сохранились, необходимо выполнить метод commit().
        ed.commit();

        //для наглядности выводим сообщение, что данные сохранены.
        Toast.makeText(MainActivity.this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    private void loadText() {
        //с помощью метода getPreferences() получаем объект SharedPreferences sPref.
        sPref = getPreferences(MODE_PRIVATE);

        //объект Editor не используем, т.к. нужно только чтение.
        //читаем с помощью метода getString().
        //первый параметр в методе getString() - это имя данного объекта.
        String savedText = sPref.getString(SAVED_TEXT, "");

        //выводим загруженный текст в поле EditText et.
        et.setText(savedText);

        //для наглядности выводим сообщение, что данные считаны.
        Toast.makeText(MainActivity.this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        //чтобы сохранение текста происходило автоматически при закрытии приложения,
        //добавим метод saveText() в метод onDestroy().
        super.onDestroy();
        saveText();
    }
}
