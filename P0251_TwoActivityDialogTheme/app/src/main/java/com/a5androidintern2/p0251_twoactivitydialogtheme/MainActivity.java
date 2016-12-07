package com.a5androidintern2.p0251_twoactivitydialogtheme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //создадим константу TAG, мы будем её использовать в логах,
    //которые будем выводить после каждого метода жизненного цикла Activity.
    final String TAG = "lifecycle";

    //объявим кнопку.
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //найдём кнопку по id.
        btn2 = (Button) findViewById(R.id.btn2);

        //присвоим кнопке обработчик.
        btn2.setOnClickListener(this);

        Log.d(TAG, "MainActivity onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "MainActivity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "MainActivity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "MainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "MainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "MainActivity onDestroy");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ActivityTwo.class);
        startActivity(intent);
    }
}
