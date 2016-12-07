package com.a5androidintern2.p1111_preferencefragment;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    //Мы здесь не используем никакой layout,
    //а сразу добавляем наш фрагмент в качестве контента,
    //используя корневой контейнер с ID android.R.id.content.

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Fragment1()).commit();

    }
}
