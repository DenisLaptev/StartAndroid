package com.a5androidintern2.p0461_expandablelistevents;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;


public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    ExpandableListView elvMain;
    AdapterHelper ah;
    SimpleExpandableListAdapter adapter;
    TextView tvInfo;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        //создаем адаптер.
        //Благодаря классу AdapterHelper код создания адаптера занял всего 2 строчки.
        //создание объекта AdapterHelper и вызов метода .getAdapter().
        ah = new AdapterHelper(this);
        adapter = ah.getAdapter();


        //присваиваем адаптер списку.
        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);


        //добавляем обработчики.

        //нажатие на элемент.
        elvMain.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition,   int childPosition, long id) {

                //выводим в лог.
                Log.d(LOG_TAG, "onChildClick groupPosition = " + groupPosition +
                        " childPosition = " + childPosition +
                        " id = " + id);

                //в TextView сверху от списка выводим имя нажатого элемента и его группу.
                tvInfo.setText(ah.getGroupChildText(groupPosition, childPosition));
                return false;
            }
        });

        //нажатие на группу.
        elvMain.setOnGroupClickListener(new OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                //выводим в лог.
                Log.d(LOG_TAG, "onGroupClick groupPosition = " + groupPosition +
                        " id = " + id);
                //блокируем дальнейшую обработку события для группы с позицией 1
                if (groupPosition == 1) return true;

                return false;
            }
        });

        //сворачивание группы.
        elvMain.setOnGroupCollapseListener(new OnGroupCollapseListener() {
            public void onGroupCollapse(int groupPosition) {
                //groupPosition - позиция группы, которую свернули.
                Log.d(LOG_TAG, "onGroupCollapse groupPosition = " + groupPosition);
                tvInfo.setText("Свернули " + ah.getGroupText(groupPosition));
            }
        });

        //разворачивание группы.
        elvMain.setOnGroupExpandListener(new OnGroupExpandListener() {
            public void onGroupExpand(int groupPosition) {
                //groupPosition - позиция группы, которую свернули.
                Log.d(LOG_TAG, "onGroupExpand groupPosition = " + groupPosition);
                tvInfo.setText("Развернули " + ah.getGroupText(groupPosition));
            }
        });

        //разворачиваем группу с позицией 2
        elvMain.expandGroup(2);
    }
}