package com.vanhelsing;

public class DocumentFactory {

	public Document makeDocument(String string, TrainingData trainer) {
		return new Document(string, new FeatureFactory(), trainer);
	}

}
