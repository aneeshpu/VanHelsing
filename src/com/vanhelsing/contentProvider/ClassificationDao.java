package com.vanhelsing.contentProvider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.vanhelsing.Classification;
import com.vanhelsing.exception.VanHelsingException;

public class ClassificationDao implements IClassificationDao {

	private Context context;

	public ClassificationDao(Context context) {
		this.context = context;
	}

	@Override
	public Category getBad() {
		return get(Classification.BAD);
	}

	// @Override
	private long persist(Classification classification, Integer numberOfDocumentForClassification) {
		final ContentValues contentValues = new ContentValues();

		contentValues.put(ClassificationTable.DB_COL_NAME, classification.toString());
		contentValues.put(ClassificationTable.DB_COL_DOCUMENT_COUNT, numberOfDocumentForClassification);

		final Uri uri = context.getContentResolver().insert(SpamContentProvider.CLASSIFICATION_URI, contentValues);
		
		final long insertedId = ContentUris.parseId(uri);

		return insertedId;
	}

	@Override
	public Category get(Classification classification) {
		final Cursor cursor = context.getContentResolver().query(SpamContentProvider.CLASSIFICATION_URI, new String[] { ClassificationTable.DB_COL_ID,ClassificationTable.DB_COL_NAME, ClassificationTable.DB_COL_DOCUMENT_COUNT },
				String.format("%s = '%s'", ClassificationTable.DB_COL_NAME, Classification.BAD.toString()), null, null);

		if(cursor.getCount() == 0)
			return null;
		
		cursor.moveToFirst();
		
		final Category category = new Category(classification, cursor.getInt(cursor.getColumnIndex(ClassificationTable.DB_COL_DOCUMENT_COUNT)));
		category.setId(cursor.getInt(cursor.getColumnIndex(ClassificationTable.DB_COL_ID)));
		cursor.close();
		return category;

	}

	@Override
	public int delete(Category category) {
		final int deletedRows = context.getContentResolver().delete(SpamContentProvider.CLASSIFICATION_URI, String.format("%s = '%s'", ClassificationTable.DB_COL_NAME, category), null);
		return deletedRows;
	}

	@Override
	public Category persist(Category category) {

		final long insertedId = persist(category.classification(), category.documentCount());
		if (insertedId == -1)
			return null;
		
		category.setId(insertedId);

		return category;
	}

	@Override
	public Category get(long classificationId) {
		final Cursor cursor = context.getContentResolver().query(SpamContentProvider.CLASSIFICATION_URI, new String[] { ClassificationTable.DB_COL_NAME, ClassificationTable.DB_COL_DOCUMENT_COUNT },
				String.format("%s = %d", ClassificationTable.DB_COL_ID, classificationId), null, null);
		
		if(cursor.getCount() == 0)
			throw new VanHelsingException(String.format("Could nto find category with id %d", classificationId));
		
		cursor.moveToFirst();
		final Classification classification = Classification.valueOf(cursor.getString(cursor.getColumnIndex(ClassificationTable.DB_COL_NAME)));
		final int documentCount = cursor.getInt(cursor.getColumnIndex(ClassificationTable.DB_COL_DOCUMENT_COUNT));
		return new Category(classification, documentCount);
	}
}