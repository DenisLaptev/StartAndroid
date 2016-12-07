package com.a5androidintern2.p0701_saveinstancestate;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";
    int cnt = 0;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "onCreate");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //сохраняем счётчик.
        outState.putInt("count", cnt);

        Log.d(LOG_TAG, "onSaveInstanceState");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //считываем счётчик.
        cnt = savedInstanceState.getInt("count");

        Log.d(LOG_TAG, "onRestoreInstanceState");
    }

    protected void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "onStart");
    }

    protected void onRestart() {
        super.onRestart();

        Log.d(LOG_TAG, "onRestart");
    }

    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "onPause");
    }

    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "onResume ");
    }

    protected void onStop() {
        super.onStop();

        Log.d(LOG_TAG, "onStop");
    }

    protected void onDestroy() {
        super.onDestroy();

        Log.d(LOG_TAG, "onDestroy");
    }

    public void onclick(View v) {
        Toast.makeText(this, "Count = " + ++cnt, Toast.LENGTH_SHORT).show();
    }
}
