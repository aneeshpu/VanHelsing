package com.vanhelsing;

public class DocumentFactory {

	public Document makeDocument(String string) {
		return new Document(string, new FeatureFactory());
	}

}
