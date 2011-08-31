package com.vanhelsing;

public class TrainerFactory {

	private static TrainingData trainer;

	public static TrainingData getTrainer() {
		if (trainer == null){
			trainer = new TrainingData();
		}
		return trainer;
	}

}
