package com.a5androidintern2.p1371_sensors;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    TextView tvText;
    SensorManager sensorManager;
    List<Sensor> sensors;
    Sensor sensorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = (TextView) findViewById(R.id.tvText);

        //получаем SensorManager.
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //запрашиваем у SensorManager полный список сенсоров,
        //используя метод getSensorList и передавая туда тип сенсора TYPE_ALL.
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        //Чтобы получить конкретный сенсор (Sensor),
        //вызываем метод getDefaultSensor.
        //Передаем тип TYPE_LIGHT и получаем сенсор света.
        //Тут аккуратнее, т.к. если такого сенсора в девайсе нет –
        //то метод вернет null.
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void onClickSensList(View v) {

        //отписываем слушателя от сенсора.
        sensorManager.unregisterListener(listenerLight, sensorLight);

        //берем список сенсоров и выводим по ним инфу на экран.
        StringBuilder sb = new StringBuilder();
        for (Sensor sensor : sensors) {
            sb.append("name = ")
                    //имя.
                    .append(sensor.getName())

                    //тип.
                    .append(", type = ").append(sensor.getType())

                    //vendor-(англ.)продавец. Здесь - создатель.
                    .append("\nvendor = ").append(sensor.getVendor())

                    //версия.
                    .append(" ,version = ").append(sensor.getVersion())

                    //максимальное значение, которое может вернуть сенсор.
                    .append("\nmax = ").append(sensor.getMaximumRange())

                    //минимальный шаг, с которым может изменяться значение.
                    .append(", resolution = ").append(sensor.getResolution())
                    .append("\n--------------------------------------\n");
        }
        tvText.setText(sb);
    }

    public void onClickSensLight(View v) {
        //используем метод registerListener,
        //чтобы на ранее полученный сенсор света (sensorLight)
        //повесить своего слушателя listenerLight.
        sensorManager.registerListener(
                listenerLight,
                sensorLight,
                SensorManager.SENSOR_DELAY_NORMAL
        );


        //Третий параметр метода – скорость получения новых данных.
        //Т.е. насколько часто вам необходимо получать данные от сенсора.
        //Есть 4 скорости в порядке убывания:
        //SENSOR_DELAY_NORMAL,
        //SENSOR_DELAY_UI,
        //SENSOR_DELAY_GAME,
        //SENSOR_DELAY_FASTEST.
    }

    @Override
    protected void onPause() {
        //мы отписываем своего слушателя от сенсора света.
        //Тут, как обычно, рекомендуется отписываться как только данные
        //вам не нужны, чтобы не расходовать зря батарею.
        super.onPause();
        sensorManager.unregisterListener(listenerLight, sensorLight);
    }

    SensorEventListener listenerLight = new SensorEventListener() {
        //слушатель, реализует интерфейс SensorEventListener.
        //У него переопределяем два метода:

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //вызывается, когда меняется точность данных сенсора
            //и в начале получения данных.
            //Дает нам объект-сенсор и уровень точности:

            //SENSOR_STATUS_ACCURACY_HIGH – максимально возможная точность
            //SENSOR_STATUS_ACCURACY_MEDIUM – средняя точность,
            //                                калибровка могла бы улучшить результат
            //SENSOR_STATUS_ACCURACY_LOW – низкая точность, необходима калибровка
            //SENSOR_STATUS_UNRELIABLE – данные сенсора совсем ни о чем.
            //                          Либо нужна калибровка,
            //                          либо невозможно чтение данных.
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            //здесь то мы и получаем данные от сенсора в объекте SensorEvent.
            tvText.setText(String.valueOf(event.values[0]));
        }
    };

}
