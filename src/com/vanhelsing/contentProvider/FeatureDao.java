package com.vanhelsing.contentProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.vanhelsing.Feature;
import com.vanhelsing.TrainingData;
import com.vanhelsing.Word;

public class FeatureDao implements IFeatureDao {

	private final Context context;
	private final TrainingData trainingData;

	public FeatureDao(Context context, TrainingData trainingData) {
		this.context = context;
		this.trainingData = trainingData;
		
	}

	@Override
	public boolean persist(Feature feature) {

		final ContentValues contentValues = new ContentValues();

		contentValues.put(FeatureTable.DB_COL_NAME, feature.toString());
		context.getContentResolver().insert(SpamContentProvider.FEATURE_URI, contentValues);

		return true;

	}

	@Override
	public Feature get(String featureName) {
		final Cursor cursor = context.getContentResolver().query(SpamContentProvider.FEATURE_URI, new String[]{FeatureTable.DB_COL_NAME}, null, null, String.format("%s %s", FeatureTable.DB_COL_NAME, "ASC"));
		cursor.moveToFirst();
		final String resultFeatureName = cursor.getString(cursor.getColumnIndex(FeatureTable.DB_COL_NAME));
		if(resultFeatureName == null)
			return null;
		return new Word(resultFeatureName, trainingData);
	}

}
