package com.vanhelsing;

public class Probability {

	private final float probabilityValue;
	private static final int DECIMAL = 1000;

	public Probability(final float probabilityValue) {
		this.probabilityValue = probabilityValue;

		if (probabilityValue < 0 || probabilityValue > 1)
			throw new InvalidProbabilityException(String.format("Probability should lie between 0 and 1 both included. A value of %f is invalid", probabilityValue));
		
	}

	public Probability multiply(Probability probability) {
		float product = probabilityValue * probability.probabilityValue;
		return new Probability(product);
	}

	public Probability round() {
		return new Probability((float)Math.round(probabilityValue * DECIMAL) / DECIMAL);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(probabilityValue);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Probability other = (Probability) obj;
		if (Float.floatToIntBits(probabilityValue) != Float.floatToIntBits(other.probabilityValue))
			return false;
		return true;
	}

	public float weightedProbability(float weight) {
		return probabilityValue * weight;
	}

	@Override
	public String toString(){
		return String.valueOf(probabilityValue);
	}

	public Boolean isGreaterThan(Probability other) {
		return probabilityValue > other.probabilityValue;
	}
}
