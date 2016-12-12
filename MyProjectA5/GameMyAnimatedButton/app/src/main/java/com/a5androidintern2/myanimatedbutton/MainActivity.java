package com.a5androidintern2.myanimatedbutton;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    //Объявляем использование ImageView
    ImageView anim_image;
    //Объявляем использование анимации AnimationDrawable
    AnimationDrawable animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Связываем объект ImageView с нашим элементом ImageView
        //и задаем ему на фон созданную анимацию:
        anim_image = (ImageView) findViewById(R.id.imageView);
        anim_image.setBackgroundResource(R.drawable.anim_button);

        //Загружаем объект анимации:
        animation = (AnimationDrawable)anim_image.getBackground();

        //Выставляя значение false, добиваемся бесконечного
        //повторения анимации (true - только 1 повторение):
        animation.setOneShot(false);}


    //Метод обработки нажатия на ImageView:
    public void animButtonClick(View v) {
        Log.d("animButton", "Click");
        animation.stop();
        animation.start();

    }
}
