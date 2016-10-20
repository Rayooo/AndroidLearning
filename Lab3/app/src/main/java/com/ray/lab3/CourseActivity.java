package com.ray.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        setTitle("选择班级");
    }

    public void ok(View view){
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        for(int i = 0; i < radioGroup.getChildCount();++i){
            RadioButton rb = (RadioButton)radioGroup.getChildAt(i);
            if(rb.isChecked()){
                Intent intent = new Intent(this,StudentListActivity.class);
                intent.putExtra("courseName",rb.getText());
                startActivity(intent);
            }
        }
    }
}
