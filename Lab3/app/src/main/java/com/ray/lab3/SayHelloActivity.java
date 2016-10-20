package com.ray.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SayHelloActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_say_hello);

        textView = (TextView)findViewById(R.id.HelloText);
        editText = (EditText)findViewById(R.id.HelloEditText);

    }
    public void showText(View view){
        String text = editText.getText().toString();
        if(text.equals("")){
            textView.setText("Hello");
        }
        else{
            textView.setText("Hello, " + text);
        }
    }

}
