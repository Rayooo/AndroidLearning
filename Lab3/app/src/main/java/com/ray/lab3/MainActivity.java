package com.ray.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goTest1(View view){
        Intent intent = new Intent(this,UserActivity.class);
        startActivity(intent);
    }

    public void goTest2(View view){
        Intent intent = new Intent(this,SayHelloActivity.class);
        startActivity(intent);
    }

    public void goTest3(View view){
        Intent intent = new Intent(this,HobbyActivity.class);
        startActivity(intent);
    }

    public void goTest4(View view){
        Intent intent = new Intent(this, CourseActivity.class);
        startActivity(intent);
    }

    public void goTest5(View view){
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

}
