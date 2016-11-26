package com.ray.lab6;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ray on 2016/11/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    DatabaseHelper(Context context) {
        //数据库名称为Manager
        super(context, "Manager", null, 1);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE student(" +
                "code TEXT UNIQUE PRIMARY KEY," +
                "name TEXT," +
                "sex TEXT," +
                "mobile TEXT," +
                "address TEXT)");
        db.execSQL("CREATE TABLE user(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userName TEXT UNIQUE," +
                "password TEXT," +
                "userType TEXT," +
                "studentCode TEXT," +
                "FOREIGN KEY (studentCode) REFERENCES student(code))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
