package com.laptev.createbuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    //опишем все компоненты, созданные в макете.
    LinearLayout llMain;
    RadioGroup rgGravity;
    EditText etName;
    Button btnCreate;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //найдём все компоненты по их id.
        llMain = (LinearLayout) findViewById(R.id.llMain);
        rgGravity = (RadioGroup) findViewById(R.id.rgGravity);
        etName = (EditText) findViewById(R.id.etName);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnClear = (Button) findViewById(R.id.btnClear);


        //присваиваем обработчик кнопкам.
        btnCreate.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //напишем код для динамического добавления компонентов (кнопок) на экран.
        switch (v.getId()) {

            case R.id.btnCreate:
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                int btnGravity = Gravity.LEFT;

                switch (rgGravity.getCheckedRadioButtonId()) {
                    case R.id.rbLeft:
                        btnGravity = Gravity.LEFT;
                        break;
                    case R.id.rbCenter:
                        btnGravity = Gravity.CENTER_HORIZONTAL;
                        break;
                    case R.id.rbRight:
                        btnGravity = Gravity.RIGHT;
                        break;
                }

                //переносим полученное значение btnGravity в LinearLayout.LayoutParams lParams
                lParams.gravity = btnGravity;

                //создаём объект Button,
                Button btnNew = new Button(this);
                //устанавливаем для него текст
                btnNew.setText(etName.getText().toString());

                /*
                //установим гравитацию для кнопки.
                btnNew.setGravity(btnGravity);
                */
                
                //и добавляем в LinearLayout.
                llMain.addView(btnNew, lParams);

                break;

            case R.id.btnClear:
                //метод удаляет все дочерние view с LinearLayout.
                llMain.removeAllViews();

                //всплывающая подсказка говорит, что все кнопки удалены.
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
