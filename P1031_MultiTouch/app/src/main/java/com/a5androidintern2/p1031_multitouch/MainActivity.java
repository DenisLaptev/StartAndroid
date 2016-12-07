package com.a5androidintern2.p1031_multitouch;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTouchListener {

    StringBuilder sb = new StringBuilder();//будет содержать информацию о движении.
    TextView tv;
    int upPI = 0;
    int downPI = 0;
    boolean inTouch = false;
    String result = "";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //создаем TextView.
        tv = new TextView(this);
        tv.setTextSize(30);

        //присваиваем обработчик – текущее Activity.
        tv.setOnTouchListener(this);

        //помещаем TextView в Activity.
        setContentView(tv);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        //событие.

        //Если для одного касания мы использовали метод getAction,
        //чтобы понять какое событие произошло,
        //то с мультитачем надо использовать getActionMasked.
        int actionMask = event.getActionMasked();
        //индекс касания.
        int pointerIndex = event.getActionIndex();
        //число касаний.
        int pointerCount = event.getPointerCount();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN: //первое касание.

                //метка inTouch = true для нас будет означать, что есть касания.
                inTouch = true;

                //Обратите внимание, что в этой ветке case мы
                //не ставим break – следующая case-ветка (ACTION_POINTER_DOWN)
                //также выполнится при ACTION_DOWN.
            case MotionEvent.ACTION_POINTER_DOWN: //последующие касания.
                downPI = pointerIndex;
                break;

            case MotionEvent.ACTION_UP: //прерывание последнего касания.
                inTouch = false;//отсутствие касания.
                sb.setLength(0);
            case MotionEvent.ACTION_POINTER_UP: //прерывания касаний.
                upPI = pointerIndex;
                break;

            case MotionEvent.ACTION_MOVE: //движение.
                sb.setLength(0);

                for (int i = 0; i < 10; i++) {
                    sb.append("Index = " + i);
                    if (i < pointerCount) {
                        sb.append(", ID = " + event.getPointerId(i));
                        sb.append(", X = " + event.getX(i));
                        sb.append(", Y = " + event.getY(i));
                    } else {
                        sb.append(", ID = ");
                        sb.append(", X = ");
                        sb.append(", Y = ");
                    }
                    sb.append("\r\n");
                }
                break;
        }
        //пишем индекс последнего касания и последнего завершенного касания.
        result = "down: " + downPI + "\n" + "up: " + upPI + "\n";

        if (inTouch) {
            //Если в данный момент есть касание (inTouch),
            //то добавляем в результат содержимое StringBuilder
            //с подробной инфой о всех касаниях.
            result += "pointerCount = " + pointerCount + "\n" + sb.toString();
        }

        //выводим result в TextView.
        tv.setText(result);
        return true;
    }
}
