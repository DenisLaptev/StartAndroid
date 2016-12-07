package com.a5androidintern2.p0441_simplelistevents;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    ListView lvMain;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView) findViewById(R.id.lvMain);

        //создаём и присваиваем списку адаптер.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.names, android.R.layout.simple_list_item_1);
        lvMain.setAdapter(adapter);

        //присваиваем списку два обработчика событий.


        /*
        метод onItemClick(AdapterView<?> parent, View view, int position, long id),
        где
            parent – View-родитель для нажатого пункта, в нашем случае - ListView
            view – это нажатый пункт,
                    в нашем случае – TextView из android.R.layout.simple_list_item_1
            position – порядковый номер пункта в списке
            id – идентификатор элемента,
        */
        lvMain.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d(LOG_TAG, "itemClick: position = " + position + ", id = "
                        + id);
            }
        });


        lvMain.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.d(LOG_TAG, "itemSelect: position = " + position + ", id = "
                        + id);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(LOG_TAG, "itemSelect: nothing");
            }
        });

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d(LOG_TAG, "scrollState = " + scrollState);
            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                Log.d(LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem
                        + ", visibleItemCount" + visibleItemCount
                        + ", totalItemCount" + totalItemCount);
            }
        });

        /*
        onScrollStateChanged(AbsListView view, int scrollState)
        //метод обработки состояний прокрутки

        view – это прокручиваемый элемент, т.е. ListView
        scrollState – состояние списка. Может принимать три значения:

SCROLL_STATE_IDLE = 0, список закончил прокрутку
SCROLL_STATE_TOUCH_SCROLL = 1, список начал прокрутку
SCROLL_STATE_FLING = 2, список «катнули», т.е. при прокрутке отпустили палец
                        и прокрутка дальше идет «по инерции»
        */



        /*
        onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
        //метод обработки прокрутки

        view – прокручиваемый элемент
        firstVisibleItem – первый видимый на экране пункт списка
        visibleItemCount – сколько пунктов видно на экране
        totalItemCount – сколько всего пунктов в списке

        Причем для параметров firstVisibleItem и visibleItemCount
        пункт считается видимым на экране даже, если он виден не полностью.
        */






    }
}
