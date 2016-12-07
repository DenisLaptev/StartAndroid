package com.a5androidintern2.p1081_actionbarnavigation;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements android.support.v7.app.ActionBar.TabListener {
    //Обработчиком для табов мы сделали MainActivity,
    //оно реализует методы интерфейса ActionBar.TabListener:
    //onTabReselected – выбран уже выбранный таб
    //onTabSelected – таб выбран
    //onTabUnselected – таб более не выбран

    final String LOG_TAG = "myLogs";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //получаем доступ к ActionBar
        //и устанавливаем для него режим навигации в NAVIGATION_MODE_TABS.
//        ActionBar bar = getActionBar();
        android.support.v7.app.ActionBar bar = getSupportActionBar();

        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //добавляем табы.
        android.support.v7.app.ActionBar.Tab tab = bar.newTab();
        tab.setText("tab1");
        tab.setTabListener(this);
        bar.addTab(tab);

        tab = bar.newTab();
        tab.setText("tab2");
        tab.setTabListener(this);
        bar.addTab(tab);

    }

//    @Override
//    public void onTabReselected(Tab tab, FragmentTransaction ft) {
//        //выбран уже выбранный таб.
//        Log.d(LOG_TAG, "reselected tab: " + tab.getText());
//    }
//
//    @Override
//    public void onTabSelected(Tab tab, FragmentTransaction ft) {
//        //таб выбран.
//        Log.d(LOG_TAG, "selected tab: " + tab.getText());
//    }
//
//    @Override
//    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
//        //таб более не выбран.
//        Log.d(LOG_TAG, "unselected tab: " + tab.getText());
//    }

    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }
}
