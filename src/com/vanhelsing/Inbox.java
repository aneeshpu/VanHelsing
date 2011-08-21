package com.vanhelsing;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

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

		listView.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View message) {

				Log.i("aneesh", "aneesh has long clicked the message");
				TextView messageBox = (TextView) message;
				Toast.makeText(getApplicationContext(), messageBox.getText(), Toast.LENGTH_SHORT);
				return true;
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> listView, View messageView, int position, long id) {
				TextView message = (TextView) messageView;
				Log.i("aneesh", "aneesh textview == null ? " + (message == null) + " position = " + position);
				Toast.makeText(getApplicationContext(), message.getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}