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

public class EditStudentInfoActivity extends AppCompatActivity {

    private String rawCode;
    private String name;
    private String sex;
    private String address;
    private String mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student_info);

        Intent intent = getIntent();
        rawCode = intent.getStringExtra("code");

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM student WHERE code=?",new String[]{rawCode});
        while (cursor.moveToNext()){
            name = cursor.getString(cursor.getColumnIndex("name"));
            sex = cursor.getString(cursor.getColumnIndex("sex"));
            mobile = cursor.getString(cursor.getColumnIndex("mobile"));
            address = cursor.getString(cursor.getColumnIndex("address"));
        }
        cursor.close();


        ((EditText)findViewById(R.id.nameEditText)).setText(name);
        ((EditText)findViewById(R.id.codeEditText)).setText(rawCode);
        ((EditText)findViewById(R.id.mobileEditText)).setText(mobile);
        ((EditText)findViewById(R.id.addressEditText)).setText(address);

        if(sex.equals("女")){
            RadioButton b = (RadioButton) findViewById(R.id.femaleRadioButton);
            b.setChecked(true);
        }
        else{
            RadioButton b = (RadioButton) findViewById(R.id.maleRadioButton);
            b.setChecked(true);
        }
    }


    public void modify(View v){
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
            if (cursor.moveToNext() && !cursor.getString(cursor.getColumnIndex("code")).equals(rawCode)){
                Toast.makeText(this, "存在重复学号，请重新输入学号", Toast.LENGTH_SHORT).show();
            }
            else{
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", name);
                contentValues.put("code", code);
                contentValues.put("sex", sex);
                contentValues.put("mobile", mobile);
                contentValues.put("address", address);
                db.update("student", contentValues,"code=?",new String[]{rawCode});
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();

                //返回
                Intent returnIntent = new Intent();
                returnIntent.putExtra("from", "editStudent");
                setResult(RESULT_OK,returnIntent);
                finish();
            }
            cursor.close();
        }
    }

    public void deleteStudent(View v){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        db.delete("student","code=?",new String[]{rawCode});

        //返回
        Intent returnIntent = new Intent();
        returnIntent.putExtra("from", "editStudent");
        setResult(RESULT_OK,returnIntent);
        finish();
    }

}
