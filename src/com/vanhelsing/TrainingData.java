package com.vanhelsing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.vanhelsing.collections.DefaultFunctions;
import com.vanhelsing.collections.DefaultMap;
import com.vanhelsing.contentProvider.Category;
import com.vanhelsing.contentProvider.FeatureDao;
import com.vanhelsing.contentProvider.IClassificationDao;
import com.vanhelsing.contentProvider.IFeatureDao;

/**
 * The existence of this class is an abomination. I was too influenced by the
 * code in Collective Intelligence to see where I was going.
 * 
 * I need to get this working before I can refactor and remove this class and
 * evolve a good domain model. I will do it soon. But till then this class has
 * to live on
 * 
 * @author aneeshpu
 * 
 */
public final class TrainingData {

	private transient final DefaultMap<String, Map<Classification, Integer>> featureCount = new DefaultMap<String, Map<Classification, Integer>>(
			new HashMap<String, Map<Classification, Integer>>(), DefaultFunctions.defaultMapInitializer());

	private transient final Map<Classification, Integer> documentClassificationCount = new DefaultMap<Classification, Integer>(
			new HashMap<Classification, Integer>(), DefaultFunctions.integerInitialization());

	private final IFeatureDao featureDao;

	private final IClassificationDao classificationDao;

	public TrainingData(IFeatureDao featureDao, IClassificationDao classificationDao) {
		this.featureDao = featureDao;
		this.classificationDao = classificationDao;
	}

	protected TrainingData train(final Document document, final Classification classification) {
		incrementDocumentCount(classification);

		final Set<Feature> uniqueFeatures = document.uniqueFeatures();

		for (Feature feature : uniqueFeatures) {
			incrementFeatureCount(feature, classification);
		}

		return this;
	}

	private void incrementDocumentCount(final Classification classification) {

		Integer numberOfDocumentForClassification = documentClassificationCount.get(classification);
		documentClassificationCount.put(classification, numberOfDocumentForClassification + 1);

		// getClassificationDao().persist(classification,
		// numberOfDocumentForClassification);
		final Category category = getClassificationDao().get(classification);
		category.incrementDocumentCount();
		getClassificationDao().persist(category);
	}

	private IClassificationDao getClassificationDao() {
		return classificationDao;
	}

	private void incrementFeatureCount(final Feature feature, final Classification classification) {
		
		getFeatureDao().get(feature);
		
		feature.incrementOccurrenceInClassification(classification);
		
		getFeatureDao().persist(feature);
		
		Map<Classification, Integer> classificationCountMap = featureCount.get(feature);

		Integer count = classificationCountMap.get(classification);
		classificationCountMap.put(classification, count + 1);

	}

	private IFeatureDao getFeatureDao() {
		return featureDao;
	}

	protected float numberOfDocumentsInTheCategory(final Classification classification) {
		return documentClassificationCount.get(classification);
	}

	protected float numberOfDocumentsTheFeatureOccurredIn(final Feature feature,
			final Classification classification) {
		return featureCount.get(feature).get(classification);
	}

	public int numberOfDocumentsTheFeatureOccurredIn(Word feature) {

		int totalCount = 0;
		for (Classification classification : Classification.values()) {
			totalCount += featureCount.get(feature).get(classification);
		}
		return totalCount;
	}

	public int totalNumberOfDocuments() {
		int totalNumberOfDocuments = 0;
		for (Classification classification : documentClassificationCount.keySet()) {
			totalNumberOfDocuments += documentClassificationCount.get(classification);
		}
		return totalNumberOfDocuments;
	}
}