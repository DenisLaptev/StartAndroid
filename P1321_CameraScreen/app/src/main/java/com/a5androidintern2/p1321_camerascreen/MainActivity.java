package com.a5androidintern2.p1321_camerascreen;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SurfaceView sv;
    SurfaceHolder holder;
    HolderCallback holderCallback;
    Camera camera;

    final int CAMERA_ID = 0;//0-задняя камера; 1-передняя камера.
    final boolean FULL_SCREEN = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //настраиваем Activity так, чтобы оно было без заголовка и в полный экран.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        //определяем surface,
        //получаем его holder
        //и устанавливаем его тип = SURFACE_TYPE_PUSH_BUFFERS
        //(настройка типа нужна только в Android версии ниже 3.0).
        sv = (SurfaceView) findViewById(R.id.surfaceView);
        holder = sv.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        //для holder создаем callback объект HolderCallback,
        //через который holder будет сообщать нам о состояниях surface.
        holderCallback = new HolderCallback();
        holder.addCallback(holderCallback);
    }

    @Override
    protected void onResume() {
        //получаем доступ к камере, используя метод open.
        //На вход передаем id камеры, если их несколько (задняя и передняя).
        //Этот метод доступен с API level 9.

        //Также существует метод open без требования id на вход.
        //Он даст доступ к задней камере. Он доступен и в более ранних версиях.
        super.onResume();
        camera = Camera.open(CAMERA_ID);

        //настраиваем размер surface.
        setPreviewSize(FULL_SCREEN);
    }

    @Override
    protected void onPause() {
        //освобождаем камеру методом release,
        //чтобы другие приложения могли ее использовать.
        super.onPause();
        if (camera != null) {
            camera.release();
        }
        camera = null;
    }

    class HolderCallback implements SurfaceHolder.Callback {
        //через класс HolderCallback holder сообщает нам о состоянии surface.

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            //surface создан.

            //Мы можем дать камере объект holder
            //с помощью метода setPreviewDisplay
            //и начать транслировать изображение методом startPreview.
            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            //был изменен формат или размер surface.

            //В этом случае мы останавливаем просмотр (stopPreview),
            //настраиваем камеру с учетом поворота
            //устройства (setCameraDisplayOrientation)
            //и снова запускаем просмотр.
            camera.stopPreview();
            setCameraDisplayOrientation(CAMERA_ID);
            try {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            //surface более недоступен.

            //Не используем этот метод.
        }

    }

    void setPreviewSize(boolean fullScreen) {
//В методе определяем размеры surface с учетом экрана и изображения с камеры,
//чтобы картинка отображалась с верным соотношением сторон и на весь экран.

        //получаем размеры экрана.
        Display display = getWindowManager().getDefaultDisplay();
        boolean widthIsMax = display.getWidth() > display.getHeight();

        //определяем размеры превью камеры.
        Size size = camera.getParameters().getPreviewSize();

        RectF rectDisplay = new RectF();
        RectF rectPreview = new RectF();

        //RectF экрана, соотвествует размерам экрана.
        rectDisplay.set(0, 0, display.getWidth(), display.getHeight());

        //RectF первью.
        if (widthIsMax) {
            //превью в горизонтальной ориентации.
            rectPreview.set(0, 0, size.width, size.height);
        } else {
            //превью в вертикальной ориентации.
            rectPreview.set(0, 0, size.height, size.width);
        }

        Matrix matrix = new Matrix();
        //подготовка матрицы преобразования.
        if (!fullScreen) {
            //если превью будет "втиснут" в экран (второй вариант из урока).
            matrix.setRectToRect(rectPreview, rectDisplay,
                    Matrix.ScaleToFit.START);
        } else {
            //если экран будет "втиснут" в превью (третий вариант из урока).
            matrix.setRectToRect(rectDisplay, rectPreview,
                    Matrix.ScaleToFit.START);
            matrix.invert(matrix);
        }
        //преобразование.
        matrix.mapRect(rectPreview);

        //установка размеров surface из получившегося преобразования.
        sv.getLayoutParams().height = (int) (rectPreview.bottom);
        sv.getLayoutParams().width = (int) (rectPreview.right);
    }

    void setCameraDisplayOrientation(int cameraId) {
        //определяем насколько повернут экран от нормального положения.
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result = 0;

        //получаем инфо по камере cameraId.
        CameraInfo info = new CameraInfo();
        Camera.getCameraInfo(cameraId, info);

        //задняя камера.
        if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
            result = ((360 - degrees) + info.orientation);
        } else
            //передняя камера.
            if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
                result = ((360 - degrees) - info.orientation);
                result += 360;
            }
        result = result % 360;
        camera.setDisplayOrientation(result);
    }
}
