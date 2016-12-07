package com.a5androidintern2.p0281_intentextras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        //поскольку мы точно указываем мя класса,
        //никакие поля в манифесте прописывать не нужно.
        //Здесь мы используем явный вызов.
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("name", editText.getText().toString());
        startActivity(intent);

    }
}
