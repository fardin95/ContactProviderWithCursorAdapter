package com.example.primetech.contactproviderwithcursoradapter;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private android.widget.ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listView = (ListView) findViewById(R.id.listView);
        ContentResolver contentResolver = getContentResolver();

        // Contact data
        String columsToExtract[] = new String[]{
                ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.PHOTO_THUMBNAIL_URI
        };

        String whereClause = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND (" + ContactsContract.Contacts.DISPLAY_NAME + " != '' ) AND (" + ContactsContract.Contacts.STARRED + "== 1))";

        // sort by incresing id
        String sortOrder = ContactsContract.Contacts._ID + " ASC";

        // query contats contentProvider

        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor!=null){
            int i = 0;
            cursor.moveToFirst();
            for ( i=0; i<cursor.getCount(); i++){
                String string = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Log.e("nAME", string);
                cursor.moveToNext();
            }
        }

        ContactInfoListAdapter contactInfoListAdapter = new ContactInfoListAdapter(this, R.layout.list_item, cursor, 1);
        listView.setAdapter(contactInfoListAdapter);
    }
}
