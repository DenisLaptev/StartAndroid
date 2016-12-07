package com.a5androidintern2.p0272_getintentaction;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.Date;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //получаем интент, который вызывал это активити.
        Intent intent = getIntent();

        //читаем из этого интента параметр action.
        String action = intent.getAction();


        //переменная содержит формат отображаемого времени или даты.
        String format = "";

        //переменная указывает, что отображается: время или дата.
        String textInfo = "";

        if(action.equals("info.fandroid.intent.action.time")){
            format = "HH:mm:ss";
            textInfo = "Time: ";
        } else if(action.equals("info.fandroid.intent.action.date")){
            format = "dd.MM.yyyy";
            textInfo = "Date: ";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String dateTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        TextView tvDate = (TextView) findViewById(R.id.textView);
        tvDate.setText(textInfo + dateTime);
    }
}
