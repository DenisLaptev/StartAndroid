package com.a5androidintern2.p1112_preferencefragment;

import android.preference.PreferenceActivity;

import java.util.List;

public class MainActivity extends PreferenceActivity {

    public void onBuildHeaders(List<Header> target) {
        //Метод onBuildHeaders вызывается системой,
        //когда надо строить заголовки.
        //На вход он принимает список List<Header>,
        //который нам надо наполнить.
        //Для этого вызываем метод loadHeadersFromResource,
        //и передаем ему наш файл с заголовками и наполняемый список.
        loadHeadersFromResource(R.xml.pref_head, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        //в классе-насследнике класса PreferenceActivity необходимо переопределить метод
        //isValidFragment() для проверки, что данный фрагмент является допустимым типом для
        //прикрепления к активити.
        //Начиная с API 19.
        return Fragment1.class.getName().equals(fragmentName)
                || Fragment2.class.getName().equals(fragmentName);
    }
}
