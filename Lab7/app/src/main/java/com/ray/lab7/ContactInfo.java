package com.ray.lab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by Ray on 2016/12/6.
 */
public class ContactInfo {
    private String id = "";
    private String name = "";
    private String telNumber = "";
    private String phoneNumber = "";
    private String website = "";
    private String address = "";
    private String email = "";

    public ContactInfo(String id, Context context) {
        this.id = id;
        Cursor cur = context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                ContactsContract.Contacts._ID+"="+id,
                null,
                null);
        // 循环遍历
        if (cur.moveToNext()) {
            int idColumn = cur.getColumnIndex(ContactsContract.Contacts._ID);
            int displayNameColumn = cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            
            do {
                // 获得联系人的ID号
                String contactId = cur.getString(idColumn);
                // 获得联系人姓名
                name = cur.getString(displayNameColumn);
                name = name == null ? "" : name;
                // 查看该联系人有多少个电话号码。如果没有这返回值为0
                int phoneCount = cur
                        .getInt(cur
                                .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                Log.i("username", name);

                if (phoneCount > 0) {
                    // 获得联系人的电话号码
                    Cursor phoneCursor = context.getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                    + " = " + contactId, null, null);
                    if (phoneCursor.moveToFirst()) {
                        do {
                            // 遍历所有的电话号码
                            int phoneType = phoneCursor
                                    .getInt(phoneCursor
                                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                            if(phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_HOME){
                                telNumber = phoneCursor
                                        .getString(phoneCursor
                                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                telNumber = telNumber == null ? "" : telNumber;
                            }else{
                                phoneNumber = phoneCursor
                                        .getString(phoneCursor
                                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                phoneNumber = phoneNumber == null ? "" : phoneNumber;
                            }
                            Log.i("phoneNumber", phoneNumber);
                            Log.i("phoneType", phoneType+"");
                        } while (phoneCursor.moveToNext());
                    }
                }


                // 获取该联系人邮箱
                Cursor emailCursor = context.getContentResolver().query(
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = " + contactId, null, null);
                if (emailCursor.moveToFirst()) {
                    do {
                        // 遍历所有的电话号码
                        email = emailCursor
                                .getString(emailCursor
                                        .getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        email = email == null ? "" : email;

//                        Log.i("emailType", emailType);
                        Log.i("email", email);
                    } while (emailCursor.moveToNext());
                }


                // 获取该联系人地址
                Cursor addressCursor = context.getContentResolver()
                        .query(
                                ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                        + " = " + contactId, null, null);
                if (addressCursor.moveToFirst()) {
                    do {
                        address = addressCursor
                                .getString(addressCursor
                                        .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS));
                        address = address == null ? "" : address;
                        Log.i("address", address);
                    } while (addressCursor.moveToNext());
                }


                // 获取该联系人主页

                String webWhere = ContactsContract.Data.CONTACT_ID + " = ? AND "
                        + ContactsContract.Data.MIMETYPE + " = ?";
                String[] webWhereParams = new String[]{contactId,
                        ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE};
                Cursor websiteCursor = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null,
                        webWhere, webWhereParams, null);

                if (websiteCursor.moveToFirst())
                {
                    website = websiteCursor.getString(websiteCursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.URL)) + ",";
                    website = website == null ? "" : website;
                    Log.d("website", website);
                }

                websiteCursor.close();
            } while (cur.moveToNext());
        }


    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name,Context context) {
        this.name = name;
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactsContract.Contacts.DISPLAY_NAME,name);
        context.getContentResolver().update(ContactsContract.Contacts.CONTENT_URI, contentValues, ContactsContract.Contacts._ID + "=" + id, null);
        // TODO: 2016/12/6 更新联系人姓名
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
