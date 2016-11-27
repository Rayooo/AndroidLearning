package com.ray.lab6;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditUserInfoActivity extends AppCompatActivity {

    private String id;
    private String rawUserName;
    private String password;
    private String userType;
    private String studentCode = null;

    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText repeatEditText;
    private EditText studentCodeEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                if(checkedId == R.id.typeManager){
                    userType = "M";
                    findViewById(R.id.studentCodeTextView).setVisibility(View.GONE);
                    findViewById(R.id.studentCodeEditText).setVisibility(View.GONE);
                }
                else if(checkedId == R.id.typeStudent){
                    userType = "S";
                    findViewById(R.id.studentCodeTextView).setVisibility(View.VISIBLE);
                    findViewById(R.id.studentCodeEditText).setVisibility(View.VISIBLE);
                }
            }
        });


        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE id=?",new String[]{id});
        while (cursor.moveToNext()){
            rawUserName = cursor.getString(cursor.getColumnIndex("userName"));
            password = cursor.getString(cursor.getColumnIndex("password"));
            userType = cursor.getString(cursor.getColumnIndex("userType"));
            if(userType.equals("S")){
                studentCode = cursor.getString(cursor.getColumnIndex("studentCode"));
                RadioButton b = (RadioButton) findViewById(R.id.typeStudent);
                b.setChecked(true);
                findViewById(R.id.studentCodeTextView).setVisibility(View.VISIBLE);
                findViewById(R.id.studentCodeEditText).setVisibility(View.VISIBLE);
                studentCodeEditText = (EditText) findViewById(R.id.studentCodeEditText);
                studentCodeEditText.setText(studentCode);
            }
            else{
                RadioButton b = (RadioButton) findViewById(R.id.typeManager);
                b.setChecked(true);
                findViewById(R.id.studentCodeTextView).setVisibility(View.GONE);
                findViewById(R.id.studentCodeEditText).setVisibility(View.GONE);
            }
        }
        cursor.close();

        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        repeatEditText = (EditText) findViewById(R.id.repeatEditText);

        userNameEditText.setText(rawUserName);
        passwordEditText.setText(password);
        repeatEditText.setText(password);


    }

    public void modify(View v){

        String userName = ((EditText)findViewById(R.id.userNameEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();
        String repeatPassword = ((EditText)findViewById(R.id.repeatEditText)).getText().toString();
        String studentCode = ((EditText)findViewById(R.id.studentCodeEditText)).getText().toString();

        if(repeatPassword.equals(password)){
            //密码相同可以被添加
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM user WHERE userName=?",new String[]{userName});

            if(cursor.moveToNext() && !cursor.getString(cursor.getColumnIndex("userName")).equals(rawUserName)){
                Toast.makeText(this, "存在重复用户名，请重新输入用户名", Toast.LENGTH_SHORT).show();
            }
            else{
                if(userType.equals("M")){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("userName",userName);
                    contentValues.put("password",password);
                    contentValues.put("userType","M");
                    contentValues.put("studentCode", "");
                    db.update("user",contentValues,"id=?",new String[]{id});

                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();

                    //返回
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("from", "editUser");
                    setResult(RESULT_OK,returnIntent);
                    finish();
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
                            db.update("user",contentValues,"id=?",new String[]{id});

                            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();

                            //返回
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("from", "editUser");
                            setResult(RESULT_OK,returnIntent);
                            finish();

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

    public void deleteUser(View v){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        db.delete("user","id=?",new String[]{id});

        //返回
        Intent returnIntent = new Intent();
        returnIntent.putExtra("from", "editUser");
        setResult(RESULT_OK,returnIntent);
        finish();
    }

}
