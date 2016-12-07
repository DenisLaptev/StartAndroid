package com.a5androidintern2.p0571_gridview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends Activity {

    String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};

    //определяем GridView.
    GridView gvMain;

    //создаём адаптер.
    ArrayAdapter<String> adapter;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //R.id.tvText - поле, в которое адаптер будет вставлять текст.
        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, data);
        gvMain = (GridView) findViewById(R.id.gvMain);
        gvMain.setAdapter(adapter);
        adjustGridView();
    }


    private void adjustGridView() {
        //для настроек GridView.

        //устанавливаем число столбцов.
        //по умолчанию число столбцов=1.
        //gvMain.setNumColumns(3);


/*
        gvMain.setNumColumns(GridView.AUTO_FIT);

        Это свойство также может иметь значение AUTO_FIT.
        В этом случае проверяется значение поля атрибута columnWidth (ширина столбца).

- если ширина столбца явно указана,
то кол-во столбцов рассчитывается исходя из ширины,
доступной GridView, и ширины столбцов.

- иначе, кол-во столбцов считается равным 2
*/

        gvMain.setNumColumns(GridView.AUTO_FIT);
        gvMain.setColumnWidth(200);
        gvMain.setVerticalSpacing(5);
        gvMain.setHorizontalSpacing(5);
        gvMain.setStretchMode(GridView.STRETCH_COLUMN_WIDTH );

    }
}
