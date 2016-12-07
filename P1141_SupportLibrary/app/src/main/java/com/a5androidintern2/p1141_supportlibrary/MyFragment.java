package com.a5androidintern2.p1141_supportlibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {
    //тут и пригодится нам библиотека v4.
    //Будем наследовать ее класс android.support.v4.app.Fragment
    //при создании фрагмента

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, null);
    }
}