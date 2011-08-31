package com.vanhelsing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.vanhelsing.collections.DefaultMap;

final class TrainingData {

	private transient final DefaultMap<String, Map<Classification, Integer>> featureCount = new DefaultMap<String, Map<Classification, Integer>>(new HashMap<String, Map<Classification, Integer>>(),
			DefaultMap.defaultMapInitializer());

	private transient final Map<Classification, Integer> documentClassificationCount = new DefaultMap<Classification, Integer>(new HashMap<Classification, Integer>(), DefaultMap.integerInitialization());

	protected TrainingData train(final Document document, final Classification classification) {
		incrementDocumentCount(document, classification);

		final Set<Word> uniqueFeatures = document.uniqueFeatures();

		for (Feature feature : uniqueFeatures) {
			incrementFeatureCount(feature, classification);
		}

		return this;
	}

	private void incrementDocumentCount(final Document document, final Classification classification) {

		Integer numberOfDocumentForClassification = documentClassificationCount.get(classification);
		documentClassificationCount.put(classification, numberOfDocumentForClassification + 1);
	}

	private void incrementFeatureCount(final Feature feature, final Classification classification) {
		Map<Classification, Integer> classificationFeatureMap = featureCount.get(feature);
		Integer count = classificationFeatureMap.get(classification);
		classificationFeatureMap.put(classification, count + 1);
	}

	float numberOfDocumentsInTheCategory(final Classification classification) {
		return documentClassificationCount.get(classification);
	}

	float numberOfDocumentsTheFeatureOccurredIn(final Feature feature, final Classification classification) {
		return featureCount.get(feature).get(classification);
	}
}