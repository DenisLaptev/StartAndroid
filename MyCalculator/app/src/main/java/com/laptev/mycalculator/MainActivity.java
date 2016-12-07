package com.laptev.mycalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int MENU_RESET_ID = 1;
    final int MENU_QUIT_ID = 2;

    //объявляем элементы экрана.

    EditText etNum1;
    EditText etNum2;

    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button btnDiv;

    TextView tvResult;

    String operation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //находим элементы экрана по их id.
        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnDiv = (Button) findViewById(R.id.btnDiv);

        tvResult = (TextView) findViewById(R.id.tvResult);

        //прописываем обработчик.
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //объявляем переменные.
        float num1 = 0;
        float num2 = 0;
        float result = 0;

        //проверяем поля на пустоту.
        if (TextUtils.isEmpty(etNum1.getText().toString())
                || TextUtils.isEmpty(etNum2.getText().toString())) {
            return;
        }

        //читаем EditText и заполняем переменные числами.
        num1 = Float.parseFloat(etNum1.getText().toString());
        num2 = Float.parseFloat(etNum2.getText().toString());

        //определяем нажатую кнопку и выполняем соответствующую операцию.
        //в operation пишем операцию, потом будем использовать в выводе.
        switch (v.getId()) {
            case R.id.btnAdd:
                operation = "+";
                result = num1 + num2;
                break;

            case R.id.btnSub:
                operation = "-";
                result = num1 - num2;
                break;

            case R.id.btnMult:
                operation = "*";
                result = num1 * num2;
                break;

            case R.id.btnDiv:
                operation = "/";
                result = num1 / num2;
                break;

            default:
                break;
        }

        //формируем строку вывода.
        tvResult.setText(num1 + " " + operation + " " + num2 + " = " + result);
    }

    //создадим меню с пунктами очистки полей
    //и выхода из приложения.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_RESET_ID, 0, "Reset");
        menu.add(0, MENU_QUIT_ID, 0, "Quit");
        return super.onCreateOptionsMenu(menu);
    }

    //обработка нажатий на пункты меню.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_RESET_ID:
                //очищаем поля.
                etNum1.setText("");
                etNum2.setText("");
                tvResult.setText("");
                break;
            case MENU_QUIT_ID:
                //выход из приложения.
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
