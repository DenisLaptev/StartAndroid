package com.a5androidintern2.p0711_preferencessimple;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PrefActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //метод берёт файл pref.xml  по нему создаёт экран настроек.
        addPreferencesFromResource(R.xml.pref);
    }
}
