package com.vanhelsing;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Inbox extends ListActivity {
	private static final String SMS_CONTENT_URI = "content://sms/inbox";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Cursor cursor = managedQuery(Uri.parse(SMS_CONTENT_URI), new String[] { "_id", "body" }, null, null, "date DESC");
		ListAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.main, cursor, new String[] { "body" }, new int[] { R.id.message });
		setListAdapter(listAdapter);

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long id) {
				Log.i("aneesh", "aneesh long pressed message: " + ((TextView)view).getText());
				return true;
			}

		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
				Log.i("aneesh", "aneesh clicked message: " + ((TextView)view).getText());
			}
		});

	}
}