package com.a5androidintern2.p1112_preferencefragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Fragment1 extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Метод addPreferencesFromResource прочитает
        //файл с описанием настроек и выведет их на экран.
        addPreferencesFromResource(R.xml.pref1);
    }

}

