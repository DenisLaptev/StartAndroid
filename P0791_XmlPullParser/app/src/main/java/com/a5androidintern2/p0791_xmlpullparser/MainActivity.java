package com.a5androidintern2.p0791_xmlpullparser;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String tmp = "";

        try {
            //получам объект XmlPullParser xpp с помощью метода prepareXpp().
            XmlPullParser xpp = prepareXpp();

            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                //запускаем прогон документа, пока не достигнем конца документа.

                switch (xpp.getEventType()) {
                    //проверяем, на каком элементе остановился парсер.

                    //начало документа.
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(LOG_TAG, "START_DOCUMENT");
                        break;

                    //начало тэга.
                    case XmlPullParser.START_TAG:
                        Log.d(LOG_TAG, "START_TAG: name = " + xpp.getName()
                                //уровень в дереве тегов(глубина)
                                + ", depth = " + xpp.getDepth()
                                //количество атрибутов тега.
                                + ", attrCount = " + xpp.getAttributeCount()
                        );
                        tmp = "";
                        for (int i = 0; i < xpp.getAttributeCount(); i++) {
                            //выводим имена и количество атрибутов.
                            tmp = tmp + xpp.getAttributeName(i) + " = "
                                    + xpp.getAttributeValue(i) + ", ";
                        }
                        if (!TextUtils.isEmpty(tmp))
                            Log.d(LOG_TAG, "Attributes: " + tmp);
                        break;

                    //конец тэга.
                    case XmlPullParser.END_TAG:
                        Log.d(LOG_TAG, "END_TAG: name = " + xpp.getName());
                        break;

                    // содержимое тэга
                    case XmlPullParser.TEXT:
                        Log.d(LOG_TAG, "text = " + xpp.getText());
                        break;

                    default:
                        break;
                }
                // следующий элемент
                xpp.next();
            }
            Log.d(LOG_TAG, "END_DOCUMENT");

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //в методе подготавливаем XmlPullParser.

/*
    //если код xml получен из файла.

 XmlPullParser prepareXpp() {
        return getResources().getXml(R.xml.data);
    }

*/


    //если код xml получен не из файла, а получен откуда-либо,
    //то XmlPullParser надо создавать другим способом.
    //Перепишем метод prepareXpp:

    XmlPullParser prepareXpp() throws XmlPullParserException {
        //в методе сами создаём парсер с помощью фабрики.
        //получаем фабрику.
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        //включаем поддержку namespace (по умолчанию выключена).
        factory.setNamespaceAware(true);
        //создаем парсер.
        XmlPullParser xpp = factory.newPullParser();
        //даем парсеру на вход Reader.
        xpp.setInput(new StringReader(
                "<data><phone><company>Samsung</company></phone></data>"));
        return xpp;
    }

}
