package com.a5androidintern2.p1051_fragmentdynamic;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends Activity {

    Fragment1 frag1;
    Fragment2 frag2;
    FragmentTransaction fTrans;
    CheckBox chbStack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //создаем пару фрагментов.
        frag1 = new Fragment1();
        frag2 = new Fragment2();

        //находим чекбокс.
        chbStack = (CheckBox)findViewById(R.id.chbStack);
    }

    public void onClick(View v) {
        //получаем менеджер фрагментов с помощью метода getFragmentManager.
        //Этот объект является основным для работы с фрагментами.

        //Далее, чтобы добавить/удалить/заменить фрагмент,
        //нам необходимо использовать транзакции.
        //Они аналогичны транзакциям в БД, где мы открываем транзакцию,
        //производим операции с БД, выполняем commit.
        //Здесь мы открываем транзакцию, производим операции
        //с фрагментами (добавляем, удаляем, заменяем), выполняем commit.
        fTrans = getFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.btnAdd:
                fTrans.add(R.id.frgmCont, frag1);
                break;
            case R.id.btnRemove:
                fTrans.remove(frag1);
                break;
            case R.id.btnReplace:
                fTrans.replace(R.id.frgmCont, frag2);
            default:
                break;
        }
        if (chbStack.isChecked()) {
            //проверяем чекбокс.
            //Если он включен, то добавляем транзакцию в BackStack.
            //Для этого используем метод addToBackStack.
            fTrans.addToBackStack(null);
        }
        fTrans.commit();
    }
}
