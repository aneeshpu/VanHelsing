package com.vanhelsing;

public interface Feature {

	Probability conditionalProbability(Classification classification);
	
	float numberOfOccurrencesInTheTrainingSet(Classification classification);
}