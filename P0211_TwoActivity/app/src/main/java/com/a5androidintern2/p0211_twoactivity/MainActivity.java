package com.a5androidintern2.p0211_twoactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //объявляем кнопки.
    Button btnActTwo;
    Button btnActTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //находим кнопки по id.
        btnActTwo = (Button) findViewById(R.id.btnActTwo);
        btnActTree = (Button) findViewById(R.id.btnActTree);

        //присваиваем кнопкам обработчик.
        btnActTwo.setOnClickListener(this);
        btnActTree.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //определяем, какая кнопка нажата.
        switch (view.getId()) {
            case R.id.btnActTwo:
                //для того, чтобы запускалось второе активити при нажатии кнопки.
                Intent intent = new Intent(this, ActivityTwo.class);
                startActivity(intent);
                break;
            case R.id.btnActTree:
                //для того, чтобы запускалось третье активити при нажатии кнопки.
                intent = new Intent(this, ActivityThree.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
