package com.vanhelsing.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public interface Table {

	public abstract Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder, Context context);

	public abstract long insert(ContentValues values, Context context);

}