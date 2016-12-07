package com.a5androidintern2.activityresult;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ColorActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnRed;
    Button btnGreen;
    Button btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        btnRed = (Button) findViewById(R.id.color_btnRed);
        btnGreen = (Button) findViewById(R.id.color_btnGreen);
        btnBlue = (Button) findViewById(R.id.color_btnBlue);

        btnRed.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.color_btnRed:
                //помещаем в интент объект с именем "color" и значениемм константой цвета.
                //для значения цветов используем системные константы.
                intent.putExtra("color", Color.RED);
                break;
            case R.id.color_btnGreen:
                intent.putExtra("color", Color.GREEN);
                break;
            case R.id.color_btnBlue:
                intent.putExtra("color", Color.BLUE);
                break;
        }
        //ставим статус RESULT_OK.
        //говорим, что надо вернуть intent в качестве результата.
        setResult(RESULT_OK, intent);

        //закрываем активити.
        finish();
    }
}
