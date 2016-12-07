package com.laptev.menuproject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    CheckBox chb1;
    CheckBox chb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        chb1 = (CheckBox) findViewById(R.id.checkBox1);
        chb2 = (CheckBox) findViewById(R.id.checkBox2);

//        chb1.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        menu.setGroupVisible(R.id.group1,chb1.isChecked());
        menu.setGroupVisible(R.id.group2,chb2.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    //метод создаёт меню.
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);


        //можно добавить пункт меню с помощью java.
        //метод .setCheckable(true) указывает на то, что пункт будет с галочкой.
       // menu.add(3,4,4,"item4").setCheckable(true);
        return true;
    }


    @Override
    //метод для обработки нажатых пунктов меню.
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, getString(R.string.action_settings), Toast.LENGTH_LONG).show();
                break;

            case R.id.mail:
                Toast.makeText(MainActivity.this, getString(R.string.mail), Toast.LENGTH_LONG).show();
                break;

            case R.id.item1:
                Toast.makeText(MainActivity.this, getString(R.string.item1), Toast.LENGTH_LONG).show();
                break;

            case R.id.item2:
                Toast.makeText(MainActivity.this, getString(R.string.item2), Toast.LENGTH_LONG).show();
                break;

            case R.id.item3:
                Toast.makeText(MainActivity.this, getString(R.string.item3), Toast.LENGTH_LONG).show();
                break;

            case 4:
                item.setChecked(!item.isChecked());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
