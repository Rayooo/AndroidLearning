package com.ray.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final EditText userNameEditText = (EditText)findViewById(R.id.userNameEditText);
        final EditText passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        Button button = (Button)findViewById(R.id.ok);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(!userName.equals("") && !password.equals("")){
                    Intent intent = new Intent(UserActivity.this,UserListActivity.class);
                    intent.putExtra("userName",userName);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(UserActivity.this,"请输入用户名和密码",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
