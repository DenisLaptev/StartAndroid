package com.a5androidintern2.p1082_actionbarnavigation;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements
        ActionBar.OnNavigationListener {

    String[] data = new String[] { "one", "two", "three" };

    final String LOG_TAG = "myLogs";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //получаем ActionBar и включаем ему режим навигации NAVIGATION_MODE_LIST.
        ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        //Для выпадающего списка необходимо создать адаптер,
        //реализующий SpinnerAdapter. Пусть это будет ArrayAdapter.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bar.setListNavigationCallbacks(adapter, this);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        Log.d(LOG_TAG, "selected: position = " + itemPosition + ", id = "
                + itemId + ", " + data[itemPosition]);
        return false;
    }
}
