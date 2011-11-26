package com.vanhelsing.contentProvider;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.vanhelsing.Classification;
import com.vanhelsing.Feature;
import com.vanhelsing.TrainingData;
import com.vanhelsing.Word;
import com.vanhelsing.exception.VanHelsingException;

public class FeatureDao implements IFeatureDao {

	private final Context context;
	private final TrainingData trainingData;
	private final IFeatureCategoryDao featureCategoryDao;

	public FeatureDao(TrainingData trainingData, Context context, IFeatureCategoryDao classificationDao) {
		this.context = context;
		this.trainingData = trainingData;
		this.featureCategoryDao = classificationDao;
	}

	@Override
	public boolean persist(Feature feature) {

		final ContentValues contentValues = new ContentValues();

		contentValues.put(FeatureTable.DB_COL_NAME, feature.toString());
		final Uri uriWithInsertedId = context.getContentResolver().insert(SpamContentProvider.FEATURE_URI, contentValues);

		final long insertedFeatureId = ContentUris.parseId(uriWithInsertedId);
		feature.setId(insertedFeatureId);

		// persist the classification count
		persistTheClassificationCount(feature);

		return true;

	}

	private void persistTheClassificationCount(Feature feature) {

		final Map<Classification, Integer> classificationCountMap = feature.getClassificationCountMap();

		final Set<Entry<Classification, Integer>> entrySet = classificationCountMap.entrySet();
		for (Entry<Classification, Integer> entry : entrySet) {
			featureCategoryDao.persist(feature, entry.getKey(), entry.getValue());
		}
	}

	@Override
	public Feature get(Feature feature) {
		final Cursor cursor = context.getContentResolver().query(SpamContentProvider.FEATURE_URI, new String[] { FeatureTable.DB_COL_NAME, FeatureTable.ID },
				String.format("%s = '%s'", FeatureTable.DB_COL_NAME, feature.name()), null, String.format("%s ASC", FeatureTable.DB_COL_NAME));
		if(cursor.getCount() > 1)
			throw new VanHelsingException(String.format("Found more than one %s", feature));
		cursor.moveToFirst();

		final String resultFeatureName = cursor.getString(cursor.getColumnIndex(FeatureTable.DB_COL_NAME));
		final int id = cursor.getInt(cursor.getColumnIndex(FeatureTable.ID));

		feature.setId(id);

		cursor.close();

		if (resultFeatureName == null)
			return null;

		// get the classifications
		return getClassifications(feature);

	}

	private Feature getClassifications(Feature feature) {
		return featureCategoryDao.get(feature);
	}

	@Override
	public int delete(Feature persistedFeature) {
		final int deletedRows = context.getContentResolver().delete(SpamContentProvider.FEATURE_URI, String.format("%s = %d", FeatureTable.ID, persistedFeature.id()), null);
		return deletedRows;
	}
}