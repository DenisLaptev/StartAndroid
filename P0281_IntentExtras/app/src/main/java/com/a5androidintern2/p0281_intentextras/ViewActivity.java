package com.a5androidintern2.p0281_intentextras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        textView = (TextView) findViewById(R.id.textView);

        //получаем интент.
        Intent intent = getIntent();

        //извлекаем объект String name из intent-а.
        String name = intent.getStringExtra("name");

        //формируем строку textView с использованием полученных данных.
        textView.setText(name);

    }
}
