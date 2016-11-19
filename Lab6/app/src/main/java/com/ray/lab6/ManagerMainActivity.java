package com.ray.lab6;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class ManagerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);
        showUser(null);
    }

    public void showUser(View v){
        //设置按钮状态
        ToggleButton showUserButton = (ToggleButton)findViewById(R.id.showUser);
        ToggleButton showStudentButton = (ToggleButton)findViewById(R.id.showStudent);
        showUserButton.setChecked(true);
        showStudentButton.setChecked(false);

        List<String> data = new ArrayList<>();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM user",null);
        while (cursor.moveToNext()){
            String userName = cursor.getString(cursor.getColumnIndex("userName"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            data.add(userName);
        }
        cursor.close();

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,data));
        listView.setOnItemClickListener(new itemClickListener());
    }

    public void showStudent(View v){
        //设置按钮状态
        ToggleButton showUserButton = (ToggleButton)findViewById(R.id.showUser);
        ToggleButton showStudentButton = (ToggleButton)findViewById(R.id.showStudent);
        showUserButton.setChecked(false);
        showStudentButton.setChecked(true);

        List<String> data = new ArrayList<>();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM student",null);
        while (cursor.moveToNext()){
            String studentName = cursor.getString(cursor.getColumnIndex("name"));
            String code = cursor.getString(cursor.getColumnIndex("code"));
            data.add(studentName);
        }
        cursor.close();

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,data));
        listView.setOnItemClickListener(new itemClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNewUser:
                //todo 新建用户
                return true;
            case R.id.addNewStudent:
                //todo 新建学生
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

class itemClickListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String data = (String)parent.getItemAtPosition(position);
        //todo 获取到点击位置跳转
    }
}