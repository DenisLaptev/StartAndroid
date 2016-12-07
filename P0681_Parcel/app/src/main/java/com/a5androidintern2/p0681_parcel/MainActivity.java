package com.a5androidintern2.p0681_parcel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";
    Parcel p;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeParcel();
        readParcel();
    }

    void writeParcel() {
        //при чтении данных надо соблюдать тот же порядок, что и при записи.
        //иначе, будет залазить на следующие байты, и получится каша.


        //получаем экземпляр Parcel.
        p = Parcel.obtain();


        //объявляем и инициализируем переменные.
        byte b = 1;
        int i = 2;
        long l = 3;
        float f = 4;
        double d = 5;
        String s = "abcdefgh";

        //записываем переменные в Parcel и выводим в лог наши действия.
        logWriteInfo("before writing");
        p.writeByte(b);
        p.writeByte(b);
        p.writeByte(b);
        logWriteInfo("byte");
        p.writeInt(i);
        logWriteInfo("int");
        p.writeLong(l);
        logWriteInfo("long");
        p.writeFloat(f);
        logWriteInfo("float");
        p.writeDouble(d);
        logWriteInfo("double");
        p.writeString(s);
        logWriteInfo("String");
    }

    void logWriteInfo(String txt) {
        //dataSize - это объём записанных данных.
        Log.d(LOG_TAG, txt + ": " + "dataSize = " + p.dataSize());
    }

    void readParcel() {
        logReadInfo("before reading");
        //устанавливаем позицию для чтения в ноль.
        //т.к. нужно читать сначала.

        //читаем в том же порядке, что записывали.
        p.setDataPosition(0);
        logReadInfo("byte = " + p.readByte());
        logReadInfo("int = " + p.readInt());
        logReadInfo("long = " + p.readLong());
        logReadInfo("float = " + p.readFloat());
        logReadInfo("double = " + p.readDouble());
        logReadInfo("string = " + p.readString());
    }

    void logReadInfo(String txt) {
        Log.d(LOG_TAG, txt + ": " + "dataPosition = " + p.dataPosition());
    }

}
