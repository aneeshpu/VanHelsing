package com.vanhelsing.contentProvider;

import android.content.Context;

import com.vanhelsing.TrainingData;

public class TrainingDataDao {

	private final Context context;

	public TrainingDataDao(Context context) {
		this.context = context;
	}

	public void saveOrUpdate(TrainingData trainingData) {
//		this.context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder)
		
		//TODO: Doing some ugly business here. The domain model is not correct. Needs refactoring.
	}

}
