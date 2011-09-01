package com.vanhelsing;

public class TrainerFactory {

	private static TrainingData trainer;

	public static TrainingData trainingData() {
		if (trainer == null){
			trainer = new TrainingData();
		}
		return trainer;
	}

}
