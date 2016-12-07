package com.a5androidintern2.p0401_layoutinflater;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    //LayoutInflater – это класс, который умеет
    //из содержимого layout-файла создать View-элемент.
    //Метод который это делает называется inflate.

    //public View inflate (int resource, ViewGroup root, boolean attachToRoot)

    //resource - ID layout-файла, который будет использован для создания View.
    //Например - R.layout.main

    //root – родительский ViewGroup-элемент для создаваемого View.
    //LayoutParams от этого ViewGroup присваиваются создаваемому View.

    //attachToRoot – присоединять ли создаваемый View к root.
    //Если true, то root становится родителем создаваемого View.
    //Т.е. это равносильно команде root.addView(View).
    //Если false – то создаваемый View просто получает LayoutParams от root,
    //но его дочерним элементом не становится.

    final String LOG_TAG = "myLogs";

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //получаем LayoutInflater методом getLayoutInflater().
        LayoutInflater ltInflater = getLayoutInflater();

        //используем LayoutInflater для получения view-элемента из layout-файла text.xml.
        //inflate (int resource, ViewGroup root, boolean attachToRoot)
        View view = ltInflater.inflate(R.layout.text, null, false);

        //считываем LayoutParams
        LayoutParams lp = view.getLayoutParams();

        //Добавим наш созданный элемент в linLayout из activity_main.xml
        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        linLayout.addView(view);

        Log.d(LOG_TAG, "Class of view: " + view.getClass().toString());
        Log.d(LOG_TAG, "LayoutParams of view is null: " + (lp == null));
        Log.d(LOG_TAG, "Text of view: " + ((TextView) view).getText());
    }
}
