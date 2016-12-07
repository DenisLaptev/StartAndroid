package com.a5androidintern2.p1391_googlemaps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends FragmentActivity {

    SupportMapFragment mapFragment;
    GoogleMap map;
    final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //находим фрагмент с картой.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        //получаем от mapFragment (фрагмента с картой) объект GoogleMap методом getMap.
        //Учитывайте, что этот метод может вернуть null.
        map = mapFragment.getMap();
        if (map == null) {
            finish();
            return;
        }
        init();
    }

    private void init() {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            //setOnMapClickListener вешает слушателя OnMapClickListener.

            @Override
            public void onMapClick(LatLng latLng) {
                //метод onMapClick сработает при клике на карту
                //и вернет объект LatLng с координатами (latitude, longitude), где было нажатие.
                Log.d(TAG, "onMapClick: " + latLng.latitude + "," + latLng.longitude);
            }
        });

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            //setOnMapLongClickListener вешает слушателя OnMapLongClickListener.

            @Override
            public void onMapLongClick(LatLng latLng) {
                //метод onMapLongClick сработает при длительном нажатии на карту
                //и вернет объект LatLng с координатами (latitude, longitude), где было нажатие.
                Log.d(TAG, "onMapLongClick: " + latLng.latitude + "," + latLng.longitude);
            }
        });

        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            //setOnCameraChangeListener вешает слушателя OnCameraChangeListener.

            @Override
            public void onCameraChange(CameraPosition camera) {
                //метод onCameraChange сработает при смене позиции камеры,
                //т.е. при пролистывании карты, и вернет объект CameraPosition,
                //который содержит информацию о текущем положении камеры.
                Log.d(TAG, "onCameraChange: " + camera.target.latitude + "," + camera.target.longitude);

                /*
                //CameraPosition имеет атрибуты:
                - target, тип LatLng с полями-координатами: latitude, longitude.
                                            Это точка, на которую смотрит камера.
                - bearing, угол поворота камеры от севера по часовой стрелеке.
                - tilt, угол наклона камеры.
                - zoom, текущий уровень зума.
                */
            }
        });
    }

    /*
    //В onClickTest мы устанавливаем тип карты методом setMapType.

    //Всего существует 5 типов:

    MAP_TYPE_NONE – карта не будет отображаться.
    MAP_TYPE_NORMAL – обычный режим, в нем карта стартует по умолчанию.
    MAP_TYPE_SATELLITE – снимки со спутника.
    MAP_TYPE_TERRAIN – карта рельефа местности.
    MAP_TYPE_HYBRID – снимки со спутника + инфа о улицах и транспорте.

    Получить текущий тип можно методом getMapType.
    */
    public void onClickTest(View view) {
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
}