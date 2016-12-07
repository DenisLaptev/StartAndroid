package com.a5androidintern2.p0431_simplelistchoice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {
    //одиночный выбор из списка.

    final String LOG_TAG = "myLogs";

    Button btnChecked;
    ListView lvMain;

    String[] names;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView) findViewById(R.id.lvMain);

        //устанавливаем для списка режим выбора пунктов.
        //CHOICE_MODE_SINGLE - значит, что список будет хранить позицию
        //последнего нажатого пункта и мы всегда можем запросить у него эту информацию.
        lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //Создаем адаптер, используя массив из файла ресурсов.
        //Используем для создания адаптера метод .createFromResource().


        //simple_list_item_single_choice - это системный .xml (layout) файл (шаблон)
        //для пунктов меню.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.names,
                android.R.layout.simple_list_item_single_choice);
        //присваиваем адаптер списку.
        lvMain.setAdapter(adapter);

        //находим кнопку и присваиваем ей обработчик.
        btnChecked = (Button) findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);

        //получаем массив из файла ресурсов.
        names = getResources().getStringArray(R.array.names);
    }

    public void onClick(View arg0) {
        //пишем в лог выделенный элемент.
        Log.d(LOG_TAG, "checked: " + names[lvMain.getCheckedItemPosition()]);
    }
}
