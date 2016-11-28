package com.ray.lab6;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        List<Person> data = new ArrayList<>();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM user",null);
        while (cursor.moveToNext()){
            String userName = cursor.getString(cursor.getColumnIndex("userName"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            data.add(new Person(userName,Integer.toString(id),"user"));
        }
        cursor.close();

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new PersonAdapter(this, R.layout.item_person, data));
        listView.setOnItemClickListener(new itemClickListener());
    }

    public void showStudent(View v){
        //设置按钮状态
        ToggleButton showUserButton = (ToggleButton)findViewById(R.id.showUser);
        ToggleButton showStudentButton = (ToggleButton)findViewById(R.id.showStudent);
        showUserButton.setChecked(false);
        showStudentButton.setChecked(true);

        List<Person> data = new ArrayList<>();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM student",null);
        while (cursor.moveToNext()){
            String studentName = cursor.getString(cursor.getColumnIndex("name"));
            String code = cursor.getString(cursor.getColumnIndex("code"));

            data.add(new Person(studentName,code,"student"));
        }
        cursor.close();

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new PersonAdapter(this,R.layout.item_person,data));
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
                Intent intent = new Intent(this,AddUserActivity.class);
                startActivityForResult(intent, 1);
                return true;
            case R.id.addNewStudent:
                Intent intentToStudent = new Intent(this,AddStudentActivity.class);
                startActivityForResult(intentToStudent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class itemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Person person = (Person)parent.getItemAtPosition(position);
            Log.i("name",person.getName());
            Log.i("id", person.getPrimaryKey());
            Log.i("type", person.getType());
            //根据student和user类型跳转
            if(person.getType().equals("user")){
                Intent intent = new Intent(getApplicationContext(),EditUserInfoActivity.class);
                intent.putExtra("id",person.getPrimaryKey());
                intent.putExtra("type",person.getType());
                startActivityForResult(intent, 1);
            }
            else if(person.getType().equals("student")){
                Intent intent = new Intent(getApplicationContext(),EditStudentInfoActivity.class);
                intent.putExtra("code",person.getPrimaryKey());
                startActivityForResult(intent, 1);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            if(data.getStringExtra("from").equals("addUser") || data.getStringExtra("from").equals("editUser")){
                showUser(null);
            }
            else if(data.getStringExtra("from").equals("addStudent") || data.getStringExtra("from").equals("editStudent")){
                showStudent(null);
            }
        }
    }
}

