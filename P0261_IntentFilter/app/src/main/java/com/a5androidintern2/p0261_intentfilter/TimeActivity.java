package com.a5androidintern2.p0261_intentfilter;

import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.Date;


public class TimeActivity extends AppCompatActivity {

    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        /*
        //это работает на моём телефоне.
        DateFormat df = new DateFormat();
        String time = (String) df.format("HH:mm:ss", System.currentTimeMillis());
        */

        //это работает для minSdkVersion (API) 24.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(new Date(System.currentTimeMillis()));

        tvTime = (TextView) findViewById(R.id.text_time);
        tvTime.setText(time);
    }
}
