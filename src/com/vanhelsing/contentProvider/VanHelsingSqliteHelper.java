package com.vanhelsing.contentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VanHelsingSqliteHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "vanhelsing";

	public VanHelsingSqliteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase) {
		sqliteDatabase.execSQL(classificationTable());
		sqliteDatabase.execSQL(createFeatureTable());
		sqliteDatabase.execSQL(createFeatureCountTable());
	}

	private String createFeatureTable() {
		return String.format("create table %s (_id INTEGER primary key autoincrement," + "%s TEXT)", FeatureTable.DB_TABLE_NAME, FeatureTable.DB_COL_NAME);
	}
	
	private String createFeatureCountTable() {
		return String.format("create table %s (_id INTEGER primary key autoincrement," + "%s INTEGER, %s INTEGER, %s INTEGER)", ClassificationFeatureCountTable.DB_TABLE_NAME,
				ClassificationFeatureCountTable.DB_COL_FEATURE_ID, ClassificationFeatureCountTable.DB_COL_CLASSIFICATION_ID, ClassificationFeatureCountTable.DB_COL_FEATURE_COUNT);
	}	
	
	private String classificationTable() {
		return String.format("create table %s (_id INTEGER primary key autoincrement," + "%s TEXT, %s INTEGER)", ClassificationTable.DB_TABLE_NAME, ClassificationTable.DB_COL_NAME,
				ClassificationTable.DB_COL_DOCUMENT_COUNT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, int olderVersion, int newerVersion) {
	}

}
