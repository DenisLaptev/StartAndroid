package com.a5androidintern2.p0561_spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

    String[] data = {"one", "two", "three", "four", "five"};

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //адаптер.

        //simple_spinner_item-шаблон для выпадающего списка.
        //simple_spinner_dropdown_item-шаблон для пункта списка.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        //заголовок выпадающего списка. Prompt - подсказка, напоминание.
        spinner.setPrompt("Title");
        //метод .setSelection(2) устанавливает выделение на элементе.
        spinner.setSelection(2);
        //устанавливаем обработчик нажатия.
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //показываем позицию нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + (position+1), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
}
