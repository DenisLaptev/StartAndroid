package com.a5androidintern2.p1121_dynamicactionbar;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment {

    public void onCreate(Bundle savedInstanceState) {

        //с помощью setHasOptionsMenu включаем режим вывода элементов фрагмента в ActionBar.
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //создаем View.
        return inflater.inflate(R.layout.fragment1, null);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        //создаём меню.
        inflater.inflate(R.menu.fragment1, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
