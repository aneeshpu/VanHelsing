package com.vanhelsing.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public final class ClassificationTable implements Table {

	public static final String DB_TABLE_NAME = "classifications";
	public static final String DB_COL_NAME = "name";
	public static final String DB_COL_DOCUMENT_COUNT = "document_count";
	public static final String DB_COL_ID = "_id";

	public static final Uri URI = Uri.parse(String.format("content://%s/classification", SpamContentProvider.AUTHORITY));
	private VanHelsingSqliteHelper spamSqliteHelper;

	public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder, Context context) {
		return sqliteHelper(context).getWritableDatabase().query(ClassificationTable.DB_TABLE_NAME, projection, selection, selectionArgs, null, null, null);
	}

	public long insert(ContentValues values, Context context) {
		return sqliteHelper(context).getWritableDatabase().insert(ClassificationTable.DB_TABLE_NAME, null, values);
	}

	private VanHelsingSqliteHelper sqliteHelper(Context context) {
		if (spamSqliteHelper == null)
			spamSqliteHelper = new VanHelsingSqliteHelper(context);
		return spamSqliteHelper;
	}

	@Override
	public int update(ContentValues values, String selection, Context context) {
		return sqliteHelper(context).getWritableDatabase().update(DB_TABLE_NAME, values, selection, null);
	}

	@Override
	public int delete(String selection, Context context) {
		return sqliteHelper(context).getWritableDatabase().delete(DB_TABLE_NAME, selection, null);
	}
}