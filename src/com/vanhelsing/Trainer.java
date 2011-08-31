package com.vanhelsing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.vanhelsing.collections.DefaultMap;

final class Trainer {

	private final DefaultMap<String, Map<Classification, Integer>> featureCount = new DefaultMap<String, Map<Classification, Integer>>(new HashMap<String, Map<Classification, Integer>>(),
			DefaultMap.DefaultMapInitializer());

	private final Map<Classification, Integer> documentClassificationCount = new DefaultMap<Classification, Integer>(new HashMap<Classification, Integer>(), DefaultMap.integerInitialization());

	public Trainer train(final Document document, final Classification classification) {
		incrementDocumentCount(document, classification);

		Set<Feature> uniqueFeatures = document.uniqueFeatures();

		for (Feature feature : uniqueFeatures) {
			incrementFeatureCount(feature, classification);
		}

		return this;
	}

	private void incrementDocumentCount(Document document, Classification classification) {

		Integer numberOfDocumentForClassification = documentClassificationCount.get(classification);
		documentClassificationCount.put(classification, numberOfDocumentForClassification + 1);
	}

	private void incrementFeatureCount(Feature feature, Classification classification) {
		Map<Classification, Integer> classificationFeatureMap = featureCount.get(feature);
		Integer count = classificationFeatureMap.get(classification);
		classificationFeatureMap.put(classification, count + 1);
	}

	public float conditionalProbability(Feature feature, Classification classification) {
		return numberOfDocumentsTheFeatureOccurredIn(feature, classification) / numberOfDocumentsInTheCategory(classification);
	}

	private float numberOfDocumentsInTheCategory(Classification classification) {
		return documentClassificationCount.get(classification);
	}

	private float numberOfDocumentsTheFeatureOccurredIn(Feature feature, Classification classification) {
		return featureCount.get(feature).get(classification);
	}

	public float conditionalProbability(Document document, Classification classification) {
		float conditionalProbabilityOfDocument = 1f;
		for (Feature feature : document.uniqueFeatures())
			conditionalProbabilityOfDocument *= conditionalProbability(feature, classification);
		
		return (float)Math.round(conditionalProbabilityOfDocument * 10000)/10000;
	}
}