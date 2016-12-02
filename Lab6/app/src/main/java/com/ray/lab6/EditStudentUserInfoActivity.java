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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditStudentUserInfoActivity extends AppCompatActivity {

    private String userId;
    private String userName;
    private String password;
    private String studentCode;
    private String name;
    private String sex;
    private String mobile;
    private String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student_user_info);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE id=?",new String[]{userId});
        while (cursor.moveToNext()){
            userName = cursor.getString(cursor.getColumnIndex("userName"));
            password = cursor.getString(cursor.getColumnIndex("password"));
            studentCode = cursor.getString(cursor.getColumnIndex("studentCode"));
        }

        cursor = db.rawQuery("SELECT * FROM student WHERE code=?",new String[]{studentCode});
        while (cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndex("name"));
            sex = cursor.getString(cursor.getColumnIndex("sex"));
            address = cursor.getString(cursor.getColumnIndex("address"));
            mobile = cursor.getString(cursor.getColumnIndex("mobile"));
        }
        cursor.close();

        ((EditText)findViewById(R.id.userNameEditText)).setText(userName);
        ((EditText)findViewById(R.id.passwordEditText)).setText(password);
        ((EditText)findViewById(R.id.studentCodeEditText)).setText(studentCode);
        ((EditText)findViewById(R.id.nameEditText)).setText(name);
        ((EditText)findViewById(R.id.mobileEditText)).setText(mobile);
        ((EditText)findViewById(R.id.addressEditText)).setText(address);
        if(sex.equals("女")){
            ((RadioButton) findViewById(R.id.femaleRadioButton)).setChecked(true);
        }
        else{
            ((RadioButton) findViewById(R.id.maleRadioButton)).setChecked(true);
        }


    }

    public void ok(View v){
        String newUserName = ((EditText)findViewById(R.id.userNameEditText)).getText().toString();
        String newPassword = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();
        String newStudentCode = ((EditText)findViewById(R.id.studentCodeEditText)).getText().toString();
        String newName = ((EditText)findViewById(R.id.nameEditText)).getText().toString();
        String newMobile = ((EditText)findViewById(R.id.mobileEditText)).getText().toString();
        String newAddress = ((EditText)findViewById(R.id.addressEditText)).getText().toString();
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);
        String newSex = ((RadioButton)this.findViewById(rg.getCheckedRadioButtonId())).getText().toString();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor;
        boolean canEdit = true;
        String errorMessage = "";
        if(!newUserName.equals(userName)){
            cursor = db.rawQuery("SELECT * FROM user WHERE userName=?", new String[]{newUserName});
            if(cursor.moveToNext()){
                canEdit = false;
                errorMessage = "已存在相同用户名";
            }
            else{
                canEdit = true;
            }
        }
        if(canEdit && !newStudentCode.equals(studentCode)){
            cursor = db.rawQuery("SELECT * FROM user WHERE studentCode=?", new String[]{newStudentCode});
            if(cursor.moveToNext()){
                canEdit = false;
                errorMessage = "该学号已被使用";
            }
            else{
                canEdit = true;
            }
            cursor.close();
        }
        //根据能否更新来进行下一步操作
        if(!canEdit){
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
        else{
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", newName);
            contentValues.put("code", newStudentCode);
            contentValues.put("sex", newSex);
            contentValues.put("mobile", newMobile);
            contentValues.put("address", newAddress);
            db.update("student", contentValues,"code=?",new String[]{studentCode});

            ContentValues userContentValues = new ContentValues();
            userContentValues.put("userName", newUserName);
            userContentValues.put("password",newPassword);
            userContentValues.put("studentCode",newStudentCode);
            db.update("user", userContentValues, "id=?", new String[]{userId});
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }



    }

}
