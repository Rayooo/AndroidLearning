package com.ray.lab2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JackActivity extends AppCompatActivity {

    private int jackRequestCode = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jack);
        //题目1，2，3
        int randomNum = (int)(Math.random()*1000);
        final EditText jackEditText = (EditText)findViewById(R.id.jackEditText);
        jackEditText.setText(String.format("%s",randomNum));

        Button jackButton = (Button)this.findViewById(R.id.jackButton);
        jackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(jackEditText.getText().toString());
                Intent intent = new Intent(JackActivity.this,MaryActivity.class);
                intent.putExtra("extraData",num);
                startActivityForResult(intent, jackRequestCode);
            }
        });
    }

    //点击返回键后显示奇偶性
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == jackRequestCode && resultCode == RESULT_OK){
            String parity = data.getStringExtra("parity");
            Toast.makeText(this,parity,Toast.LENGTH_SHORT).show();
        }
    }

    //题目4打电话
    public void call(View view){
        EditText phoneNumEditText = (EditText)findViewById(R.id.phoneNum);
        String phoneNum = phoneNumEditText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNum));
        startActivity(intent);
    }

}
