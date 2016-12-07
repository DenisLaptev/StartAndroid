package com.a5androidintern2.p1412_canvasviewsurfaceview2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //в метод setContentView передаем наш объект DrawView.
        setContentView(new DrawView(this));
    }

    class DrawView extends SurfaceView implements SurfaceHolder.Callback {
        //DrawView является наследником SurfaceView
        //и заодно реализует интерфейс обработчика SurfaceHolder.Callback.

        //SurfaceView только отображает контент.
        //работа с контентом ведется через обработчик SurfaceHolder.

        private DrawThread drawThread;

        public DrawView(Context context) {
            //получаем SurfaceHolder и сообщаем ему,
            //что сами будем обрабатывать его события.

            //Таких событий три:

            //surfaceChanged - был изменен формат или размер SurfaceView.
            //surfaceCreated – SurfaceView создан и готов к отображению информации.
            //surfaceDestroyed – вызывается перед тем, как SurfaceView будет уничтожен.


            super(context);
            getHolder().addCallback(this);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            //создаем свой поток прорисовки и передаем ему SurfaceHolder.
            drawThread = new DrawThread(getHolder());

            //Вызовом метода setRunning(true) ставим потоку метку о том,
            //что он может работать.
            drawThread.setRunning(true);

            //стартуем поток.
            drawThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            //сообщаем своему потоку о том, что его работа должна быть прекращена,
            //т.к. SurfaceView сейчас будет уничтожено.
            drawThread.setRunning(false);

            //запускаем цикл, который ждет, пока не завершит работу наш поток прорисовки.
            //Дождаться надо обязательно,
            //иначе поток может попытаться нарисовать что-либо на уничтоженном SurfaceView.
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }

        class DrawThread extends Thread {
            //это наш поток прорисовки. В нем и будет происходить рисование.

            private boolean running = false;
            private SurfaceHolder surfaceHolder;

            public DrawThread(SurfaceHolder surfaceHolder) {
                //В конструктор передаем SurfaceHolder.
                //Он нам нужен, чтобы добраться до канвы.
                this.surfaceHolder = surfaceHolder;
            }

            public void setRunning(boolean running) {
                //ставит метку работы, сообщающую потоку, можно ли работать.
                this.running = running;
            }

            @Override
            public void run() {
                Canvas canvas;
                //цикл выполняется пока позволяет метка работы (running).

                //когда в surfaceDestroyed вызывается метод setRunning(false),
                //происходит выход из цикла в методе run и поток завершает свою работу.
                while (running) {

                    //обнуляем переменную канвы.
                    canvas = null;
                    try {
                        //от SurfaceHolder получаем канву методом lockCanvas.
                        canvas = surfaceHolder.lockCanvas(null);

                        //На всякий случай проверяем, что канва не null, и можно рисовать.
                        if (canvas == null) {
                            continue;
                        }
                        //рисуем: просто закрашиваем все зеленым цветом.
                        canvas.drawColor(Color.GREEN);
                    } finally {
                        //После того, как нарисовали, что хотели,
                        //возвращаем канву объекту SurfaceHolder методом unlockCanvasAndPost
                        //и SurfaceView отобразит наши художества.
                        if (canvas != null) {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }

    }

}
