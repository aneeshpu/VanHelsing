package com.vanhelsing;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Inbox extends ListActivity {
	private static final String SMS_CONTENT_URI = "content://sms/inbox";

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Cursor cursor = managedQuery(Uri.parse(SMS_CONTENT_URI), new String[] { "_id", "body" }, null, null, "date DESC");
		final ListAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.main, cursor, new String[] { "body" }, new int[] { R.id.message });
		setListAdapter(listAdapter);

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		
		registerForContextMenu(listView);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
		LinearLayout linearLayout = (LinearLayout) contextMenuInfo.targetView;

		TextView textView = (TextView) linearLayout.getChildAt(0);

		Log.i("vanhelsing", "vanhelsing long pressed message: " + (textView.getText()));

		menu.setHeaderTitle("Spam picker");
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.inbox_context, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo contextMenuInfo = (AdapterContextMenuInfo) item.getMenuInfo();

		LinearLayout targetView = (LinearLayout) contextMenuInfo.targetView;

		TextView textView = (TextView) targetView.getChildAt(0);
		final TrainingData trainingData = ClassifierFactory.makeClassifier(this).markAsSpam(new DocumentFactory().makeDocument((String) textView.getText(), TrainerFactory.trainingData(this)));
		
//		new TrainingDataDao(this).saveOrUpdate(trainingData);
		final ContentValues contentValues = makeContentValues(trainingData);
		return true;
	}

	private ContentValues makeContentValues(TrainingData trainingData) {
		final ContentValues contentValues = new ContentValues();
		return contentValues;
	}
}