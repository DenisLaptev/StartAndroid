package com.a5androidintern2.gamecustomlistview;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    private static final String[] catnames = { "Васька", "Барсик", "Мурзик",
            "Рыжик", "Дорофей", "Маркиз" };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView listView = getListView();
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, catnames));
    }
}
