package com.a5androidintern2.simpleactivityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        editText = (EditText) findViewById(R.id.name_editText);
        button = (Button) findViewById(R.id.name_button_ok);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //создаём интент и помещаем в него данные из поля ввода EditText.
        //при этом мы ни как не адресуем этот интент. Ни класс, ни экшн мы не указываем.
        Intent intent = new Intent();
        intent.putExtra("name", editText.getText().toString());

        //константа RESULT_OK означает успешное завершение вызова.
        //именно она передаётся в параметр resultCode метода onActivityResult.
        setResult(RESULT_OK, intent);

        //методом finish() мы завершаем работу NameActivity,
        //чтобы переключиться в MainActivity.
        finish();

    }
}
