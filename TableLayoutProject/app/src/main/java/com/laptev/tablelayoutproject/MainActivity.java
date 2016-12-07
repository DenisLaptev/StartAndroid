package com.laptev.tablelayoutproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    TextView textView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //найдём view с помощью метода findViewById по их id:
        textView = (TextView) findViewById(R.id.largeText);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);

        //настроим действие после нажатия кнопок.
        //рассмотрим 3 способа.

        //1-й способ.
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Нажата кнопка 1");
            }
        });

        //2-й способ.
        button2.setOnClickListener(this);

        //3-й способ.
        /*
        указать для кнопки атрибут
        android:onClick="clickButton3"
        после нажатия сработает метод clickButton3
        определить метод clickButton3

        */

    }

    @Override
    public void onClick(View v) {
        textView.setText("Нажата кнопка 2");
    }

    public void clickButton3(View view){
        textView.setText("Нажата кнопка 3");
    }
}
