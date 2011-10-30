package com.vanhelsing.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.vanhelsing.Classification;

public class ClassificationDao implements IClassificationDao {

	private Context context;

	public ClassificationDao(Context context) {
		this.context = context;
	}

	@Override
	public Category getBad() {
		final Cursor cursor = context.getContentResolver().query(SpamContentProvider.CLASSIFICATION_URI, new String[] { ClassificationTable.DB_COL_NAME, ClassificationTable.DB_COL_DOCUMENT_COUNT },
				String.format("%s = '%s'", ClassificationTable.DB_COL_NAME, Classification.BAD.toString()), null, null);

		cursor.moveToFirst();
		return new Category(Classification.BAD, cursor.getInt(cursor.getColumnIndex(ClassificationTable.DB_COL_DOCUMENT_COUNT)));
		
	}

	@Override
	public boolean persist(Classification classification, Integer numberOfDocumentForClassification) {
		final ContentValues contentValues = new ContentValues();

		contentValues.put(ClassificationTable.DB_COL_NAME, Classification.BAD.toString());
		contentValues.put(ClassificationTable.DB_COL_DOCUMENT_COUNT, numberOfDocumentForClassification);

		context.getContentResolver().insert(SpamContentProvider.CLASSIFICATION_URI, contentValues);

		return true;
	}

}
