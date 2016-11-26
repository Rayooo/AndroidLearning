package com.ray.lab6;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditUserInfoActivity extends AppCompatActivity {

    private String id;
    private String userName;
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
            userName = cursor.getString(cursor.getColumnIndex("userName"));
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

        userNameEditText.setText(userName);
        passwordEditText.setText(password);
        repeatEditText.setText(password);


    }

    public void modify(View v){



    }

}
