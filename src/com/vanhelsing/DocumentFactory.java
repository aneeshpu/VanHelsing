package com.vanhelsing;

public class DocumentFactory {

	public Document makeDocument(final String string, final TrainingData trainer) {
		return new Document(string, new FeatureFactory(), trainer);
	}

}
