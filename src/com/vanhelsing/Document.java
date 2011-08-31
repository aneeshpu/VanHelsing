package com.vanhelsing;

import java.util.HashSet;
import java.util.Set;

public class Document {

	private transient final String contents;
	private transient final FeatureFactory featureFactory;
	private transient final TrainingData trainer;

	public Document(final String contents, final FeatureFactory featureFactory, final TrainingData trainer) {
		this.contents = contents;
		this.featureFactory = featureFactory;
		this.trainer = trainer;
	}

	public Set<Word> uniqueFeatures() {
		Set<String> uniqueFeatures = toLower(contents.split("\\W"));

		return featureFactory.makeFeatures(uniqueFeatures, trainer);
	}

	private Set<String> toLower(final String[] split) {

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
	public boolean equals(final Object obj) {
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

	public Probability conditionalProbability(final Classification classification) {
		Probability conditionalProbabilityOfDocument = new Probability(1);
		for (final Feature feature : uniqueFeatures())
			conditionalProbabilityOfDocument = conditionalProbabilityOfDocument.multiply(feature.conditionalProbability(classification));

		return conditionalProbabilityOfDocument;
	}

}