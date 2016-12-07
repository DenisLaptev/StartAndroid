package com.a5androidintern2.menuproject3;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import static com.a5androidintern2.menuproject3.R.id.group1;

public class MainActivity extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener{

    CheckBox chb1;
    CheckBox chb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chb1 = (CheckBox) findViewById(R.id.checkBox1);
        chb2 = (CheckBox) findViewById(R.id.checkBox2);

        chb1.setOnCheckedChangeListener(this);
        chb2.setOnCheckedChangeListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //метод создаёт меню.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //метод вызывается при щелчке на меню
        //или при запуске метода invalidateOptionsMenu();.
        invalidateOptionsMenu();
        MenuItem action_item1 = menu.findItem(R.id.action_item1);

        if (chb2.isChecked()) {
            action_item1.setVisible(true);
        } else {
            action_item1.setVisible(false);
        }
        if (chb1.isChecked()) {
            menu.setGroupVisible(group1, true);
        } else {
            menu.setGroupVisible(group1, false);
        }
        if(!chb1.isChecked() && !chb2.isChecked()){
            menu.close();
        }

        invalidateOptionsMenu();

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        //Этот метод срабатывает, когда мы щёлкаем на галочку.
        //Это слушатель.
        if ((chb1.isChecked()) || (chb2.isChecked())) {
            invalidateOptionsMenu();
        }

        if (!(chb1.isChecked()) && !(chb2.isChecked())) {
            invalidateOptionsMenu();
        }

    }
}
