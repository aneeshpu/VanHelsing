package com.vanhelsing.contentProvider;

import com.vanhelsing.Classification;

public class Category {

	private final int documentCount;

	public Category(final Classification classification, final int documentCount) {
		this.documentCount = documentCount;
	}

	public int documentCount() {
		return documentCount;
	}

}
