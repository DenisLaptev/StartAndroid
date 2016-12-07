package com.a5androidintern2.p0403_layoutinflateradvanced2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //получаем LayoutInflater методом getLayoutInflater().
        LayoutInflater ltInflater = getLayoutInflater();

        //находим элемент linLayout с экрана.
        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);

        //с помощью LayoutInflater создаем View-элемент из layout-файла text.xml.
        //созданный View-элемент получит
        //LayoutParams от root-элемента, и добавится к нему (true).
        View view1 = ltInflater.inflate(R.layout.text, linLayout, true);
        LayoutParams lp1 = view1.getLayoutParams();

        Log.d(LOG_TAG, "Class of view1: " + view1.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view1: " + lp1.getClass().toString());

        //находим элемент linLayout с экрана.
        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);

        //с помощью LayoutInflater создаем View-элемент из layout-файла text.xml.
        //созданный View-элемент получит
        //LayoutParams от root-элемента, и добавится к нему (true).
        View view2 = ltInflater.inflate(R.layout.text, relLayout, true);
        LayoutParams lp2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of view2: " + view2.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view2: " + lp2.getClass().toString());
    }
}
