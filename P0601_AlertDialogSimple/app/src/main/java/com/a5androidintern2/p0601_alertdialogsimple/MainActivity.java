package com.a5androidintern2.p0601_alertdialogsimple;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    final int DIALOG_EXIT = 1;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View v) {
        //вызываем диалог.
        showDialog(DIALOG_EXIT);
    }

    public void onBackPressed() {
        //Чтобы диалог вызывался не только по кнопке выход,
        //но и при нажатии на кнопку Назад в приложении,
        //добавьте вызов диалога в реализацию метода onBackPressed.

        //вызываем диалог.
        showDialog(DIALOG_EXIT);
    }

    protected Dialog onCreateDialog(int id) {

        //диалог может содержать максимум 3 кнопки:
        //положительная, отрицательная, нейтральная.
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            //заголовок.
            adb.setTitle(R.string.exit);
            //сообщение.
            adb.setMessage(R.string.save_data);
            //иконка.
            adb.setIcon(android.R.drawable.ic_dialog_info);
            //кнопка положительного ответа.
            adb.setPositiveButton(R.string.yes, myClickListener);
            //кнопка отрицательного ответа.
            adb.setNegativeButton(R.string.no, myClickListener);
            //кнопка нейтрального ответа.
            adb.setNeutralButton(R.string.cancel, myClickListener);

            //если хотите, чтобы вызванный диалог не закрывался по нажатию кнопки Назад,
            //то используйте метод setCancelable:
            adb.setCancelable(false);




            //создаем диалог.
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    OnClickListener myClickListener = new OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                //положительная кнопка.
                case Dialog.BUTTON_POSITIVE:
                    saveData();
                    finish();
                    break;
                //негативная кнопка.
                case Dialog.BUTTON_NEGATIVE:
                    finish();
                    break;
                //нейтральная кнопка.
                case Dialog.BUTTON_NEUTRAL:
                    break;
            }
        }
    };

    void saveData() {
        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
    }
}
