package com.vanhelsing;

interface Feature {

	Probability conditionalProbability(Classification classification);
	
	float numberOfOccurrencesInTheTrainingSet(Classification classification);
}