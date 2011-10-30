package com.vanhelsing.contentProvider;

import com.vanhelsing.Classification;

public interface IClassificationDao {

	public abstract boolean persist(Classification classification, Integer numberOfDocumentForClassification);

	public abstract Category getBad();

}