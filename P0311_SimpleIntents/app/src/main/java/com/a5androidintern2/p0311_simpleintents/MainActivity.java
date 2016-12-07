package com.a5androidintern2.p0311_simpleintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnWeb;
    Button btnMap;
    Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWeb = (Button) findViewById(R.id.main_btnWeb);
        btnMap = (Button) findViewById(R.id.main_btnMap);
        btnCall = (Button) findViewById(R.id.main_btnCall);

        btnWeb.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId()){
            //обрабатываем 3-мя разными способами,
            //но все они дают один эффект.

            case R.id.main_btnWeb:
                //Intent.ACTION_VIEW - это системная константа,
                //означает, что мы хотим просмотреть что-либо.
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com"));
                startActivity(intent);
                break;
            case R.id.main_btnMap:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:-0.45609946, -90.26607513"));
                startActivity(intent);
                break;
            case R.id.main_btnCall:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:12345"));
                startActivity(intent);
                break;
        }
    }
}
