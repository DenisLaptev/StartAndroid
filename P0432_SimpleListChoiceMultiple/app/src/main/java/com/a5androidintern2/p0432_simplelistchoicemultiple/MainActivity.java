package com.a5androidintern2.p0432_simplelistchoicemultiple;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {
    //множественный выбор из списка.

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
        //CHOICE_MODE_MULTIPLE - позволяет множественный выбор.
        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //Создаем адаптер, используя массив из файла ресурсов.
        //Используем для создания адаптера метод .createFromResource().


        //simple_list_item_single_choice - это системный .xml (layout) файл (шаблон)
        //для пунктов меню, позволяет множественный выбор.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.names,
                android.R.layout.simple_list_item_multiple_choice);
        //присваиваем адаптер списку.
        lvMain.setAdapter(adapter);

        //находим кнопку и присваиваем ей обработчик.
        btnChecked = (Button) findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);

        //получаем массив из файла ресурсов.
        names = getResources().getStringArray(R.array.names);
    }

    public void onClick(View arg0) {

        // пишем в лог выделенные элементы
        Log.d(LOG_TAG, "checked: ");

        //Мы получаем позиции выделенных элементов в виде объекта SparseBooleanArray.
        //Он представляет собой Map(int, boolean).
        //Ключ (int) – это позиция элемента,
        //а значение (boolean) – это выделен пункт списка или нет.
        //Причем SparseBooleanArray хранит инфу не о всех пунктах,
        //а только о тех, с которыми проводили действие (выделяли и снимали выделение).
        //Мы перебираем его содержимое,
        //получаем позицию пункта
        //и, если пункт выделен, то выводим в лог имя из массива,
        //соответствующее позиции пункта.
        SparseBooleanArray sbArray = lvMain.getCheckedItemPositions();
        for (int i = 0; i < sbArray.size(); i++) {
            int key = sbArray.keyAt(i);
            if (sbArray.get(key))
                Log.d(LOG_TAG, names[key]);
        }
    }
}
