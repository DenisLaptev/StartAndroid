package com.a5androidintern2.p1411_canvasview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //в метод setContentView передаем не layout-файл, как обычно,
        //а свой view-компонент DrawView.
        //Он будет занимать все содержимое Activity.
        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        public DrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //метод дает нам доступ к объекту Canvas.
            //Пока что не будем рисовать ничего особенного,
            //а просто закрасим все зеленым цветом с помощью метода drawColor.
            canvas.drawColor(Color.GREEN);
        }

    }

}