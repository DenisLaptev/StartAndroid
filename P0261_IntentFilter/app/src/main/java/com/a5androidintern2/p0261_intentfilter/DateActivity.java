package com.a5androidintern2.p0261_intentfilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import android.text.format.*;
import java.util.Date;

public class DateActivity extends AppCompatActivity {

    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

       // String date = android.text.format.DateFormat.format("dd,MMM,yyyy",new Date(System.currentTimeMillis())).toString();

        DateFormat df = new DateFormat();
        //String date = (String)df.format("dd,MMM,yyyy",new Date(System.currentTimeMillis()));
        String date = (String)df.format("dd,MMM,yyyy",System.currentTimeMillis());

        tvDate = (TextView) findViewById(R.id.text_date);
        tvDate.setText(date);
    }
}
