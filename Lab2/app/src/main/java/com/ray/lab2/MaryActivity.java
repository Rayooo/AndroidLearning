package com.ray.lab2;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MaryActivity extends AppCompatActivity {

    private int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mary);
        Intent intent = getIntent();
        num = intent.getIntExtra("extraData",0);
        Toast.makeText(MaryActivity.this, Integer.toString(num), Toast.LENGTH_SHORT).show();
    }

    //返回键点击事件
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MaryActivity.this,JackActivity.class);
        String parity = num % 2 == 0 ?"偶数":"奇数";
        intent.putExtra("parity",parity);
        setResult(RESULT_OK, intent);
        finish();
    }
}
