package com.ray.lab7;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class EditContactActivity extends AppCompatActivity {

    ContactInfo contactInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        contactInfo = new ContactInfo(id,this);
        ((EditText)findViewById(R.id.nameEditText)).setText(contactInfo.getName());
        ((EditText)findViewById(R.id.mobileEditText)).setText(contactInfo.getPhoneNumber());
        ((EditText)findViewById(R.id.phoneEditText)).setText(contactInfo.getTelNumber());
        ((EditText)findViewById(R.id.addressEditText)).setText(contactInfo.getAddress());
        ((EditText)findViewById(R.id.emailEditText)).setText(contactInfo.getEmail());
        ((EditText)findViewById(R.id.indexMobileEditText)).setText(contactInfo.getWebsite());
    }

    public void modifyContact(View v){
        String name = ((EditText)findViewById(R.id.nameEditText)).getText().toString();
        contactInfo.setName(name,this);

    }

    public void removeContact(View v){
        // TODO: 2016/12/6 删除联系人
    }

}