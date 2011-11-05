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

	private FeatureTableSqliteHelper sqliteHelper;

	@Override
	public long insert(ContentValues values, Context context) {
		long insertedId = sqliteHelper(context).getWritableDatabase().insert(FeatureTable.DB_TABLE_NAME, null, values);
		return insertedId;
	}

	@Override
	public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder, Context context) {
		return sqliteHelper(context).getWritableDatabase().query(FeatureTable.DB_TABLE_NAME, projection, selection, selectionArgs, null, null, null);
	}

	private SQLiteOpenHelper sqliteHelper(Context context) {
		if (sqliteHelper == null)
			sqliteHelper = new FeatureTableSqliteHelper(context);
		return sqliteHelper;
	}

	@Override
	public int update(ContentValues values, String selection, Context context) {
		return 0;
	}

	@Override
	public int delete(String whereClause, Context context) {
		return sqliteHelper(context).getWritableDatabase().delete(DB_TABLE_NAME, whereClause, null);
	}

}