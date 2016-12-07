package com.a5androidintern2.p1121_dynamicactionbar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    final int MENU_ID = 1;

    CheckBox chbAddDel;
    CheckBox chbVisible;

    Fragment frag1;
    Fragment frag2;
    Fragment frag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chbAddDel = (CheckBox) findViewById(R.id.chbAddDel);
        chbVisible = (CheckBox) findViewById(R.id.chbVisible);

        frag1 = new Fragment1();
        frag2 = new Fragment2();

        frag = frag1;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        //в зависимости от значения чекбокса chbVisible настраиваем видимость группы groupVsbl.
        menu.setGroupVisible(R.id.groupVsbl, chbVisible.isChecked());

        //в зависимости от значения чекбокса chbAddDel создаем или удаляем элемент.
        if (chbAddDel.isChecked()) {
            menu.add(0, MENU_ID, 0, R.string.menu_item1)
                    .setIcon(android.R.drawable.ic_delete)
                    .setShowAsAction(
                            MenuItem.SHOW_AS_ACTION_ALWAYS
                                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        } else {
            menu.removeItem(MENU_ID);
        }
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            //для чекбоксов вызываем метод invalidateOptionsMenu.
            case R.id.chbAddDel:
            case R.id.chbVisible:

                //метод invalidateOptionsMenu - это перерисовка меню/ActionBar.
                invalidateOptionsMenu();
                break;

            //А по нажатию на кнопку поочередно выводим на экран Fragment1 или Fragment2.
            case R.id.btnFrag:
                frag = (frag == frag1) ? frag2 : frag1;
                getFragmentManager().beginTransaction().replace(R.id.cont, frag)
                        .commit();
                break;
            default:
                break;
        }

    }
}
