package com.a5androidintern2.p0691_parcelable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends Activity {

    final String LOG_TAG = "myLogs";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Log.d(LOG_TAG, "getParcelableExtra");

        //вытаскиваем объект из Intent.
        MyObject myObj = (MyObject) getIntent().getParcelableExtra(
                MyObject.class.getCanonicalName());

        //в лог выводим значения s и i.
        Log.d(LOG_TAG, "myObj: " + myObj.s + ", " + myObj.i);
    }
}
