package com.a5androidintern2.mybuttondesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton btnAndroid;
    ImageButton btnFitness;
    ImageButton btnStar;
    ImageButton btnSend;
    ImageButton btnAttach;
    ImageButton btnAudio;
    ImageButton btnCloud;
    ImageButton btnRun;
    ImageButton btnPlay;

    TextView tvTestTitle;
    TextView tvTestValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAndroid = (ImageButton) findViewById(R.id.btnAndroid);
        btnFitness = (ImageButton) findViewById(R.id.btnFitness);
        btnStar = (ImageButton) findViewById(R.id.btnStar);
        btnSend = (ImageButton) findViewById(R.id.btnSend);
        btnAttach = (ImageButton) findViewById(R.id.btnAttach);
        btnAudio = (ImageButton) findViewById(R.id.btnAudio);
        btnCloud = (ImageButton) findViewById(R.id.btnCloud);
        btnRun = (ImageButton) findViewById(R.id.btnRun);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);

        tvTestTitle = (TextView) findViewById(R.id.tvTestTitle);
        tvTestValue = (TextView) findViewById(R.id.tvTestValue);
    }

    public void onBtnClick(View v) {
        int btnId = v.getId();
        switch (btnId) {
            case R.id.btnAndroid:
                tvTestValue.setText("робот");
                break;

            case R.id.btnFitness:
                tvTestValue.setText("фитнес");
                break;

            case R.id.btnStar:
                tvTestValue.setText("звезда");
                break;

            case R.id.btnSend:
                tvTestValue.setText("самолёт");
                break;

            case R.id.btnAttach:
                tvTestValue.setText("срепка");
                break;

            case R.id.btnAudio:
                tvTestValue.setText("нота");
                break;

            case R.id.btnCloud:
                tvTestValue.setText("облако");
                break;

            case R.id.btnRun:
                tvTestValue.setText("бег");
                break;

            case R.id.btnPlay:
                tvTestValue.setText("стрелка");
                break;
        }

    }
}
