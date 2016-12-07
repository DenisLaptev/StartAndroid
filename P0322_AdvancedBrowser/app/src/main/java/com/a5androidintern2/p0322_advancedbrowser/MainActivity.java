package com.a5androidintern2.p0322_advancedbrowser;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    EditText et;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.main_tv);
        et = (EditText) findViewById(R.id.main_editText);
        btn = (Button) findViewById(R.id.main_button);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        //Intent.ACTION_VIEW - это системная константа,
        //означает, что мы хотим просмотреть что-либо.
        intent = new Intent(Intent.ACTION_VIEW);
        String address;
        address = et.getText().toString();
        intent.setData(Uri.parse(address));
        startActivity(intent);

    }
}
