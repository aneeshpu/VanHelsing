package com.vanhelsing;

import java.util.Map;

import com.vanhelsing.contentProvider.Category;
import com.vanhelsing.contentProvider.Entity;

public interface Feature extends Entity{

	Probability conditionalProbability(Classification classification);
	
	float numberOfOccurrencesInTheTrainingSet(Classification classification);

	//TODO: Get rid of this . This is ugly. This was introduced because the query requires the value of the column which is the featureName. 
	//Depending on the toString() would have been really bad as it would cause the query to break when the String representation changes. 
	String name();

	Feature incrementOccurrenceInClassification(Classification classification);

	//TODO:Get an ORM to persist the data. This method is only exposed so that the data can be persisted to the database.
	Map<Classification, Integer> getClassificationCountMap();

	Feature add(Category category, Integer classificationCount);
}