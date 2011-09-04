package com.vanhelsing.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public final class FeatureTable implements Table {

	public static final Uri URI = Uri.parse(String.format("content://%s/feature", SpamContentProvider.AUTHORITY));

	public static final String DB_TABLE_NAME = "features";
	public static final String DB_COL_NAME = "name";

	private class FeatureTableSqliteHelper extends SQLiteOpenHelper {

		private static final int DATABASE_VERSION = 1;
		private static final String DATABASE_NAME = "vanhelsing";

		public FeatureTableSqliteHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase sqliteDatabase) {
			sqliteDatabase.execSQL(createFeatureTable());
		}

		private String createFeatureTable() {
			return String.format("create table %s (_id INTEGER primary key autoincrement," + "%s TEXT)", FeatureTable.DB_TABLE_NAME,
					FeatureTable.DB_COL_NAME);
		}

		@Override
		public void onUpgrade(SQLiteDatabase sqliteDatabase, int olderVersion, int newerVersion) {
		}

	}

	private FeatureTableSqliteHelper spamSqliteHelper;

	@Override
	public long insert(ContentValues values, Context context) {
		// TODO: How do I get rid of this ugly check.
		if (spamSqliteHelper == null)
			spamSqliteHelper = new FeatureTableSqliteHelper(context);
		long insertedId = spamSqliteHelper.getWritableDatabase().insert(FeatureTable.DB_TABLE_NAME, null, values);
		return insertedId;
	}

	@Override
	public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder, Context context) {
		// TODO: How do I get rid of this ugly check.
		if (spamSqliteHelper == null)
			spamSqliteHelper = new FeatureTableSqliteHelper(context);

		return spamSqliteHelper.getWritableDatabase().query(FeatureTable.DB_TABLE_NAME, projection, selection, selectionArgs, null, null, null);
	}

}