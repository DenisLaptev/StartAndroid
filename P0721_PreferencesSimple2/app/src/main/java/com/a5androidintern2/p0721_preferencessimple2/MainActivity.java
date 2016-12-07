package com.a5androidintern2.p0721_preferencessimple2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView tvInfo;
    SharedPreferences sp;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        //получаем доступ к настройкам.
        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    protected void onResume() {
        //если ничего не выбрано, то отображаем "не выбрано".
        //иначе, отображаем выбранное значение.
        String listValue = sp.getString("list", "не выбрано");
        tvInfo.setText("Значение списка - " + listValue);
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        //создаём пункт меню.
        MenuItem mi = menu.add(0, 1, 0, "Preferences");

        //вешаем на созданный пункт меню интент. Он запустит экран настроек.
        mi.setIntent(new Intent(this, PrefActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
}
