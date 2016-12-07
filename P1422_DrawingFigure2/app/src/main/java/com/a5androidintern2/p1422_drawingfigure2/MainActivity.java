package com.a5androidintern2.p1422_drawingfigure2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
        float[] points;
        float[] points1;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            rectf = new RectF(700,100,800,150);
            points = new float[]{100,50,150,100,150,200,50,200,50,100};
            points1 = new float[]{300,200,600,200,300,300,600,300,400,100,400,400,500,100,500,400};
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //фон.
            canvas.drawARGB(80, 102, 204, 255);

            p.setColor(Color.RED);//цвет кисти.
            p.setStrokeWidth(10);//толщина кисти.

            //рисуем точки их массива points. {x1,y1,x2,y2,...}.
            canvas.drawPoints(points,p);

            //рисуем линии по точкам из массива points1. {x1,y1,x2,y2,...}.
            canvas.drawLines(points1,p);

            //перенастраиваем кисть на зеленый цвет.
            p.setColor(Color.GREEN);

            //рисуем закругленный прямоугольник по координатам из rectf.
            //радиусы закругления по оси х и по оси у = 20.
            canvas.drawRoundRect(rectf, 20, 20, p);

            //смещаем коорднаты rectf на 150 вниз.
            //параллельный перенос по х на 0, по у на 150.
            rectf.offset(0, 150);

            //рисуем овал вписанный в прямоугольник rectf.
            canvas.drawOval(rectf, p);

            //смещаем rectf в (900,100) (левая верхняя точка).
            rectf.offsetTo(900, 100);

            //Далее меняем размер прямоугольника rectf методом inset.
            //На вход метод принимает две дельты,
            //на которые он уменьшит прямоугольник по горизонтали (0) и вертикали (-25).
            //Уменьшит на -25 означает, увеличение на 25.

            //Выполнив rectf.inset(0, -25) я увеличиваю размер прямоугольника rectf
            //по вертикали на 25 и вверх и вниз.
            //(удаляем верхнюю и нижнюю сторону от центра на 25).
            //Итого, размер по вертикали увеличивается на 50.
            //Горизонтальный не меняется.
            rectf.inset(0, -25);

            //рисуем круг с вырезом внутри прямоугольника rectf.
            //углы отсчитываем от 3 часов по часовой стрелке.
            //с началом в 90(6ч), и длиной 270(длина 9ч).
            //true-соединение крайних точек через центр.
            //false-соединение крайних точек по прямой.
            canvas.drawArc(rectf, 90, 270, true, p);

            //смещаем коорднаты rectf на 150 вниз.
            rectf.offset(0, 150);

            //рисуем круг с вырезом внутри прямоугольника rectf.
            //углы отсчитываем от 3 часов по часовой стрелке.
            //с началом в 90(6ч), и длиной 270(длина 9ч).
            //true-соединение крайних точек через центр.
            //false-соединение крайних точек по прямой.
            canvas.drawArc(rectf, 90, 270, false, p);

            //перенастраиваем кисть на толщину линии = 3.
            p.setStrokeWidth(3);

            //рисуем линию (150,450) - (150,600).
            canvas.drawLine(150, 450, 150, 600, p);

            //перенастраиваем кисть на синий цвет.
            p.setColor(Color.BLUE);

            //настраиваем размер текста = 30.
            p.setTextSize(30);

            //рисуем текст в точке (150,500).Точка будет слева от текста.
            canvas.drawText("text left", 150, 500, p);

            //настраиваем выравнивание текста на центр.
            p.setTextAlign(Paint.Align.CENTER);

            //рисуем текст в точке (150,525).
            canvas.drawText("text center", 150, 525, p);

            //настраиваем выравнивание текста на левое.
            p.setTextAlign(Paint.Align.RIGHT);

            //рисуем текст в точке (150,550).
            canvas.drawText("text right", 150, 550, p);
        }
    }

}
