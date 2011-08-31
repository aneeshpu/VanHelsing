package com.vanhelsing;

public class FictitiousWord implements Feature{

	private static final float ASSUMED_PROBABILITY = 0.5f;
	private static final int ASSUMED_WEIGHT = 1;
	protected final Feature word;

	public FictitiousWord(final Feature word) {
		this.word = word;
	}

	@Override
	public Probability conditionalProbability(final Classification classification) {
		float numberOfOccurrencesInTheTrainingSet = word.numberOfOccurrencesInTheTrainingSet(classification);
		float weightedProbability = word.conditionalProbability(classification).weightedProbability(numberOfOccurrencesInTheTrainingSet);
		return round((ASSUMED_PROBABILITY * ASSUMED_WEIGHT + weightedProbability) / (ASSUMED_WEIGHT + numberOfOccurrencesInTheTrainingSet));
	}

	private Probability round(float probability) {
		return new Probability(probability);
	}

	@Override
	public float numberOfOccurrencesInTheTrainingSet(final Classification classification) {
		// TODO Auto-generated method stub
		return 0;
	}

}
