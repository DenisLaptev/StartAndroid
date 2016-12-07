package com.a5androidintern2.p1302_audiorecorderperiodicandmarker;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    final String TAG = "myLogs";

    int myBufferSize = 8192;
    AudioRecord audioRecord;
    boolean isReading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //вызываем свой метод создания AudioRecorder.
        createAudioRecorder();

        //выводим в лог состояние созданного объекта.
        //Состояние можно получить методом getState.
        //Может быть всего два состояния:
        //STATE_INITIALIZED - AudioRecorder к работе готов,
        //STATE_UNINITIALIZED - AudioRecorder к работе не готов.
        Log.d(TAG, "init state = " + audioRecord.getState());
    }



    void createAudioRecorder() {
        // создаем AudioRecorder.
        int sampleRate = 8000;//сэмплрейт (как часто считывается звук), например = 8000 Hz.
        int channelConfig = AudioFormat.CHANNEL_IN_MONO;//режим моно.

        //формат (сколько места занимает одна запись), например = 16 бит.
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;//аудиоформат - 16 бит.

        //метод getMinBufferSize исходя из переданных ему на вход данных
        //о формате аудио, возвращает минимально-возможный размер буфера,
        //с которым сможет работать AudioRecorder.
        int minInternalBufferSize = AudioRecord.getMinBufferSize(sampleRate,
                channelConfig, audioFormat);

        //устанавливаем размер буфера в созданном AudioRecord
        //равным minInternalBufferSize * 4.
        int internalBufferSize = minInternalBufferSize * 4;
        Log.d(TAG, "minInternalBufferSize = " + minInternalBufferSize
                + ", internalBufferSize = " + internalBufferSize
                + ", myBufferSize = " + myBufferSize);

        //создаем AudioRecord.
        //В качестве источника звука указываем микрофон.
        //Также указываем сэмплрейт, режим каналов, формат и размер буфера.
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                sampleRate, channelConfig, audioFormat, internalBufferSize);

        //Метод setPositionNotificationPeriod устанавливает
        //кол-во фреймов для срабатывания onPeriodicNotification.
        audioRecord.setPositionNotificationPeriod(1000);

        //Метод setNotificationMarkerPosition устанавливает
        //кол-во фреймов для срабатывания onMarkerReached.
        audioRecord.setNotificationMarkerPosition(10000);
        audioRecord
                .setRecordPositionUpdateListener(new AudioRecord.OnRecordPositionUpdateListener() {
                    //Метод setRecordPositionUpdateListener устанавливает слушателя
                    //с методами onPeriodicNotification и onMarkerReached.

                    //метод onPeriodicNotification будет срабатывать каждые 1000 отданных фреймов.
                    //метод onMarkerReached будет срабатывать по достижению 10000 отданных фреймов.
                    //В onMarkerReached мы остановим чтение.
                    public void onPeriodicNotification(AudioRecord recorder) {
                        Log.d(TAG, "onPeriodicNotification");
                    }

                    public void onMarkerReached(AudioRecord recorder) {
                        Log.d(TAG, "onMarkerReached");
                        isReading = false;
                    }
                });
    }


    public void recordStart(View v) {
        //стартуем запись методом startRecording.
        Log.d(TAG, "record start");
        audioRecord.startRecording();

        //С помощью метода getRecordingState получаем статус - идет запись или нет.
        //Вариантов тут два:
        //RECORDSTATE_RECORDING (запись идет)
        //и RECORDSTATE_STOPPED (запись остановлена).
        int recordingState = audioRecord.getRecordingState();
        Log.d(TAG, "recordingState = " + recordingState);
    }

    public void recordStop(View v) {
        //останавливаем запись методом stop.
        Log.d(TAG, "record stop");
        audioRecord.stop();
    }

    public void readStart(View v) {
        Log.d(TAG, "read start");

        //метка isReading=true означает, что мы сейчас находимся
        //в режиме чтения данных из AudioRecorder.
        isReading = true;

        //создаем новый поток и чтение выполняем в нем,
        //чтобы не занимать основной поток.
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (audioRecord == null) {
                    return;
                }

                //Мы создаем свой буфер размером myBufferSize
                //и читаем в него данные методом read.
                //Это происходит в цикле, который проверяет,
                //что мы в режиме чтения.
                //Метод read на вход принимает
                //массив (в который будут помещены данные),
                //отступ (если вам надо прочесть данные не сначала, а с какой-то позиции)
                //и размер порции получаемых данных.
                byte[] myBuffer = new byte[myBufferSize];
                int readCount = 0;
                int totalCount = 0;
                while (isReading) {
                    //Метод read на вход принимает
                    //массив (в который будут помещены данные),
                    //отступ (если вам надо прочесть данные не сначала, а с какой-то позиции)
                    //и размер порции получаемых данных.
                    readCount = audioRecord.read(myBuffer, 0, myBufferSize);

                    //В readCount метод read возвращает число байт,
                    //которые он нам отдал.
                    //В totalCount мы суммируем общее количество полученных байтов.
                    totalCount += readCount;
                    Log.d(TAG, "readCount = " + readCount + ", totalCount = "
                            + totalCount);
                }
            }
        }).start();
    }

    public void readStop(View v) {
        //выключаем режим чтения, присваивая переменной isReading значение false.
        //Поток из readStart прочтет это значение,
        //выйдет из цикла и завершит свою работу.
        Log.d(TAG, "read stop");
        isReading = false;
    }

    @Override
    protected void onDestroy() {
        //выключаем режим чтения
        //и методом release освобождаем ресурсы, занятые AudioRecord.
        super.onDestroy();

        isReading = false;
        if (audioRecord != null) {
            audioRecord.release();
        }
    }
}

