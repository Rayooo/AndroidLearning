package com.ray.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HobbyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby_list);
        setTitle("兴趣爱好选择结果");
        Intent intent = getIntent();
        String hobby = intent.getStringExtra("hobby");
        TextView hobbyTextView = (TextView)findViewById(R.id.hobbyTextView);
        hobbyTextView.setText(hobby);
    }
}
