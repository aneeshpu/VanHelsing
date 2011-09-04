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

	private static class ClassificationFeatureCountTableSqliteHelper extends SQLiteOpenHelper {

		private static final int DATABASE_VERSION = 1;
		private static final String DATABASE_NAME = "vanhelsing";

		public ClassificationFeatureCountTableSqliteHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase sqliteDatabase) {
			sqliteDatabase.execSQL(createFeatureTable());
		}

		private String createFeatureTable() {
			return String.format("create table %s (_id INTEGER primary key autoincrement," + "%s INTEGER, %s INTEGER, %s INTEGER)", ClassificationFeatureCountTable.DB_TABLE_NAME,
					ClassificationFeatureCountTable.DB_COL_FEATURE_ID, ClassificationFeatureCountTable.DB_COL_CLASSIFICATION_ID, ClassificationFeatureCountTable.DB_COL_FEATURE_COUNT);
		}

		@Override
		public void onUpgrade(SQLiteDatabase sqliteDatabase, int olderVersion, int newerVersion) {
		}

	}

	private ClassificationFeatureCountTableSqliteHelper sqliteHelper;

	@Override
	public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder, Context context) {
		return sqliteHelper(context).getWritableDatabase().query(DB_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
	}

	@Override
	public long insert(ContentValues values, Context context) {
		return sqliteHelper(context).getWritableDatabase().insert(DB_TABLE_NAME, null, values);
	}

	private ClassificationFeatureCountTableSqliteHelper sqliteHelper(Context context) {
		if (sqliteHelper == null)
			sqliteHelper = new ClassificationFeatureCountTableSqliteHelper(context);
		return sqliteHelper;
	}

}
