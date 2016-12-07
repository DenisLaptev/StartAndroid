package com.a5androidintern2.p1021_touch;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import static android.view.MotionEvent.ACTION_MOVE;

public class MainActivity extends Activity implements OnTouchListener {
    //MainActivity реализует интерфейс OnTouchListener для того,
    //чтобы выступить обработчиком касаний.

    TextView tv;
    float x;
    float y;
    String sDown;
    String sMove;
    String sUp;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv = new TextView(this);
        tv.setOnTouchListener(this);
        setContentView(tv);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //метод onTouch. На вход методу идет View для которого
        //было событие касания и объект MotionEvent с информацией о событии.

        //Методы getX и getY дают нам X и Y координаты касания.
        x = event.getX();
        y = event.getY();

        //Метод getAction дает тип события касания:
        //ACTION_DOWN – нажатие
        //ACTION_MOVE – движение
        //ACTION_UP – отпускание
        //ACTION_CANCEL – практически никогда не случается.
        //Насколько я понял, возникает в случае каких-либо внутренних сбоев,
        //и следует трактовать это как ACTION_UP.
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //нажатие

                //пишем в sDown координаты нажатия.
                sDown = "Down: " + x + "," + y;
                sMove = ""; sUp = "";
                break;
            case ACTION_MOVE: //движение

                //пишем в sMove координаты точки текущего положения пальца.
                sMove = "Move: " + x + "," + y;
                break;
            case MotionEvent.ACTION_UP: //отпускание
            case MotionEvent.ACTION_CANCEL:
                sMove = "";

                //пишем в sUp координаты точки, в которой отпустили палец.
                sUp = "Up: " + x + "," + y;
                break;
        }

        //Все это в конце события выводим в TextView.
        tv.setText(sDown + "\n" + sMove + "\n" + sUp);

        //И возвращаем true – мы сами обработали событие.
        return true;
    }
}
