package com.a5androidintern2.p0272_getintentaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnTime;
    Button btnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTime = (Button) findViewById(R.id.but_time);
        btnDate = (Button) findViewById(R.id.but_date);

        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()){
            case R.id.but_time:
                intent = new Intent("info.fandroid.intent.action.time");
                startActivity(intent);
                break;

            case R.id.but_date:
                intent = new Intent("info.fandroid.intent.action.date");
                startActivity(intent);
                break;
        }
    }
}

