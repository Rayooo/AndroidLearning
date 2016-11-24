package com.ray.lab6;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ray on 2016/11/19.
 */
public class AddData extends Activity {

    //增加管理员方法
    public void addManager(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userName","ray2");
        values.put("password","123");
        values.put("userType","M");
        db.insert("user",null,values);
    }

    //删除用户方法
    public void deleteUser(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete("user","id=?",new String[] {"1"});
    }

    public void addUser(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userName","rayStudent");
        values.put("password","123");
        values.put("userType","S");
        values.put("studentCode","2014210834");
        db.insert("user",null,values);
    }

    public void addStudent(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("code","2014210834");
        values.put("name","陈枭磊");
        values.put("sex","男");
        values.put("mobile","15012312312");
        values.put("address","杭州");
        db.insert("student",null,values);
    }


}
