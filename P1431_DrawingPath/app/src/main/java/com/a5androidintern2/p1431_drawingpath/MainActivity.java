package com.a5androidintern2.p1431_drawingpath;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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

        //объект RectF нам понадобится для рисования прямоугольника.
        //(координаты типа float).
        RectF rectf;
        Path path;
        Path path1;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            rectf = new RectF(350,100,400,150);
            path = new Path();
            path1 = new Path();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            //очистка path.
            path.reset();

            //угол.
            //ставим курсор в указанную точку. Далее рисование пойдёт от неё.
            path.moveTo(100, 100);
            //метод рисует линию от текущей точки до указанной.
            path.lineTo(150, 200);
            path.lineTo(50, 200);

            //треугольник.
            //ставим курсор в указанную точку. Далее рисование пойдёт от неё.
            //мы какбы сообщили, что начали рисовать новую фигуру.
            path.moveTo(250, 100);
            path.lineTo(300, 200);
            path.lineTo(200, 200);
            //рисуется линия от последней точки до начальной.
            //т.е. фигура замыкается.
            path.close();

            //квадрат и круг.
            //Path.Direction - направление рисования фигуры:
            //Path.Direction.CW - lock wise (по часовой стрелке).
            //Path.Direction.CCW - counter lock wise (против часовой стрелки).
            path.addRect(rectf, Path.Direction.CW);
            path.addCircle(450, 150, 25, Path.Direction.CW);

            //рисование path.
            //выводим на экран получившийся path чёрным цветом.
            p.setColor(Color.BLACK);
            canvas.drawPath(path, p);


            //очистка path1.
            path1.reset();

            //две пересекающиеся линии.
            path1.moveTo(50,50);
            path1.lineTo(500,250);
            path1.moveTo(500,50);
            path1.lineTo(50,250);

            //рисование path1.
            //выводим на экран получившийся path1 зелёным цветом.
            //path1 получился поверх path.
            p.setColor(Color.GREEN);
            canvas.drawPath(path1, p);


            //добавление path1 к path.
            //к path можно добавлять не только фигуры и линии, но и path-объекты.
            path.addPath(path1);

            //параллельный перенос path-объекта
            //на 500 по горизонтали (вправо) и на 100 по вертикали (вниз).
            path.offset(500,100);

            //рисование path.
            //выводим на экран получившийся path синим цветом.
            p.setColor(Color.BLUE);
            canvas.drawPath(path, p);
        }

    }

}
