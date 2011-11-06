package com.vanhelsing.contentProvider;

import com.vanhelsing.Classification;

public class Category {

	private int documentCount;
	private final Classification classification;

	public Category(final Classification classification, final int documentCount) {
		this.classification = classification;
		this.documentCount = documentCount;
	}

	@Override
	public String toString() {
		return classification.toString();
	}

	public int documentCount() {
		return documentCount;
	}

	public int incrementDocumentCount() {
		return documentCount++;
	}

	public Classification classification() {
		return classification;
	}
}
