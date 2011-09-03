package com.vanhelsing.contentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpamSqliteHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "vanhelsing";

	public SpamSqliteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase) {
		sqliteDatabase.execSQL(createFeatureTable());
	}

	private String createFeatureTable() {
		return String.format("create table %s (_id INTEGER primary key autoincrement," + "%s TEXT)", SpamContentProvider.FeatureTable.DB_TABLE_NAME, SpamContentProvider.FeatureTable.DB_COL_NAME);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, int olderVersion, int newerVersion) {
	}

}
