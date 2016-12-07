package com.a5androidintern2.p0691_parcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View v) {
        MyObject myObj = new MyObject("text", 1);

        //создаём intent.
        Intent intent = new Intent(this, SecondActivity.class);

        //помещаем в intent объект MyObject.
        //в качестве ключа используем имя класса MyObject.class.getCanonicalName().
        intent.putExtra(MyObject.class.getCanonicalName(), myObj);
        Log.d(LOG_TAG, "startActivity");

        //отправляем интент с вызовом SecondActivity.
        startActivity(intent);
    }
}
