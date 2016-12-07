package com.a5androidintern2.p1421_drawingfigure;

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

        /*
        создавать объекты крайне желательно за пределами метода onDraw,
        т.к. при частой прорисовке у вас постоянно будут создаваться новые объекты,
        а это является лишней нагрузкой на сборщик мусора
        и может замедлить работу приложения.
        Поэтому создаем мы объекты всего один раз, в конструкторе.
        */


        public DrawView(Context context) {
            super(context);
            p = new Paint();
            rect = new Rect();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //сначала закрашиваем всю канву цветом.
            //drawARGB – аналогичен drawRGB,
            //но добавляет использование прозрачности (alpha): alpha + RGB = ARGB.
            canvas.drawARGB(80, 102, 204, 255);

            //Далее настраиваем нашу кисть, она же Paint.
            //красный цвет.
            p.setColor(Color.RED);
            //толщина линии = 10.
            p.setStrokeWidth(10);

            //рисуем точку (50,50).
            canvas.drawPoint(50, 50, p);

            //рисуем линию от (100,100) до (500,50).
            canvas.drawLine(100,100,500,50,p);

            //рисуем круг с центром в (100,200), радиус = 50.
            canvas.drawCircle(100, 200, 50, p);

            //рисуем прямоугольник.
            //левая верхняя точка (200,150), нижняя правая (400,200).
            canvas.drawRect(200, 150, 400, 200, p);


            //рисуем прямоугольник вторым способом.
            //настройка объекта Rect.
            //левая верхняя точка (250,300), нижняя правая (350,500).
            rect.set(250, 300, 350, 500);

            //рисуем прямоугольник третим способом.
            //рисуем прямоугольник из объекта rect.
            canvas.drawRect(rect, p);
        }

    }

}
