package com.a5androidintern2.p1381_location;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static android.location.LocationManager.GPS_PROVIDER;
import static android.location.LocationManager.NETWORK_PROVIDER;

public class MainActivity extends Activity {

    //TextView-компоненты.
    TextView tvEnabledGPS;
    TextView tvStatusGPS;
    TextView tvLocationGPS;
    TextView tvEnabledNet;
    TextView tvStatusNet;
    TextView tvLocationNet;

    private LocationManager locationManager;
    StringBuilder sbGPS = new StringBuilder();
    StringBuilder sbNet = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //находим TextView-компоненты.
        tvEnabledGPS = (TextView) findViewById(R.id.tvEnabledGPS);
        tvStatusGPS = (TextView) findViewById(R.id.tvStatusGPS);
        tvLocationGPS = (TextView) findViewById(R.id.tvLocationGPS);
        tvEnabledNet = (TextView) findViewById(R.id.tvEnabledNet);
        tvStatusNet = (TextView) findViewById(R.id.tvStatusNet);
        tvLocationNet = (TextView) findViewById(R.id.tvLocationNet);

        //получаем LocationManager, через который и будем работать.
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onResume() {
        //вешаем слушателя с помощью метода requestLocationUpdates.
        //На вход передаем:
        //- тип провайдера: GPS_PROVIDER или NETWORK_PROVIDER
        //- минимальное время (в миллисекундах) между получением данных.
        //              Я укажу здесь 10 секунд, мне этого вполне хватит.
        //              Если хотите получать координаты без задержек – передавайте 0.
        //              Но учитывайте, что это только минимальное время.
        //              Реальное ожидание может быть дольше.
        //- минимальное расстояние (в метрах).
        //              Т.е. если ваше местоположение изменилось на указанное кол-во метров,
        //              то вам придут новые координаты.
        //- слушатель, объект locationListener, который рассмотрим ниже
        super.onResume();
        locationManager.requestLocationUpdates(GPS_PROVIDER, 1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(NETWORK_PROVIDER, 1000 * 10, 10, locationListener);

        //обновляем на экране инфу о включенности провайдеров.
        checkEnabled();
    }

    @Override
    protected void onPause() {
        //отключаем слушателя методом removeUpdates.
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    //locationListener – слушатель, реализует интерфейс LocationListener.
    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            //метод showLocation на экране отобразит данные о местоположении.
            showLocation(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
            //указанный провайдер был отключен юзером.
            //В этом методе вызываем свой метод checkEnabled,
            //который на экране обновит текущие статусы провайдеров.
            checkEnabled();
        }

        @Override
        public void onProviderEnabled(String provider) {
            //указанный провайдер был включен юзером.
            checkEnabled();

            //методом getLastKnownLocation (он может вернуть null)
            //запрашиваем последнее доступное местоположение от включенного провайдера
            //и отображаем его. Оно может быть вполне актуальным,
            //если вы до этого использовали какое-либо приложение
            //с определением местоположения.
            showLocation(locationManager.getLastKnownLocation(provider));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //изменился статус указанного провайдера.
            //В этом методе мы просто выводим новый статус на экран.

            //В поле status могут быть значения
            //OUT_OF_SERVICE (данные будут недоступны долгое время),
            //TEMPORARILY_UNAVAILABLE (данные временно недоступны),
            //AVAILABLE (все ок, данные доступны).
            if (provider.equals(GPS_PROVIDER)) {
                tvStatusGPS.setText("Status: " + String.valueOf(status));
            } else if (provider.equals(NETWORK_PROVIDER)) {
                tvStatusNet.setText("Status: " + String.valueOf(status));
            }
        }
    };

    private void showLocation(Location location) {
        //метод на вход берет Location,
        //определяет его провайдера методом getProvider
        //и отображает координаты в соответствующем текстовом поле.
        if (location == null)
            return;
        if (location.getProvider().equals(GPS_PROVIDER)) {
            tvLocationGPS.setText(formatLocation(location));
        } else if (location.getProvider().equals(
                NETWORK_PROVIDER)) {
            tvLocationNet.setText(formatLocation(location));
        }
    }

    private String formatLocation(Location location) {
        //на вход берет Location,
        //читает из него данные и форматирует из них строку.
        //Какие данные он берет:
        //getLatitude – широта,
        //getLongitude – долгота,
        //getTime – время определения.
        if (location == null)
            return "";
        return String.format(
                "Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT",
                location.getLatitude(), location.getLongitude(), new Date(
                        location.getTime()));
    }

    private void checkEnabled() {
        //определяет включены или выключены провайдеры
        //методом isProviderEnabled и отображает эту инфу на экране.
        tvEnabledGPS.setText("Enabled: " + locationManager.isProviderEnabled(GPS_PROVIDER));
        tvEnabledNet.setText("Enabled: " + locationManager.isProviderEnabled(NETWORK_PROVIDER));
    }

    public void onClickLocationSettings(View view) {
        //Метод срабатывает по нажатию кнопки Location settings
        //и открывает настройки,
        //чтобы пользователь мог включить или выключить провайдер.
        //Для этого используется Intent с action = ACTION_LOCATION_SOURCE_SETTINGS.

        startActivity(new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    };
}
