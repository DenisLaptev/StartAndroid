package com.a5androidintern2.activityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class AlignActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLeft;
    Button btnCenter;
    Button btnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align);

        btnLeft = (Button) findViewById(R.id.align_btnLeft);
        btnCenter = (Button) findViewById(R.id.align_btnCenter);
        btnRight = (Button) findViewById(R.id.align_btnRight);

        btnLeft.setOnClickListener(this);
        btnCenter.setOnClickListener(this);
        btnRight.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.align_btnLeft:
                intent.putExtra("alignment", Gravity.LEFT);
                break;
            case R.id.align_btnCenter:
                intent.putExtra("alignment", Gravity.CENTER);
                break;
            case R.id.align_btnRight:
                intent.putExtra("alignment", Gravity.RIGHT);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
