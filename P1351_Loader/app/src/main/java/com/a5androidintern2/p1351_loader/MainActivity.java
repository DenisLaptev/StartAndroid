package com.a5androidintern2.p1351_loader;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.ContentObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements LoaderCallbacks<String> {

    final String LOG_TAG = "myLogs";
    static final int LOADER_TIME_ID = 1;

    TextView tvTime;
    RadioGroup rgTimeFormat;
    static int lastCheckedId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = (TextView) findViewById(R.id.tvTime);
        rgTimeFormat = (RadioGroup) findViewById(R.id.rgTimeFormat);

        Bundle bndl = new Bundle();
        //В Bundle помещаем формат времени, который хотим получить.
        //Для определения формата используем свой метод getTimeFormat() .
        bndl.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());

        //получаем объект LoaderManager с помощью метода getLoaderManager
        //и вызываем его метод initLoader, который создаст и вернет нам Loader.

        //В качестве параметров метода initLoader указываем:
        //- ID лоадера, это необходимо, т.к. мы запросто можем использовать
        //    сразу несколько разных лоадеров, кроме того LoaderManager, да и мы сами,
        //    должны их как-то различать.
        //- объект Bunlde. В него вы помещаете данные,
        //    которые хотите использовать при создании лоадера.
        //- объект, реализующий колбэк-интерфейс LoaderCallbacks.
        //    Он будет использоваться для взаимодействия с лоадером.
        getLoaderManager().initLoader(LOADER_TIME_ID, bndl, this);
        lastCheckedId = rgTimeFormat.getCheckedRadioButtonId();
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        //вызывается, когда требуется создать новый лоадер,
        //например, в тот момент, когда мы выше вызываем метод initLoader.
        //На вход получает ID требуемого лоадера и Bundle с данными.
        //Т.е. те самые объекты, что мы передавали в initLoader.
        Loader<String> loader = null;
        if (id == LOADER_TIME_ID) {
            loader = new TimeAsyncLoader(this, args);
            Log.d(LOG_TAG, "onCreateLoader: " + loader.hashCode());
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String result) {
        //срабатывает, когда лоадер закончил работу и вернул результат.
        //На вход приходит сам лоадер и результат его работы.
        Log.d(LOG_TAG, "onLoadFinished for loader " + loader.hashCode()
                + ", result = " + result);
        tvTime.setText(result);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        //срабатывает, когда LoaderManager собрался уничтожать лоадер.
        //На вход получает лоадер.
        Log.d(LOG_TAG, "onLoaderReset for loader " + loader.hashCode());
    }

    public void getTimeClick(View v) {
        //обработчик кнопки Get time.
        //В нем мы определяем: в каком формате требуется получить время.
        Loader<String> loader;

        int id = rgTimeFormat.getCheckedRadioButtonId();
        if (id == lastCheckedId) {
            loader = getLoaderManager().getLoader(LOADER_TIME_ID);
        } else {
            Bundle bndl = new Bundle();
            bndl.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());
            loader = getLoaderManager().restartLoader(LOADER_TIME_ID, bndl,
                    this);
            lastCheckedId = id;
        }
        loader.forceLoad();
    }

    String getTimeFormat() {
        //Методе getTimeFormat просто возвращает формат времени
        //в зависимости от выбранного на экране формата.
        String result = TimeLoader.TIME_FORMAT_SHORT;
        switch (rgTimeFormat.getCheckedRadioButtonId()) {
            case R.id.rdShort:
                result = TimeLoader.TIME_FORMAT_SHORT;
                break;
            case R.id.rdLong:
                result = TimeLoader.TIME_FORMAT_LONG;
                break;
        }
        return result;
    }

    public void observerClick(View v) {
        //обработчик нажатия кнопки Observer.

        //Создаем экземпляр ForceLoadContentObserver и эмулируем ситуацию:
        //он через 5 сек сообщит нам о том, что данные изменились.
        Log.d(LOG_TAG, "observerClick");
        Loader<String> loader = getLoaderManager().getLoader(LOADER_TIME_ID);
        final ContentObserver observer = loader.new ForceLoadContentObserver();
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                observer.dispatchChange(false);
            }
        }, 5000);
    }

}
