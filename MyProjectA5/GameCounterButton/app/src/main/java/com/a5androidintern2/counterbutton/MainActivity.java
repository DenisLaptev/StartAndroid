package com.a5androidintern2.counterbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bubbleButton;
    Button button1;

   
    TextView tvResult;

    int pressedTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bubbleButton = (Button) findViewById(R.id.bubble_button);
        button1 = (Button) findViewById(R.id.button1);


        tvResult = (TextView) findViewById(R.id.tvResult);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bubble_button: {
                pressedTimes++;

                tvResult.setText(String.valueOf(pressedTimes));
                break;
            }
            case R.id.button1: {
                tvResult.setText("You pressed the ordinary button!");
                break;
            }
        }
    }
}
