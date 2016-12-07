package com.a5androidintern2.p0761_tab;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // такого метода нет при extends Activity: getTabHost();
        //находим компонент TabHost.
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);


        //первичная инициализация.
        //В этом методе TabHost находит в себе TabWidget и FrameLayout.
        tabHost.setup();



        //создаём 3 вкладки. Используем .newTabSpec("имя тега").
        //тег - это некий строковый идентификатор вкладки.

//        TabHost.TabSpec spec=tabHost.newTabSpec("mitab1");
//
//        spec.setIndicator("sss",
//                getResources().getDrawable(android.R.drawable.ic_btn_speak_now));
//        Intent sssIntent = new Intent(this, MainActivity.class);
//        spec.setContent(sssIntent);
//        tabHost.addTab(spec);


        TabHost.TabSpec tabSpec;

//1-я вкладка.
        //создаем вкладку и указываем тег.
        tabSpec = tabHost.newTabSpec("tag1");

        //название вкладки.
        tabSpec.setIndicator("Вкладка 1");

        //указываем id компонента из FrameLayout, которое станет содержимым вкладки.
        tabSpec.setContent(R.id.tvTab1);

        //добавляем вкладку в корневой элемент (в TabHost).
        tabHost.addTab(tabSpec);

//2-я вкладка.
/*        tabSpec = tabHost.newTabSpec("tag2");
        //заголовок данной вкладки содержит название и картинку.
        //в нашем случае вместо картинки идет xml-файл,
        //который определяет картинку по состоянию вкладки.
        tabSpec.setIndicator("Вкладка 2", getResources().getDrawable(R.drawable.tab_icon_selector));
//        tabSpec.setIndicator("Вкладка 2", getResources().getDrawable(android.R.drawable.ic_dialog_map));
        tabSpec.setContent(R.id.tvTab2);
        tabHost.addTab(tabSpec);*/

//        tabHost.addTab(tabHost.newTabSpec("tab_test1").setIndicator(getLayoutInflater().inflate(R.layout.view1, null)).setContent(R.id.tvTab2));

//3-я вкладка.
        tabSpec = tabHost.newTabSpec("tag3");
        //создаем View из layout-файла
        View v = getLayoutInflater().inflate(R.layout.tab_header, null);
        //и устанавливаем его, как заголовок.
        tabSpec.setIndicator(v);
        tabSpec.setContent(R.id.tvTab3);
        tabHost.addTab(tabSpec);

        //вторая вкладка будет выбрана по умолчанию.
        tabHost.setCurrentTabByTag("tag2");

        //обработчик, который срабатывает при переключении вкладок.
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                //выводим подсказку с тегом вкладки.
                Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
