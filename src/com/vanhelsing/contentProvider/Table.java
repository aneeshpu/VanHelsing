package com.vanhelsing.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public interface Table {
	
	String ID = "_id"; 

	public abstract Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder, Context context);

	public abstract long insert(ContentValues values, Context context);

	public abstract int update(ContentValues values, String selection, Context context);

	public abstract int delete(String selection, Context context);

}