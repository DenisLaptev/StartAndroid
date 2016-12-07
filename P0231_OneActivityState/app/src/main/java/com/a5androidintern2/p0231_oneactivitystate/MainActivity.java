package com.a5androidintern2.p0231_oneactivitystate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    //создадим константу TAG, мы будем её использовать в логах,
    //которые будем выводить после каждого метода жизненного цикла Activity.
    final String TAG = "lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Activity создано");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "Activity становится видимым");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "Activity получает фокус (состояние Resumed)");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "Activity приостановлено (состояние Paused)");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "Activity остановлено (состояние Stopped)");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "Activity уничтожено");
    }
}
