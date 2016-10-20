package com.ray.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        TextView userNameTextView = (TextView)findViewById(R.id.userNameText);
        userNameTextView.setText("用户名:" + userName);
    }
}
