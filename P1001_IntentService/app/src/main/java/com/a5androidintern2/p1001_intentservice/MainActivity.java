package com.a5androidintern2.p1001_intentservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStart(View v){

        //time - это время паузы, которую будем делать в сервисе.
        //label - метка, чтобы отличать вызовы.
        startService(new Intent(this, MyService.class).putExtra("time", 3).putExtra("label", "Call 1") );
        startService(new Intent(this, MyService.class).putExtra("time", 1).putExtra("label", "Call 2") );
        startService(new Intent(this, MyService.class).putExtra("time", 4).putExtra("label", "Call 3") );
    }
}
