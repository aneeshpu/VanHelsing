package com.vanhelsing;

import java.util.HashSet;
import java.util.Set;

public class FeatureFactory {

	public Set<Feature> makeFeatures(Set<String> features) {
		Set<Feature> featureSet = new HashSet<Feature>();
		for (String feature: features){
			featureSet.add(new Feature(feature));
		}
		
		return featureSet;
	}

}