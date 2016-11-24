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

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
    }

    public void ok(View v){
        String name = ((EditText)findViewById(R.id.nameEditText)).getText().toString();
        String code = ((EditText)findViewById(R.id.codeEditText)).getText().toString();

        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);
        String sex = ((RadioButton)this.findViewById(rg.getCheckedRadioButtonId())).getText().toString();

        String mobile = ((EditText)findViewById(R.id.mobileEditText)).getText().toString();
        String address = ((EditText)findViewById(R.id.addressEditText)).getText().toString();

        if(name.isEmpty() || code.isEmpty() || mobile.isEmpty() || address.isEmpty()){
            Toast.makeText(this, "请填写完整所有内容", Toast.LENGTH_SHORT).show();
        }
        else{
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM student WHERE code=?",new String[]{code});
            if (cursor.moveToNext()){
                Toast.makeText(this, "存在重复学号，请重新输入学号", Toast.LENGTH_SHORT).show();
            }
            else{
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", name);
                contentValues.put("code", code);
                contentValues.put("sex", sex);
                contentValues.put("mobile", mobile);
                contentValues.put("address", address);
                db.insert("student", null, contentValues);
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();

                //返回
                Intent returnIntent = new Intent();
                returnIntent.putExtra("from", "addStudent");
                setResult(RESULT_OK,returnIntent);
                finish();
            }
            cursor.close();
        }

    }
}
