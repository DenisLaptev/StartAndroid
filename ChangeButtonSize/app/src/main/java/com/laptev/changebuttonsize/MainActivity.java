package com.laptev.changebuttonsize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    //для SeekBar нужен обработчик, который будет реагировать на движение ползунка.
    //для этого реализуем интерфейс SeekBar.OnSeekBarChangeListener.

    //опишем компоненты.
    SeekBar sbWeight;
    Button btn1;
    Button btn2;

    LinearLayout.LayoutParams lParams1;
    LinearLayout.LayoutParams lParams2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //найдём компоненты по id.
        sbWeight = (SeekBar) findViewById(R.id.sbWeight);
        sbWeight.setOnSeekBarChangeListener(this);//связали элемент с обработчиком событий.

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        lParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
        lParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();
    }


    //методы иентерфейса SeekBar.OnSeekBarChangeListener
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        int leftValue = progress;
        int rightValue = seekBar.getMax() - progress;

        //вес левой кнопки = значению ползунка.
        //вес правой кнопки = макс. значение - текущее значение
        lParams1.weight = leftValue;
        lParams2.weight = rightValue;

        //для наглядности на кнопках отображается их "вес"
        btn1.setText(String.valueOf(leftValue));
        btn2.setText(String.valueOf(rightValue));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
