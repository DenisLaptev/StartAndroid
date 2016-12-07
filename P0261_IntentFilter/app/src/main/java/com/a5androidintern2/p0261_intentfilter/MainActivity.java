package com.a5androidintern2.p0261_intentfilter;

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

        btnTime = (Button) findViewById(R.id.btnTime);
        btnDate = (Button) findViewById(R.id.btnDate);

        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //создадим Intent.
        Intent intent;

        //определим, какая кнопка была нажата.
        switch (view.getId()){
            case R.id.btnTime:
                intent = new Intent("info.fandroid.intent.action.time");
                startActivity(intent);
                break;
            case R.id.btnDate:
                intent = new Intent("info.fandroid.intent.action.date");
                startActivity(intent);
                break;
        }
    }
}
