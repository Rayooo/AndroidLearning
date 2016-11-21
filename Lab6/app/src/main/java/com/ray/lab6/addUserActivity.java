package com.ray.lab6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class addUserActivity extends AppCompatActivity {

    private boolean isManager = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        RadioGroup radgroup = (RadioGroup) findViewById(R.id.radioGroup);
        radgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                if(checkedId == R.id.typeManager){
                    isManager = true;
                    findViewById(R.id.studentCodeTextView).setVisibility(View.GONE);
                    findViewById(R.id.studentCodeEditText).setVisibility(View.GONE);
                }
                else if(checkedId == R.id.typeStudent){
                    isManager = false;
                    findViewById(R.id.studentCodeTextView).setVisibility(View.VISIBLE);
                    findViewById(R.id.studentCodeEditText).setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void ok(View v){
        String userName = ((EditText)findViewById(R.id.userNameEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();
        String repeatPassword = ((EditText)findViewById(R.id.repeatEditText)).getText().toString();
        String studentCode = ((EditText)findViewById(R.id.studentCodeEditText)).getText().toString();

        if(repeatPassword.equals(password)){
            //密码相同可以被添加
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM user WHERE userName=?",new String[]{userName});

            if(cursor.moveToNext()){
                Toast.makeText(this, "存在重复用户名，请重新输入用户名", Toast.LENGTH_SHORT).show();
            }
            else{
                if(isManager){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("userName",userName);
                    contentValues.put("password",password);
                    contentValues.put("userType","M");
                    db.insert("user",null,contentValues);
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                }
                else{
                    //检查学号是否有用户被注册
                    cursor = db.rawQuery("SELECT * FROM user WHERE studentCode=?",new String[]{studentCode});
                    if(cursor.moveToNext()){
                        Toast.makeText(this, "存在相同学号的用户，不能添加", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        cursor = db.rawQuery("SELECT * FROM student WHERE code=?",new String[]{studentCode});
                        if(cursor.moveToNext()){
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("userName",userName);
                            contentValues.put("password",password);
                            contentValues.put("userType","S");
                            contentValues.put("studentCode", studentCode);
                            db.insert("user",null,contentValues);
                            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //不存在学生
                            Toast.makeText(this, "请先添加学生信息后再添加学生用户", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            cursor.close();
        }
        else{
            Toast.makeText(this, "两次密码输入不同，请重新输入", Toast.LENGTH_SHORT).show();
        }
    }

}
