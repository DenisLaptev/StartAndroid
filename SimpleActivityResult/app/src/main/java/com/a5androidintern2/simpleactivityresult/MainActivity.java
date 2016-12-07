package com.a5androidintern2.simpleactivityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvName;
    Button btnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (TextView) findViewById(R.id.main_tv_name);
        btnName = (Button) findViewById(R.id.main_button);

        btnName.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, NameActivity.class);

        //для отправки интента будем использовать не startActivity(intent),
        //а startActivityForResult().
        //особенность этого метода в том, что он связывает два активити так, что
        //при вызове второго активити из первого в момент закрытия второго активити
        //вызывается метод onActivityResult в первом активити.
        //В этот метод передаются данные введённые на втором активити.



        //в качестве параметров в метод startActivityForResult() передаём интент и
        //реквест-код (requestCode), он необходим для идентификации.
        startActivityForResult(intent, 1);
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //requestCode - это тот же идентификатор, что и в методе startActivityForResult().
        //              по нему мы определяем, с какого активити пришёл результат.
        //resultCode - это код возврата, он определяет, успешно прошёл вызов или нет.
        //data - это интент, в котором возвращаются данные.

        super.onActivityResult(requestCode, resultCode, data);

        //из data мы будем получать объект name,
        //который будем выводить на экран в TextView tvName.
        if (data == null){
            return;
        }
        String name = data.getStringExtra("name");
        tvName.setText("Your name is " + name);
    }
}
