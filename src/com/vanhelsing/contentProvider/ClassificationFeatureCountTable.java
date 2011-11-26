package com.vanhelsing.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClassificationFeatureCountTable implements Table {

	public static final String URI = String.format("content://%s/classificationfeaturecount", SpamContentProvider.AUTHORITY);
	public static final String DB_TABLE_NAME = "classification_feature_count";
	
	public static final String DB_COL_CLASSIFICATION_ID = "classification_id";
	public static final String DB_COL_FEATURE_ID = "feature_id";
	public static final String DB_COL_FEATURE_COUNT = "feature_count";

	private VanHelsingSqliteHelper sqliteHelper;

	@Override
	public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder, Context context) {
		return sqliteHelper(context).getWritableDatabase().query(DB_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
	}

	@Override
	public long insert(ContentValues values, Context context) {
		return sqliteHelper(context).getWritableDatabase().insert(DB_TABLE_NAME, null, values);
	}

	private VanHelsingSqliteHelper sqliteHelper(Context context) {
		if (sqliteHelper == null)
			sqliteHelper = new VanHelsingSqliteHelper(context);
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
