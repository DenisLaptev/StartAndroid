package com.a5androidintern2.p0241_twoactivitystate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener{

    //создадим константу TAG, мы будем её использовать в логах,
    //которые будем выводить после каждого метода жизненного цикла Activity.
    final String TAG = "lifecycle";

    //объявим кнопку.
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        //найдём кнопку по id.
        btn3 = (Button) findViewById(R.id.btn3);

        //присвоим кнопке обработчик.
        btn3.setOnClickListener(this);

        Log.d(TAG, "ActivityTwo onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "ActivityTwo onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "ActivityTwo onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "ActivityTwo onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "ActivityTwo onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "ActivityTwo onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "ActivityTwo onDestroy");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ActivityThree.class);
        startActivity(intent);
    }
}
