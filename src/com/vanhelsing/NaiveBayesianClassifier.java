package com.vanhelsing;


import android.util.Log;


public class NaiveBayesianClassifier {

	private static final float ASSUMED_PROBABILITY = 0.5f;
	private static final float WEIGHT = 1;

	public void markAsSpam(String message) {
		Log.i("vanhelsing", "vanhelsing marking " + message + " as spam");

	}

	public Classification classify(Document document) {

//		conditionalProbabilityOfDocumentBeingBad(document);

		return null;
	}
//
//	private float conditionalProbabilityOfDocumentBeingBad(Document document) {
//
//		String[] features = document.split("\\W");
//
//		float conditionalProbabilityOfDocumentBeingBad = 1;
//
//		for (String feature : features)
//			conditionalProbabilityOfDocumentBeingBad *= weightedProbability(conditionalProbabilityOfFeatureBeingBad(feature), numberOfBadDocumentsThatTheFeatureAppearedIn(feature));
//
//		return conditionalProbabilityOfDocumentBeingBad;
//	}
//
//	private float weightedProbability(float conditionalProbabilityOfFeatureBeingBad, int count) {
//		return (WEIGHT * ASSUMED_PROBABILITY + count * conditionalProbabilityOfFeatureBeingBad) / WEIGHT + count;
//	}
//
//	private float conditionalProbabilityOfFeatureBeingBad(String feature) {
//		return numberOfBadDocumentsThatTheFeatureAppearedIn(feature) / totalNumberOfBadDocuments();
//
//	}
//
//	private int totalNumberOfBadDocuments() {
//		return 0;
//	}
//
//	private int numberOfBadDocumentsThatTheFeatureAppearedIn(String feature) {
//		return 0;
//	}

}
