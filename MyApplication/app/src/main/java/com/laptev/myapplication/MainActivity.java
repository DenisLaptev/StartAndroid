package com.laptev.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import static android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    //напишем сами логи. Тэг - это метка позволяющая отличить наш лог от других логов.
    private static final String TAG = "myLogs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //для логирования.
        //появится лог с текстом "Найдём View элементы".
        Log.d(TAG, "Найдём View элементы");

        //найдём view с помощью метода findViewById по их id:
        textView = (TextView) findViewById(R.id.largeText);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);


        //настроим действие после нажатия кнопок.

        //создадим обработчик, который говорит, что происходит при нажатии кнопок.
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
/*

                //создадим объект Toast для всплывающих подсказок.
                Toast toast = Toast.makeText(MainActivity.this, "Текст подсказки", Toast.LENGTH_LONG);
                //чтобы toast отобразился на экране.
                toast.show();

*/



                switch (v.getId()){
                    case R.id.button1:
                        textView.setText(R.string.push_info_1);

                        //для логирования.
                        //появится лог с текстом "Нажата кнопка 1".
                        Log.d(TAG, "Нажата кнопка 1");

                        break;
                    case R.id.button2:
                        textView.setText(R.string.push_info_2);

                        //для логирования.
                        //появится лог с текстом "Нажата кнопка 2".
                        Log.d(TAG, "Нажата кнопка 2");

                        break;
                    case R.id.button3:
                        textView.setText(R.string.push_info_3);

                        //для логирования.
                        //появится лог с текстом "Нажата кнопка 3".
                        Log.d(TAG, "Нажата кнопка 3");

                        break;
                    case R.id.button4:
                        textView.setText("Попытка / на 0");

                        try {
                            int i = 6/0;

                            //для логирования.
                            //появится лог с текстом "Попытка / на 0".
                            Log.d(TAG, "Попытка / на 0");
                        } catch (Exception e) {
                            Log.d(TAG, "Попытка / на 0", e);
                        }

                        break;

                    case R.id.button5:
                        textView.setText("Текст");

                        //для логирования.
                        //появится лог с текстом "Текст".
                        Log.d(TAG, "Текст");

                        //создадим объект Toast toast1 для всплывающих подсказок.
                        Toast toast1 = Toast.makeText(MainActivity.this, "Текст", Toast.LENGTH_LONG);

                        //задать расположение подсказки
                        toast1.setGravity(Gravity.BOTTOM,0,0);

                        //чтобы toast1 отобразился на экране.
                        toast1.show();


                        break;

                    case R.id.button6:
                        textView.setText("Текст и рис");

                        //для логирования.
                        //появится лог с текстом "Текст и рис".
                        Log.d(TAG, "Текст и рис");

                        //создадим объект Toast toast2 для всплывающих подсказок.
                        Toast toast2 = Toast.makeText(MainActivity.this, "Текст и рис", Toast.LENGTH_LONG);

                        //задать расположение подсказки
                        toast2.setGravity(Gravity.BOTTOM,0,0);

                        //Добавим картинку к текстовой подсказке.
                        LinearLayout toastImage = (LinearLayout) toast2.getView();
                        ImageView imageView = new ImageView(MainActivity.this);
                        imageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        toastImage.addView(imageView,0);//0 означает, что изображение выше текста.

                        //чтобы toast2 отобразился на экране.
                        toast2.show();

                        break;
                }
            }
        };

        //назначим обработчик кнопкам.
        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);


        textView.setOnClickListener(new OnClickListener() {
            //мы сделали обработчик для textView.
            //при нажатии на textView, у кнопки button3 меняется текст.
            @Override
            public void onClick(View v) {
                button4.setText("Всё");
                button5.setText("будет");
                button6.setText("хорошо");


            }
        });
    }


}
