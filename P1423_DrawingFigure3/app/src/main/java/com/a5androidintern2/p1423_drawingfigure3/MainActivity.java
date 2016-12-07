package com.a5androidintern2.p1423_drawingfigure3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

        //объект Rect нам понадобится для рисования прямоугольника.
        Rect rect;
        StringBuilder sb;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            rect = new Rect(100,200,200,300);
            sb = new StringBuilder();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            //фон.
            canvas.drawARGB(80, 102, 204, 255);

            p.setColor(Color.BLUE);
            p.setStrokeWidth(10);

            p.setTextSize(30);

            //создаем строку с значениями ширины и высоты канвы.
            sb.setLength(0);
            sb.append("width = ").append(canvas.getWidth())
                    .append(", height = ").append(canvas.getHeight());

            //выводим на экран ширину и высоту канвы.
            canvas.drawText(sb.toString(), 100, 100, p);

            //перенастраивам кисть на заливку.
            //прямоугольник закрашивается изнутри, граница при этом не рисуется.
            //это по умолчанию.
            p.setStyle(Paint.Style.FILL);
            canvas.drawRect(rect, p);

            //перенастраивам кисть на контуры.
            //прямоугольник не закрашивается изнутри, граница при этом рисуется.
            p.setStyle(Paint.Style.STROKE);
            rect.offset(150, 0);
            canvas.drawRect(rect, p);

            //перенастраивам кисть на заливку + контуры.
            //прямоугольник закрашивается изнутри, граница при этом рисуется.
            p.setStyle(Paint.Style.FILL_AND_STROKE);
            rect.offset(150, 0);
            canvas.drawRect(rect, p);
        }

    }
}