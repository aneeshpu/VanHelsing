package com.vanhelsing;

import java.util.ArrayList;
import java.util.List;

class Document {

	private final String contents;

	public Document(String contents) {
		this.contents = contents;
	}

	public List<String> features() {
		return toLower(contents.split("\\W"));
	}

	private List<String> toLower(String[] split) {

		List<String> features = new ArrayList<String>();
		for (String feature : split)
			features.add(feature.toLowerCase());
		
		return features;
	}

}
