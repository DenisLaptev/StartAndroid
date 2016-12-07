package com.a5androidintern2.p1331_camerarecord;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends Activity {

    SurfaceView surfaceView;
    Camera camera;
    MediaRecorder mediaRecorder;

    File photoFile;
    File videoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //создаем пути для фото и видео файлов, в которые будет сохраняться результат.
        File pictures = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        photoFile = new File(pictures, "myphoto.jpg");
        videoFile = new File(pictures, "myvideo.3gp");

        //Далее определяем SurfaceView, Holder и callback к нему.
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });

    }

    @Override
    protected void onResume() {
        //получаем доступ к камере.

        super.onResume();
        camera = Camera.open();
    }

    @Override
    protected void onPause() {
        //освобождаем ресурсы MediaRecorder и камеры.
        super.onPause();
        releaseMediaRecorder();
        if (camera != null)
            camera.release();
        camera = null;
    }

    public void onClickPicture(View view) {
        //это обработчик нажатия на кнопку Picture.
        //Здесь будем делать снимок.
        //
        //Для этого нам необходимо вызвать метод takePicture.
        //Этот метод асинхронный, для получения результата используются callback-и.
        //Их три.

        //Первый, ShutterCallback, сработает сразу после того,
        //        как камера сделает снимок.
        //        Сюда можно повесить звук затвора, например.
        //        Я его не использую, передаю null.

        //Второй, PictureCallback, вернет нам сырую raw картинку.
        //        Я его также не использую, передаю null.

        //Третий, PictureCallback, вернет нам готовую сжатую jpeg картинку.
        //        Это нам и надо.

        //Создаем callback и в его методе onPictureTaken будем получать byte-массив.
        //Это и есть готовое фото, которое мы пишем в файл.
        camera.takePicture(null, null, new PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                try {
                    FileOutputStream fos = new FileOutputStream(photoFile);
                    fos.write(data);
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void onClickStartRecord(View view) {
        //обработчик нажатия на кнопку Start.
        //Здесь будем включать запись видео с камеры.

        //Для этого подготавливаем MediaRecorder в методе prepareVideoRecorder.
        //Метод вернет нам значение Boolean,
        //по которому мы поймем удалось ли подготовить объект.
        //Если он готов, стартуем запись методом start.
        //Если при подготовке возникли проблемы, то освобождаем ресурсы.
        if (prepareVideoRecorder()) {
            mediaRecorder.start();
        } else {
            releaseMediaRecorder();
        }
    }

    public void onClickStopRecord(View view) {
        //обработчик нажатия на кнопку Stop.
        //Здесь останавливаем запись видео методом stop и освобождаем ресурсы.

        if (mediaRecorder != null) {
            mediaRecorder.stop();
            releaseMediaRecorder();
        }
    }

    private boolean prepareVideoRecorder() {
        //метод подготовки MediaRecorder к записи.

        //снимаем монопольный доступ с камеры,
        //чтобы MediaRecorder мог ее использовать.
        camera.unlock();

        //создаем объект MediaRecorder.
        mediaRecorder = new MediaRecorder();

        //Далее идут 6 методов его настройки.

        //1)предоставляем камеру, которая будет использована для записи.
        mediaRecorder.setCamera(camera);

        //2)указываем источник звука – CAMCORDER.
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);

        //3)указываем источник видео – CAMERA.
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        //4)указываем профиль записи.
        //В профиле содержаться такие данные как:
        //тип контейнера, аудио/видео кодек, битрейт и пр.
        //Чтобы нам руками не задавать все эти характеристики, используем профили.
        //Список профилей можно посмотреть в хелпе к объекту CamcorderProfile.
        //Мы используем высокое качество - QUALITY_HIGH.
        mediaRecorder.setProfile(CamcorderProfile
                .get(CamcorderProfile.QUALITY_HIGH));

        //5)указываем путь к файлу, куда будет записано видео.
        mediaRecorder.setOutputFile(videoFile.getAbsolutePath());

        //6)указываем surface для показа превью в процессе записи.
        //Если ранее вы для камеры уже указали surface в ее методе setPreviewDisplay,
        //то этот метод вызывать необязательно.
        mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());


        //Хелп очень рекомендует вызывать эти 6 методов именно в таком порядке,
        //иначе грозит ошибками и неудачей при записи.
        try {
            //Когда все параметры указали, вызываем prepare
            //и если все хорошо, то получаем MediaRecorder готовый к записи.
            mediaRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    private void releaseMediaRecorder() {
        //В методе освобождаем ресурсы MediaRecorder
        //и снова монополизируем камеру методом lock.
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            camera.lock();
        }
    }

}
