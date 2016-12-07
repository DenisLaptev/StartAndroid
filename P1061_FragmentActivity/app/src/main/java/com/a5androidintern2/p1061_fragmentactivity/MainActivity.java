package com.a5androidintern2.p1061_fragmentactivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements Fragment2.onSomeEventListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Здесь мы просто добавляем Fragment2 в контейнер.
        Fragment frag2 = new Fragment2();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment2, frag2);
        ft.commit();
    }


    public void onClick(View v) {

        //получили доступ к фрагментам из активити.
        //Нажали кнопку в активити, а во фрагментах отобразился текст.
        Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
        ((TextView) frag1.getView().findViewById(R.id.textView))
                .setText("Access to Fragment 1 from Activity");

        Fragment frag2 = getFragmentManager().findFragmentById(R.id.fragment2);
        ((TextView) frag2.getView().findViewById(R.id.textView))
                .setText("Access to Fragment 2 from Activity");
    }


    @Override
    public void someEvent(String s) {
        //Реализуем метод someEvent. Просто ищем первый фрагмент и вставляем туда текст.
        Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
        ((TextView)frag1.getView().findViewById(R.id.textView)).setText("Text from Fragment 2:" + s);
    }
}
