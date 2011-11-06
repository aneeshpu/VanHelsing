package com.vanhelsing;

public interface Feature {

	Probability conditionalProbability(Classification classification);
	
	float numberOfOccurrencesInTheTrainingSet(Classification classification);

	//TODO: Get rid of this . This is ugly. This was introduced because the query requires the value of the column which is the featureName. 
	//Depending on the toString() would have been really bad as it would cause the query to break when the String representation changes. 
	String featureName();
}