package com.a5androidintern2.p0941_servicekillclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStart(View v) {
        Intent intent = new Intent("com.a5androidintern2.p0942_servicekillserver.MyService");
//        intent.setPackage(this.getPackageName());
        intent.setPackage("com.a5androidintern2.p0942_servicekillserver");
        intent.putExtra("name", "value");
        startService(intent);

       // startService(new Intent(this, MyService.class));
    }
}
