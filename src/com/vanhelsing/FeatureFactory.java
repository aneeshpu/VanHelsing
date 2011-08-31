package com.vanhelsing;

import java.util.HashSet;
import java.util.Set;

public class FeatureFactory {

	public Set<Word> makeFeatures(final Set<String> features, final TrainingData trainer) {
		final Set<Word> featureSet = new HashSet<Word>();
		for (String feature: features){
			featureSet.add(new Word(feature, trainer));
		}
		
		return featureSet;
	}

}
