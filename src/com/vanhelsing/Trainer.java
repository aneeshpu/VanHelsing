package com.vanhelsing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class Trainer {

	private final Map<String, Map<Classification, Integer>> featureCount = new HashMap<String, Map<Classification, Integer>>();
	private final Map<Classification, Integer> documentClassificationCount = new HashMap<Classification, Integer>();

	public Trainer train(Document document, Classification classification) {
		incrementDocumentCount(document, classification);

		Set<String> uniqueFeatures = document.uniqueFeatures();

		for (String feature : uniqueFeatures) {
			incrementFeatureCount(feature, classification);
		}

		return this;
	}

	private void incrementDocumentCount(Document document, Classification classification) {

		Integer numberOfDocumentForClassification = documentClassificationCount.get(classification);
		if (numberOfDocumentForClassification == null)
			numberOfDocumentForClassification = 0;

		documentClassificationCount.put(classification, numberOfDocumentForClassification + 1);

	}

	private void incrementFeatureCount(String feature, Classification classification) {
		Map<Classification, Integer> classificationFeatureMap = featureCount.get(feature);

		if (classificationFeatureMap == null) {
			HashMap<Classification, Integer> hashMap = new HashMap<Classification, Integer>();
			hashMap.put(classification, 1);
			featureCount.put(feature, hashMap);
			return;
		}

		Integer count = classificationFeatureMap.get(classification);
		if(count == null)
			count = 0;
		
		classificationFeatureMap.put(classification, count + 1);
	}

	public float conditionalProbability(String feature, Classification classification) {

		return numberOfDocumentsTheFeatureOccurredIn(feature, classification) / numberOfDocumentsInTheCategory(classification);
	}

	private float numberOfDocumentsInTheCategory(Classification classification) {
		Integer numberOfDocumentsInTheCategory = documentClassificationCount.get(classification);
		if (numberOfDocumentsInTheCategory == null)
			return 1;

		return numberOfDocumentsInTheCategory;
	}

	private float numberOfDocumentsTheFeatureOccurredIn(String feature, Classification classification) {
		Map<Classification, Integer> numberOfDocumentsTheFeatureOccurredIn = featureCount.get(feature);
		if (numberOfDocumentsTheFeatureOccurredIn == null)
			return 0;

		return numberOfDocumentsTheFeatureOccurredIn.get(classification);
	}

}
