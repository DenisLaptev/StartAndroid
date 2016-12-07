package com.a5androidintern2.p0251_twoactivitydialogtheme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ActivityThree extends AppCompatActivity {

    //создадим константу TAG, мы будем её использовать в логах,
    //которые будем выводить после каждого метода жизненного цикла Activity.
    final String TAG = "lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        Log.d(TAG, "ActivityThree onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "ActivityThree onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "ActivityThree onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "ActivityThree onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "ActivityThree onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "ActivityThree onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "ActivityThree onDestroy");
    }
}
