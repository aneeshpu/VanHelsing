package com.vanhelsing;

public class ClassifierFactory {

	public static Classifier makeClassifier() {
		return new NaiveBayesianClassifier(TrainerFactory.trainingData()); 
	}

}
