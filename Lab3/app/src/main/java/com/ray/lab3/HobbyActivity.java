package com.ray.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class HobbyActivity extends AppCompatActivity {

    private class checkBoxChangeListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                selectedStrings.add(buttonView.getText().toString());
            }
            else{
                selectedStrings.remove(buttonView.getText().toString());
            }
        }
    }

    private ArrayList<String> selectedStrings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);
        setTitle("兴趣爱好选择");
        CheckBox musicCheckBox = (CheckBox) findViewById(R.id.MusicCheckBox);
        CheckBox movieCheckBox = (CheckBox) findViewById(R.id.movieCheckBox);
        CheckBox readingCheckBox = (CheckBox) findViewById(R.id.readingCheckBox);
        CheckBox basketballCheckBox = (CheckBox) findViewById(R.id.basketballCheckBox);
        CheckBox tennisCheckBox = (CheckBox) findViewById(R.id.basketballCheckBox);
        musicCheckBox.setOnCheckedChangeListener(new checkBoxChangeListener());
        movieCheckBox.setOnCheckedChangeListener(new checkBoxChangeListener());
        readingCheckBox.setOnCheckedChangeListener(new checkBoxChangeListener());
        basketballCheckBox.setOnCheckedChangeListener(new checkBoxChangeListener());
        tennisCheckBox.setOnCheckedChangeListener(new checkBoxChangeListener());

    }

    public void ok(View view){
        String hobby = "";
        for(int i = 0;i < selectedStrings.size();++i){
            if(i == selectedStrings.size() - 1){
                hobby += selectedStrings.get(i);
            }
            else {
                hobby += selectedStrings.get(i) + "、";
            }
        }
        Intent intent = new Intent(this,HobbyListActivity.class);
        intent.putExtra("hobby",hobby);
        startActivity(intent);
    }
}
