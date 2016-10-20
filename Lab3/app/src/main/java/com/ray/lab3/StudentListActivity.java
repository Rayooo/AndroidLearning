package com.ray.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StudentListActivity extends AppCompatActivity {

    private String[] cs141 = {"张三","李四","王五"};
    private String[] cs142 = {"小陈","小李","小汤","小穆"};
    private String[] cs143 = {"小A","小B","小C","小D","小E"};
    private String[] other = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Intent intent = getIntent();
        String courseName = intent.getStringExtra("courseName");
        if(!courseName.equals("")){
            setTitle(courseName + "名单");
        }
        else{
            setTitle("空班级");
        }
        ListView listView = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter;
        switch (courseName) {
            case "计算机141":
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, cs141);
                break;
            case "计算机142":
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, cs142);
                break;
            case "计算机143":
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, cs143);
                break;
            default:
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, other);
                break;
        }
        listView.setAdapter(adapter);

    }
}
