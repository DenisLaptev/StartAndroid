package com.a5androidintern2.p1372_acceleration;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_GRAVITY;
import static android.hardware.Sensor.TYPE_LINEAR_ACCELERATION;

public class MainActivity extends Activity {

    TextView tvText;
    SensorManager sensorManager;
    Sensor sensorAccel;
    Sensor sensorLinAccel;
    Sensor sensorGravity;

    StringBuilder sb = new StringBuilder();

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = (TextView) findViewById(R.id.tvText);

        //получаем три сенсора:

        //TYPE_ACCELEROMETER – ускорение, включая гравитацию (g=9.81, из физики).
        //TYPE_LINEAR_ACCELERATION – ускорение (чистое, без гравитации).
        //TYPE_GRAVITY - гравитация.

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorAccel = sensorManager.getDefaultSensor(TYPE_ACCELEROMETER);
        sensorLinAccel = sensorManager.getDefaultSensor(TYPE_LINEAR_ACCELERATION);
        sensorGravity = sensorManager.getDefaultSensor(TYPE_GRAVITY);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //регистрируем один слушатель listener на все три сенсора.
        sensorManager.registerListener(listener, sensorAccel,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listener, sensorLinAccel,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listener, sensorGravity,
                SensorManager.SENSOR_DELAY_NORMAL);

        //запускаем таймер, который будет каждые 400 мсек отображать данные в TextView.
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showInfo();
                    }
                });
            }
        };
        timer.schedule(task, 0, 400);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //отписываем слушателя от всех сенсоров,
        //вызывая метод unregisterListener, но не указывая конкретный сенсор.
        sensorManager.unregisterListener(listener);

        //отключаем таймер.
        timer.cancel();
    }

    //Метод format просто отформатирует float значения до одного знака после запятой.
    String format(float values[]) {
        return String.format("%1$.1f\t\t%2$.1f\t\t%3$.1f", values[0], values[1],
                values[2]);
    }

    //showInfo выведет в TextView данные. Данные у нас будут в пяти массивах.
    void showInfo() {
        sb.setLength(0);
        sb.append("Accelerometer: " + format(valuesAccel))
                .append("\n\nAccel motion: " + format(valuesAccelMotion))
                .append("\nAccel gravity : " + format(valuesAccelGravity))
                .append("\n\nLin accel : " + format(valuesLinAccel))
                .append("\nGravity : " + format(valuesGravity));
        tvText.setText(sb);
    }

    //данные с сенсора ускорения (включая гравитацию).
    float[] valuesAccel = new float[3];

    //valuesAccelMotion и valuesAccelGravity – данные из valuesAccel,
    //разделенные с помощью вычислительного фильтра
    //на чистое ускорение (без гравитации) и гравитацию.
    float[] valuesAccelMotion = new float[3];
    float[] valuesAccelGravity = new float[3];

    //данные с сенсора ускорения без гравитации.
    float[] valuesLinAccel = new float[3];

    //данные с сенсора гравитации.
    float[] valuesGravity = new float[3];

    SensorEventListener listener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            //определяем тип сенсора и пишем данные в соответствующие массивы.
            switch (event.sensor.getType()) {
                case TYPE_ACCELEROMETER:
                    for (int i = 0; i < 3; i++) {
                        valuesAccel[i] = event.values[i];
                        valuesAccelGravity[i] = (float) (0.1 * event.values[i] + 0.9 * valuesAccelGravity[i]);
                        valuesAccelMotion[i] = event.values[i]
                                - valuesAccelGravity[i];

                        //Т.е. мы получаем данные по ускорению (valuesAccel)
                        //с сенсора TYPE_ACCELEROMETER
                        //и потом вычислительным фильтром сами разбиваем
                        //на чистое ускорение и гравитацию.
                        //Но можно так не заморачиваться,
                        //а использовать сенсоры TYPE_LINEAR_ACCELERATION и TYPE_GRAVITY,
                        //которые должны дать нам примерно тот же результат.
                    }
                    break;
                case TYPE_LINEAR_ACCELERATION:
                    for (int i = 0; i < 3; i++) {
                        valuesLinAccel[i] = event.values[i];
                    }
                    break;
                case TYPE_GRAVITY:
                    for (int i = 0; i < 3; i++) {
                        valuesGravity[i] = event.values[i];
                    }
                    break;
            }

        }

    };

}
