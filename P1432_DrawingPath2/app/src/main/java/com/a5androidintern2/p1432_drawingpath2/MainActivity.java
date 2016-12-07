package com.a5androidintern2.p1432_drawingpath2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
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
        Point point1;
        Point point21;
        Point point22;

        public DrawView(Context context) {
            super(context);
            //параметр Paint.ANTI_ALIAS_FLAG сглаживает кривые при рисовании.
            p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setStrokeWidth(3);
            path = new Path();

            point1 = new Point(200,300);
            point21 = new Point(500,600);
            point22 = new Point(900,200);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //фон.
            canvas.drawARGB(80, 102, 204, 255);


            //первая линия.
            //такой была бы прямая линия, если бы мы не сделали из неё кривую.
            p.setColor(Color.BLACK);
            canvas.drawLine(100, 100, 600, 100, p);

            //точка отклонения для первой линии.
            //для наглядности изобразим её в виде маленького кружка.
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.GREEN);
            canvas.drawCircle(point1.x, point1.y, 10, p);

            //квадратичная кривая.
            //обновляем path.
            path.reset();
            //становимся в точку (100, 100).
            path.moveTo(100, 100);
            //рисуем кривую, она начинается из текущей точки (100, 100),
            //идёт в точку с координатами (600, 100).
            //при этом она искривляется в направлении точки (point1.x, point1.y).
            //точка кабкбы притягивает на себя кривую.
            path.quadTo(point1.x, point1.y, 600, 100);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);


            //вторая линия.
            //такой была бы прямая линия, если бы мы не сделали из неё кривую.
            p.setColor(Color.BLACK);
            canvas.drawLine(400, 400, 1100, 400, p);

            //точки отклонения для второй линии.
            //для наглядности изобразим эти точки в виде маленьких кружков.
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.BLUE);
            //третий параметр - радиус.
            canvas.drawCircle(point21.x, point21.y, 10, p);
            canvas.drawCircle(point22.x, point22.y, 10, p);

            //кубическая кривая.
            //обновляем path.
            path.reset();
            //становимся в точку (400, 400).
            path.moveTo(400, 400);
            //рисуем кривую, она начинается из текущей точки (400, 400),
            //идёт в точку с координатами (1100, 400).
            //при этом она искривляется
            //в направлении точек (point21.x, point21.y) и (point22.x, point22.y).
            //точки кабкбы притягивают на себя кривую.
            path.cubicTo(point21.x, point21.y, point22.x, point22.y, 1100, 400);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);
        }
    }

}
