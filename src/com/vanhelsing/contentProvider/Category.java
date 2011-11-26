package com.vanhelsing.contentProvider;

import com.vanhelsing.Classification;

public class Category implements Entity{

	private int documentCount;
	private final Classification classification;
	private long id;

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

	public long id() {
		return id;
	}

	public void setId(long insertedId) {
		id = insertedId;
	}
	
	
}
