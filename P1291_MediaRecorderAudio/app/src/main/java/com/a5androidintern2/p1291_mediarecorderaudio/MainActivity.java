package com.a5androidintern2.p1291_mediarecorderaudio;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;

public class MainActivity extends Activity {

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //задаем имя файла, куда будет записываться звук.
        fileName = Environment.getExternalStorageDirectory() + "/record.3gpp";
    }

    public void recordStart(View v) {
        try {
            //избавляемся от старого рекордера.
            releaseRecorder();

            File outFile = new File(fileName);

            //удаляем файл для записи, если он уже существует.
            if (outFile.exists()) {
                outFile.delete();
            }


            //создаем и настраиваем рекордер используя ряд методов.
            mediaRecorder = new MediaRecorder();

            //Указываем источник звука – микрофон (MIC).
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

            //Указываем формат – 3GPP (THREE_GPP).
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

            //Указываем кодек для сжатия аудио - AMR_NB.
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            //Указываем имя файла, в который будет вести запись.
            mediaRecorder.setOutputFile(fileName);

            // метод prepare подготовит рекордер к записи.
            mediaRecorder.prepare();

            //стартуем запись методом start.
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void recordStop(View v) {

        //После этого метода необходимо заново настроить рекордер,
        //если вы снова хотите его использовать.
        //Просто снова вызвать start не получится.
        //Кстати, метод reset также сбрасывает все настройки рекордера
        //и после него необходимо заново указывать
        //источник, формат, кодек, файл. Но объект новый создавать необязательно.
        if (mediaRecorder != null) {
            mediaRecorder.stop();
        }
    }

    public void playStart(View v) {
        //стартуем воспроизведение записанного файла.
        try {
            releasePlayer();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playStop(View v) {
        //останавливаем воспроизведение записанного файла.
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    private void releaseRecorder() {
        //освобождаем все ресурсы рекордера методом release.
        //После этого объект уже нельзя использовать
        //и необходимо создавать и настраивать новый.
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    private void releasePlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
        releaseRecorder();
    }

}
