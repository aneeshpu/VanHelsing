package com.vanhelsing;

import java.util.HashSet;
import java.util.Set;

public class Document {

	private final String contents;

	public Document(String contents) {
		this.contents = contents;
	}

	public Set<String> uniqueFeatures() {
		return toLower(contents.split("\\W"));
	}

	private Set<String> toLower(String[] split) {

		Set<String> features = new HashSet<String>();
		for (String feature : split)
			features.add(feature.toLowerCase());
		
		return features;
	}

}
