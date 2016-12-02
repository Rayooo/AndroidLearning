package com.ray.lab6;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }




    public void doLogin(View view) {

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String userName = ((EditText)findViewById(R.id.userName)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE userName=? AND password=?",new String[]{userName,password});
        if(cursor.moveToFirst()){
            cursor.move(0);
            String userNameFromDB = cursor.getString(cursor.getColumnIndex("userName"));
            String passwordFromDB = cursor.getString(cursor.getColumnIndex("password"));
            if(userName.equals(userNameFromDB) && password.equals(passwordFromDB)){
                //登陆成功,跳转
                if(cursor.getString(cursor.getColumnIndex("userType")).equals("M")){
                    //管理员跳转
                    Intent intent = new Intent(this,ManagerMainActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(this,EditStudentUserInfoActivity.class);
                    intent.putExtra("userId",cursor.getString(cursor.getColumnIndex("id")));
                    startActivity(intent);
                }
            }
            else{
                Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }
}
