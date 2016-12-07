package com.a5androidintern2.p0421_simplelist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей" };

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //находим список.
        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        //создаем адаптер.

        //мы использовали этот конструктор:
        //public ArrayAdapter (Context context, int textViewResourceId, T[] objects)
        //и передали ему следующие параметры:

        //this – контекст
        //android.R.layout.simple_list_item_1 – это системный layout-файл,
        //                                      который представляет собой TextView
        //names – массив данных, которые мы хотим вывести в список.




        /*
        //создадим адаптер, использующий системный layout-файл android.R.layout.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);

        */


        /*
        Когда список при формировании запрашивает очередной пункт,
        адаптер берет этот Layout-ресурс simple_list_item_1,
        прогоняет его через LayoutInflater и получает View,
        преобразует View к TextView, присваивает ему текст из массива данных
        и отдает списку.
        */



        //создаем адаптер, использующий созданный нами layout-файл android.R.layout.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.my_list_item,
                names);


        //присваиваем адаптер списку.
        lvMain.setAdapter(adapter);
    }
}