package com.ray.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class JackActivity extends AppCompatActivity {

    private int jackRequestCode = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jack);

        Button jackButton = (Button)this.findViewById(R.id.jackButton);
        jackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNum = (int)(Math.random()*1000);
                Intent intent = new Intent(JackActivity.this,MaryActivity.class);
                intent.putExtra("extraData",randomNum);
                startActivityForResult(intent, jackRequestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //todo 不知道为什么resultCode一直是0，前一个activity传过来是-1的
        if(requestCode == jackRequestCode && resultCode == RESULT_OK){
            String parity = data.getStringExtra("parity");
            Toast.makeText(this,parity,Toast.LENGTH_SHORT).show();
        }
    }
}
