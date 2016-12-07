package com.a5androidintern2.p1401_googlemapsdraw;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {

    SupportMapFragment mapFragment;
    GoogleMap map;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //находим наш фрагмент с картой
        //и получаем от него объект GoogleMap методом getMap.
        //Учитывайте, что этот метод может вернуть null.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map = mapFragment.getMap();
        if (map == null) {
            finish();
            return;
        }

        init();

    }

    private void init() {

    }

    /*

    public void onClickTest(View view) {
        //поставим программно маркер на карту.
        //Для этого используем метод addMarker.

        //на вход методу addMarker передаем объект MarkerOptions.
        //Указываем координаты маркера (position) и текст (title),
        //который будет отображен по нажатию на маркер.
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Hello world"));
    }

    */

/*
    В точку (-10, -10) мы ставим маркер по умолчанию,
    который можно получить методом defaultMarker,
    но при этом мы меняем его цвет на зеленый.

    В точку (0, 0) мы ставим маркер по умолчанию,
    который можно получить методом defaultMarker. Цвет не меняем.

    В точку (10, 10) поставим маркер в виде стандартной Android иконки,
    используя метод fromResource и указав требуемый drawable-ресурс.
*/

    public void onClickTest(View view) {
        map.addMarker(new MarkerOptions().position(new LatLng(-10, -10)).icon(
                BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).icon(
                BitmapDescriptorFactory.defaultMarker()));

        map.addMarker(new MarkerOptions().position(new LatLng(10, 10)).icon(
                BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
    }
}
