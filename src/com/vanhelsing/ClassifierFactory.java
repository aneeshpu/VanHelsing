package com.vanhelsing;

import android.content.Context;

public class ClassifierFactory {

	public static Classifier makeClassifier(Context context) {
		return new NaiveBayesianClassifier(TrainerFactory.trainingData(context)); 
	}

}
