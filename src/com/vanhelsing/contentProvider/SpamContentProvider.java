package com.vanhelsing.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class SpamContentProvider extends ContentProvider{

	public static final Uri URI = Uri.parse("content://com.vanhelsing.contentProvider/feature");
	public static final String DB_TABLE_NAME = "feature";
	public static final String DB_COL_NAME = "name";
	
	private SpamSqliteHelper spamSqliteHelper;
	
	public SpamContentProvider(){
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Log.i("vanhelsing", "Inside SpamContentProvider.insert()");
		spamSqliteHelper = new SpamSqliteHelper(getContext());
		spamSqliteHelper.getWritableDatabase().insert(DB_TABLE_NAME, null, values);
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Log.i("vanhelsing", "Inside SpamContentProvider.query()");
		return spamSqliteHelper.getWritableDatabase().query(DB_TABLE_NAME, new String[]{SpamContentProvider.DB_COL_NAME}, selection, selectionArgs, null, null, null);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
