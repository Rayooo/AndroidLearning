package com.ray.lab7;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShowContactActivity extends AppCompatActivity {

    private ContactInfo contactInfo;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        setTitle("联系人信息");

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        refresh();
    }

    private void refresh(){
        contactInfo = new ContactInfo(id,this);
        ((EditText)findViewById(R.id.nameEditText)).setText(contactInfo.getName());
        ((EditText)findViewById(R.id.mobileEditText)).setText(contactInfo.getPhoneNumber());
        ((EditText)findViewById(R.id.phoneEditText)).setText(contactInfo.getTelNumber());
        ((EditText)findViewById(R.id.addressEditText)).setText(contactInfo.getAddress());
        ((EditText)findViewById(R.id.emailEditText)).setText(contactInfo.getEmail());
        ((EditText)findViewById(R.id.indexMobileEditText)).setText(contactInfo.getWebsite());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        refresh();
    }


    public void back(View v){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    public void deleteContact(View v){
        contactInfo.deleteContact(this);
        //返回
        Intent returnIntent = new Intent();
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    public void editContact(View v){
        Intent intent = new Intent(this, EditContactActivity.class);
        intent.putExtra("id",contactInfo.getId());
        startActivityForResult(intent, 1);
    }

    public void callContact(View v){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contactInfo.getPhoneNumber()));
        startActivity(intent);
    }

}
