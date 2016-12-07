package com.a5androidintern2.p0731_preferencesenable;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

public class PrefActivity extends PreferenceActivity {
    //экран настроек.

    CheckBoxPreference chb3;
    PreferenceCategory categ2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

        //находим на экране CheckBox и Category.
        chb3 = (CheckBoxPreference) findPreference("chb3");
        categ2  = (PreferenceCategory) findPreference("categ2");

        //устанавливаем, что активность Category определяется с помощью CheckBox chb3
        //при старте экрана.
        categ2.setEnabled(chb3.isChecked());

        //для CheckBox-а прописываем обработчик.
        chb3.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                //устанавливаем, что активность Category определяется с помощью CheckBox chb3.
                categ2.setEnabled(chb3.isChecked());
                return false;
            }
        });
    }
}