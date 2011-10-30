package com.vanhelsing;

import android.content.Context;

import com.vanhelsing.contentProvider.ClassificationDao;
import com.vanhelsing.contentProvider.FeatureDao;

public class TrainerFactory {

	private static TrainingData trainer;

	public static TrainingData trainingData(Context context) {
		if (trainer == null){
			trainer = new TrainingData(new FeatureDao(), new ClassificationDao(context));
		}
		return trainer;
	}

}
