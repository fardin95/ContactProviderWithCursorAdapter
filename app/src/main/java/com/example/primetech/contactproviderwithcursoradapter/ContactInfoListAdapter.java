package com.example.primetech.contactproviderwithcursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Prime Tech on 7/19/2016.
 */
public class ContactInfoListAdapter extends ResourceCursorAdapter {
    private Bitmap mNoPictureBitMaps;
    private String TAG = "ContactInfoListAdapter";


    public ContactInfoListAdapter(Context context, int list_item, Cursor cursor, int flage) {
        super(context, list_item,cursor, flage);
        mNoPictureBitMaps = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_stat_name);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return  inflater.inflate(R.layout.list_item, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = (TextView) view.findViewById(R.id.textView);
        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        textView.setText(name);


        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        String photoContactURI = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));

        if (null != photoContactURI) {
            InputStream inputStream = null;


            try {
                inputStream = context.getContentResolver().openInputStream(Uri.parse(photoContactURI));
                if (inputStream != null) {
                    mNoPictureBitMaps = BitmapFactory.decodeStream(inputStream);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.i(TAG, "bindView: FileNotFoundException");
            }

        }

        imageView.setImageBitmap(mNoPictureBitMaps);
    }
}
