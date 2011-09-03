package com.vanhelsing.contentProvider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class SpamContentProvider extends ContentProvider {

	public static abstract class Table {

		private SpamSqliteHelper spamSqliteHelper;

		public long insert(ContentValues values, Context context) {
			spamSqliteHelper = new SpamSqliteHelper(context);
			long insertedId = spamSqliteHelper.getWritableDatabase().insert(FeatureTable.DB_TABLE_NAME, null, values);			
			return insertedId;
		}

		public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
			return spamSqliteHelper.getWritableDatabase().query(FeatureTable.DB_TABLE_NAME, projection, selection, selectionArgs, null, null, null);			
		}

	}

	private static final String AUTHORITY = "com.vanhelsing.contentProvider";

	private SpamSqliteHelper spamSqliteHelper;

	private final HashMap<Uri, Table> tableMap;

	public static final class FeatureTable extends Table{

		public static final Uri FEATURE_URI = Uri.parse(String.format("content://%s/feature", AUTHORITY));

		public static final String DB_TABLE_NAME = "feature";
		public static final String DB_COL_NAME = "name";
	}

	public static final class ClassificationTable extends Table {
		public static final Uri CLASSIFICATION_URI = Uri.parse(String.format("content://%s/classification", AUTHORITY));
	}

	public SpamContentProvider() {
		tableMap = new HashMap<Uri, Table>();
		tableMap.put(FeatureTable.FEATURE_URI, new FeatureTable());
		tableMap.put(ClassificationTable.CLASSIFICATION_URI, new ClassificationTable());
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
		long insertedId = tableMap.get(uri).insert(values, getContext());

		return Uri.withAppendedPath(uri, String.valueOf(insertedId));
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Log.i("vanhelsing", "Inside SpamContentProvider.query()");
		return tableMap.get(uri).query(projection, selection, selectionArgs, sortOrder);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
