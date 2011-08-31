package com.vanhelsing;

import java.util.HashSet;
import java.util.Set;

public class Document {

	private final String contents;
	private FeatureFactory featureFactory;
	private final TrainingData trainer;


	public Document(String contents, FeatureFactory featureFactory, TrainingData trainer) {
		this.contents = contents;
		this.featureFactory = featureFactory;
		this.trainer = trainer;
	}

	public Set<Feature> uniqueFeatures() {
		Set<String> uniqueFeatures = toLower(contents.split("\\W"));

		return featureFactory.makeFeatures(uniqueFeatures, trainer);
	}

	private Set<String> toLower(String[] split) {

		Set<String> features = new HashSet<String>();
		for (String feature : split)
			features.add(feature.toLowerCase());

		return features;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		return true;
	}

	public float conditionalProbability(Classification classification) {
		float conditionalProbabilityOfDocument = 1f;
		for (Feature feature : uniqueFeatures())
			conditionalProbabilityOfDocument *= feature.conditionalProbability(classification);
		
		return (float)Math.round(conditionalProbabilityOfDocument * 10000)/10000;
	}

}