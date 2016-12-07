package com.a5androidintern2.p1011_contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class MyContactsProvider extends ContentProvider {
    final String LOG_TAG = "myLogs";

    //Константы для БД.
    //БД.
    static final String DB_NAME = "mydb";
    static final int DB_VERSION = 1;

    //Таблица.
    //В БД будет всего 1 таблица с 3 полями.
    static final String CONTACT_TABLE = "contacts";

    //Поля.
    static final String CONTACT_ID = "_id";
    static final String CONTACT_NAME = "name";
    static final String CONTACT_EMAIL = "email";

    //Скрипт создания таблицы.
    static final String DB_CREATE = "create table " + CONTACT_TABLE + "("
            + CONTACT_ID + " integer primary key autoincrement, "
            + CONTACT_NAME + " text, "
            + CONTACT_EMAIL + " text" + ");";

    //Uri.
    //authority
    static final String AUTHORITY = "com.a5androidintern2.providers.AdressBook";

    //path.
    static final String CONTACT_PATH = "contacts";

    //Общий Uri
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + CONTACT_PATH);

    //Типы данных.
    //набор строк.
    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + CONTACT_PATH;

    //одна строка
    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + CONTACT_PATH;

    //UriMatcher (это что-то типа парсера).
    //общий Uri.
    static final int URI_CONTACTS = 1;

    //Uri с указанным ID.
    static final int URI_CONTACTS_ID = 2;

    //описание и создание UriMatcher.
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH, URI_CONTACTS);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH + "/#", URI_CONTACTS_ID);

        //главный смысл этого uriMatcher в том, что он определит,
        //какой Uri к нам пришел – общий или с ID.
        //Если общий – то вернет URI_CONTACTS, если с ID – то вернет URI_CONTACTS_ID.

        //мы можем использовать спецсимволы:
        //* - строка любых символов любой длины,
        //# - строка цифр любой длины.
    }

    //помошник для работы с БД.
    DBHelper dbHelper;
    SQLiteDatabase db;

    public boolean onCreate() {
        Log.d(LOG_TAG, "onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    //чтение.
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //в методе получаем на вход Uri uri и набор параметров для выборки из БД:
        //projection - столбцы,
        //selection - условие,
        //selectionArgs – аргументы для условия,
        //sortOrder – сортировка.
        Log.d(LOG_TAG, "query, " + uri.toString());

        //проверяем Uri. Отдаём Uri uriMatcher-у, он определяет тип Uri (общий или с ID).
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS: //общий Uri
                Log.d(LOG_TAG, "URI_CONTACTS");
                //если сортировка не указана, ставим свою - по имени.
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = CONTACT_NAME + " ASC";
                }
                break;
            case URI_CONTACTS_ID: //Uri с ID
                //извлекаем ID из Uri.
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_CONTACTS_ID, " + id);
                //добавляем ID к условию выборки.
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                //если uriMatcher не смог распознать Uri, выводим Exception.
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        //получаем БД.
        db = dbHelper.getWritableDatabase();

        //выполняем для БД метод query(), получаем курсор.
        Cursor cursor = db.query(CONTACT_TABLE, projection, selection,
                selectionArgs, null, null, sortOrder);
        //просим ContentResolver уведомлять этот курсор
        //об изменениях данных в CONTACT_CONTENT_URI
        cursor.setNotificationUri(getContext().getContentResolver(),
                CONTACT_CONTENT_URI);
        return cursor;
    }

    public Uri insert(Uri uri, ContentValues values) {
        Log.d(LOG_TAG, "insert, " + uri.toString());
        //проверяем, что пришёл общий Uri (URI_CONTACTS).
        if (uriMatcher.match(uri) != URI_CONTACTS) {
            throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        db = dbHelper.getWritableDatabase();

        //если всё хорошо, то вставляем данные в таблицу.
        long rowID = db.insert(CONTACT_TABLE, null, values);
        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowID);
        //уведомляем ContentResolver, что данные по адресу resultUri изменились.
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(LOG_TAG, "delete, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            //проверяем, какой Uri к нам пришёл.
            case URI_CONTACTS:
                Log.d(LOG_TAG, "URI_CONTACTS");
                break;
            case URI_CONTACTS_ID:
                // если с id, то извлекаем id и добавляем его в selection.
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_CONTACTS_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(CONTACT_TABLE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(LOG_TAG, "update, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                Log.d(LOG_TAG, "URI_CONTACTS");

                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_CONTACTS_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(CONTACT_TABLE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public String getType(Uri uri) {
        //В методе getType возвращаем типы соответственно типу Uri – общий или с ID.
        Log.d(LOG_TAG, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                return CONTACT_CONTENT_TYPE;
            case URI_CONTACTS_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    private class DBHelper extends SQLiteOpenHelper {
        //Класс DBHelper помогает нам создать БД и наполнить ее первоначальными данными.

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
            ContentValues cv = new ContentValues();
            for (int i = 1; i <= 3; i++) {
                cv.put(CONTACT_NAME, "name " + i);
                cv.put(CONTACT_EMAIL, "email " + i);
                db.insert(CONTACT_TABLE, null, cv);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Обновление здесь не реализуем.
        }
    }
}