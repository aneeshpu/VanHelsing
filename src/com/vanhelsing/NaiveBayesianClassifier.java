package com.vanhelsing;

import android.util.Log;

public class NaiveBayesianClassifier implements Classifier {

	private transient final TrainingData trainingData;

	public NaiveBayesianClassifier(final TrainingData trainingData) {
		this.trainingData = trainingData;
	}

	public void markAsSpam(final String message) {
		Log.i("vanhelsing", "vanhelsing marking " + message + " as spam");

	}

	public Classification classify(final Document document) {
		/**
		 * Bayes Formula Pr( A | B ) Pr (B | A) * Pr (A) / Pr (B) Pr ( category
		 * | document ) = Pr (document | category ) * Pr(category) / Pr
		 * (document)
		 */

		Probability probabilityOfBadForDocument = probabilityOfDocumentBeingInACategory(document, Classification.BAD);
		Probability probabilityOfGoodForDocument = probabilityOfDocumentBeingInACategory(document, Classification.GOOD);

		return probabilityOfBadForDocument.isGreaterThan(probabilityOfGoodForDocument) ? Classification.BAD : Classification.GOOD;

	}

	private Probability probabilityOfDocumentBeingInACategory(final Document document, final Classification classification) {
		Probability probabilityOfDocumentBeingInACategory = document.conditionalProbability(classification).multiply(probabilityOfCategory(classification));
		System.out.println(String.format("Pr(%s | %s)=%s", document, classification, probabilityOfDocumentBeingInACategory));
		return probabilityOfDocumentBeingInACategory.round();

	}

	private Probability probabilityOfCategory(final Classification classification) {
		float probabilityValue = trainingData.numberOfDocumentsInTheCategory(classification) / trainingData.totalNumberOfDocuments();
		System.out.println(String.format("Pr(%s)=%f", classification, probabilityValue));
		return new Probability(probabilityValue);
	}
}