package com.a5androidintern2.p1433_drawingpath3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        //объектом Paint будем рисовать фигуры.
        Paint p;
        Path path;
        //текст, который будем выводить по кругу.
        String text;

        public DrawView(Context context) {
            super(context);
            //параметр Paint.ANTI_ALIAS_FLAG сглаживает кривые при рисовании.
            p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setStrokeWidth(1);
            p.setTextSize(20);
            path = new Path();
            //текст, который будем выводить по кругу.
            text = "Draw the text, with origin at (x,y), using the specified paint";
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //фон.
            canvas.drawARGB(80, 102, 204, 255);

            //черный.
            //очищаем path.
            path.reset();
            path.addCircle(200, 200, 100, Path.Direction.CW);
            p.setColor(Color.BLACK);
            //рисуем текст по кругу path кистью p.
            canvas.drawTextOnPath(text, path, 0, 0, p);

            //синий.
            //очищаем path.
            path.reset();
            path.addCircle(500, 200, 100, Path.Direction.CCW);
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.BLUE);
            canvas.drawTextOnPath(text, path, 0, 0, p);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);

            //зеленый.
            //переместим path на новое место.
            path.offset(-300, 250);
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.GREEN);
            canvas.drawTextOnPath(text, path, 100, 0, p);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);

            //красный.
            //переместим path на новое место.
            path.offset(300, 0);
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.RED);
            canvas.drawTextOnPath(text, path, 0, 30, p);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);

        }
    }
}
