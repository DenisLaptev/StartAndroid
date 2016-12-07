package com.laptev.dynamiclayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //мы удалили файл макета из папки layout.
        //будем добавлять View-элементы програмно с помощью java.

        LinearLayout linearLayout = new LinearLayout(this);
        //установим ориентацию.
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //установим значения параметров для разных типов View на linearLayout-е.
        //для ViewGroup конструктор принимает на вход 2 параметра ширина и высота.
        ViewGroup.LayoutParams linLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //спомощью метода setContentView устанавливаем linearLayout, как корневой элемент экрана
        //с параметрами linLayoutParams.
        setContentView(linearLayout, linLayoutParams);

        //покачто linearLayout прозрачен, мы его не видим.

        //добавим View-элемент на linearLayout.


        ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //создали TextView, установили параметры и добавили в linearLayout.
        TextView tv = new TextView(this);
        tv.setText("TextView");
        tv.setLayoutParams(lpView);
        linearLayout.addView(tv);

        //аналогично для кнопки.
        Button btn = new Button(this);
        btn.setText("Button");
        linearLayout.addView(btn,lpView);//более краткая версия, вместо 2-х строчек.


        //ViewGroup.LayoutParams имеет только параметры ширина, высота.
        //Если мы хотим другие параметры, например, отступ, то используем LinearLayout.LayoutParams.

        LinearLayout.LayoutParams leftMarginParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftMarginParams.leftMargin = 50;//отступ слева = 50 пикселей.

        Button btn1 = new Button(this);
        btn1.setText("Button1");
        linearLayout.addView(btn1,leftMarginParams);

        LinearLayout.LayoutParams rightGravityParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightGravityParams.gravity = Gravity.RIGHT;//выравнивание по правому краю.

        Button btn2 = new Button(this);
        btn2.setText("Button2");
        linearLayout.addView(btn2,rightGravityParams);
    }
}
