package com.ray.lab7;

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

    private List<Person> contactsList = new ArrayList<>();

    private Person currentPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsListView = (ListView)findViewById(R.id.listView);
        readContacts();
        adapter = new PersonAdapter(this, R.layout.adapter_person,contactsList);
        contactsListView.setAdapter(adapter);
        contactsListView.setOnItemClickListener(new listViewClickListener());

        //设置按钮不可见
        ((Button)findViewById(R.id.editPersonButton)).setVisibility(View.GONE);
        ((Button)findViewById(R.id.showPersonInfoButton)).setVisibility(View.GONE);

    }

    private void readContacts() {
        try {
            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (cursor != null && cursor.moveToNext()){
                //联系人姓名
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
                Person person = new Person(displayName,number,id);
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
            Person person = (Person)parent.getItemAtPosition(position);
            //设置按钮可见
            ((Button)findViewById(R.id.editPersonButton)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.showPersonInfoButton)).setVisibility(View.VISIBLE);

            Log.i("name",person.getName());
            Log.i("mobile",person.getMobile());
            Log.i("id",person.getId());
        }
    }


}
