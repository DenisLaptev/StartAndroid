package com.a5androidintern2.activityresult;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //добавим константы, которые мы будем использовать в качестве requestCode.

    final static int REQUEST_CODE_COLOR = 1;
    final static int REQUEST_CODE_ALIGN = 2;

    TextView tvText;
    Button btnColor;
    Button btnAlign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = (TextView) findViewById(R.id.main_tvText);
        btnColor = (Button) findViewById(R.id.main_btnColor);
        btnAlign = (Button) findViewById(R.id.main_btnAlign);

        btnColor.setOnClickListener(this);
        btnAlign.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        //определяем, какая кнопка была нажата и
        //посылаем интент с ожиданием возврата результата.
        switch (view.getId()) {
            case R.id.main_btnColor:
                intent = new Intent(this, ColorActivity.class);
                startActivityForResult(intent, REQUEST_CODE_COLOR);
                break;
            case R.id.main_btnAlign:
                intent = new Intent(this, AlignActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ALIGN);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_COLOR:
                    int color = data.getIntExtra("color", Color.WHITE);
                    tvText.setTextColor(color);
                    break;
                case REQUEST_CODE_ALIGN:
                    int align = data.getIntExtra("alignment", Gravity.LEFT);
                    tvText.setGravity(align);
                    break;
            }
        }
        else {
            Toast.makeText(MainActivity.this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }
}
