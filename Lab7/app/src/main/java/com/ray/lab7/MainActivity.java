package com.ray.lab7;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView contactsListView;

    private ArrayAdapter<Person> adapter;

    private List<Person> contactsList;

    private Person currentPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("联系人列表");

        contactsListView = (ListView)findViewById(R.id.listView);
        readContacts();
        adapter = new PersonAdapter(this, R.layout.adapter_person,contactsList);
        contactsListView.setAdapter(adapter);
        contactsListView.setOnItemClickListener(new listViewClickListener());

        ((Button)findViewById(R.id.editPersonButton)).setVisibility(View.GONE);
        ((Button)findViewById(R.id.showPersonInfoButton)).setVisibility(View.GONE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        readContacts();
        adapter = new PersonAdapter(this, R.layout.adapter_person,contactsList);
        contactsListView.setAdapter(adapter);

        ((Button)findViewById(R.id.editPersonButton)).setVisibility(View.GONE);
        ((Button)findViewById(R.id.showPersonInfoButton)).setVisibility(View.GONE);
    }

    private void readContacts() {
        contactsList = new ArrayList<>();
        try {
            Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
            while (cursor != null && cursor.moveToNext()){
                //联系人姓名
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String telNumber = "",phoneNumber = "";
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                int phoneCount = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if (phoneCount > 0) {
                    // 获得联系人的电话号码
                    Cursor phones = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                    while (phones.moveToNext()){
                        // 遍历所有的电话号码
                        int phoneType = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    }
                }
                Person person = new Person(displayName,phoneNumber,id);
                contactsList.add(person);
            }
            if (cursor != null) {
                cursor.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class listViewClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            currentPerson = (Person)parent.getItemAtPosition(position);
            //设置按钮可见
            ((Button)findViewById(R.id.editPersonButton)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.showPersonInfoButton)).setVisibility(View.VISIBLE);

        }
    }

    public void addContact(View v){
        Intent intent = new Intent(this, AddContactActivity.class);
        startActivityForResult(intent, 1);
    }

    public void exitContact(View v){
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    public void editContact(View v){
        Intent intent = new Intent(this, EditContactActivity.class);
        intent.putExtra("id",currentPerson.getId());
        startActivityForResult(intent, 1);
    }

    public void showContactInfo(View v){
        Intent intent = new Intent(this, ShowContactActivity.class);
        intent.putExtra("id",currentPerson.getId());
        startActivityForResult(intent, 1);
    }

}
