package com.vanhelsing;

class Feature {

	private transient final String feature;
	private transient final TrainingData trainer;

	public Feature(final String feature, final TrainingData trainer) {
		this.feature = feature;
		this.trainer = trainer;
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
		Feature other = (Feature) obj;
		if (feature == null) {
			if (other.feature != null)
				return false;
		} else if (!feature.equals(other.feature))
			return false;
		return true;
	}

	public float conditionalProbability(Classification classification) {
		return trainer.numberOfDocumentsTheFeatureOccurredIn(this, classification)/trainer.numberOfDocumentsInTheCategory(classification);
	}

}
