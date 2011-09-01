package com.vanhelsing;

class Word implements Feature{

	private static final float ASSUMED_PROBABILITY = 0.5f;
	private static final float WEIGHT = 1;
	private transient final String feature;
	private transient final TrainingData trainedData;

	public Word(final String feature, final TrainingData trainer) {
		this.feature = feature;
		this.trainedData = trainer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feature == null) ? 0 : feature.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (feature == null) {
			if (other.feature != null)
				return false;
		} else if (!feature.equals(other.feature))
			return false;
		return true;
	}

	public Probability conditionalProbability(Classification classification) {
		Probability probability = new Probability(trainedData.numberOfDocumentsTheFeatureOccurredIn(this, classification)/trainedData.numberOfDocumentsInTheCategory(classification));
		
		Probability weightedProbability = weightedProbability(probability, classification);
		
		System.out.println(String.format("The weighted probability of the feature '%s' in category %s is %s", this, classification, weightedProbability));
		return weightedProbability;
		
	}

	private Probability weightedProbability(Probability probability, Classification classification) {
		float numberOfOccurrencesInTheTrainingSet = numberOfOccurrencesInTheTrainingSet(classification);
		return new Probability((float)(ASSUMED_PROBABILITY * WEIGHT + probability.weightedProbability(numberOfOccurrencesInTheTrainingSet)) / (WEIGHT + numberOfOccurrencesInTheTrainingSet));
	}

	@Override
	public float numberOfOccurrencesInTheTrainingSet(Classification classification) {
		return trainedData.numberOfDocumentsTheFeatureOccurredIn(this);
	}
	
	@Override
	public String toString(){
		return feature;
	}

}