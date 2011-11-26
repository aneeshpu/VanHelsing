package com.vanhelsing.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.vanhelsing.Classification;
import com.vanhelsing.Feature;
import com.vanhelsing.exception.VanHelsingException;

public class FeatureClassificationDao implements IFeatureCategoryDao {

	private final IClassificationDao classificationDao;
	private final Context context;

	public FeatureClassificationDao(IClassificationDao classificationDao, Context context) {
		this.classificationDao = classificationDao;
		this.context = context;
	}

	@Override
	public void persist(Feature feature, Classification classification, Integer featureCount) {
		Category category = getCategory(classification);
		if (category == null) {
			throw new VanHelsingException(String.format("Could not find category %s", classification));
		}

		final ContentValues contentValues = new ContentValues();
		contentValues.put(ClassificationFeatureCountTable.DB_COL_FEATURE_ID, feature.id());
		contentValues.put(ClassificationFeatureCountTable.DB_COL_CLASSIFICATION_ID, category.id());
		contentValues.put(ClassificationFeatureCountTable.DB_COL_FEATURE_COUNT, featureCount);

		context.getContentResolver().insert(SpamContentProvider.CLASSIFICATION_FEATURE_COUNT_URI, contentValues);

	}

	private Category getCategory(Classification classification) {
		final Category category = classificationDao.get(classification);
		return category;
	}

	@Override
	public Feature get(Feature feature) {

		final Cursor cursor = context.getContentResolver().query(SpamContentProvider.CLASSIFICATION_FEATURE_COUNT_URI,
				new String[] { ClassificationFeatureCountTable.DB_COL_CLASSIFICATION_ID, ClassificationFeatureCountTable.DB_COL_FEATURE_COUNT },
				String.format("%s = %d", ClassificationFeatureCountTable.DB_COL_FEATURE_ID, feature.id()), null, null);
		
		cursor.moveToFirst();
		do {

			final int classificationId = cursor.getInt(cursor.getColumnIndex(ClassificationFeatureCountTable.DB_COL_CLASSIFICATION_ID));
			final int featureCount = cursor.getInt(cursor.getColumnIndex(ClassificationFeatureCountTable.DB_COL_FEATURE_COUNT));
			
			final Category category = classificationDao.get(classificationId);
			if(category == null)
				throw new VanHelsingException(String.format("Could not lookup category by id %d", classificationId));

			Log.d("vanhelsing", String.format("Retrieved %s for id %d", category, classificationId));

			feature.add(category, featureCount);
		} while (cursor.moveToNext());
		return feature;
	}
}