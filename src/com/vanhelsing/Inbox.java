package com.vanhelsing;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class Inbox extends ListActivity {
    private static final String SMS_CONTENT_URI = "content://sms/inbox";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Cursor cursor = managedQuery(Uri.parse(SMS_CONTENT_URI), new String[] {"_id", "body"}, null, null, "date DESC");
		ListAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.main, cursor, new String[]{"body"}, new int[] {R.id.message});
		
		setListAdapter(listAdapter);
    }
}