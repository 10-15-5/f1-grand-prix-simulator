package com.f1sim.models;

public class Car {
    private final String model;
    private final int speedRating;
    private final int reliabilityRating;

    public Car(String model, int speedRating, int reliabilityRating) {
        if (model == null || model.isBlank()) throw new IllegalArgumentException("Model cannot be blank.");
        validateRating(speedRating, "Speed");
        validateRating(reliabilityRating, "Reliability");
        this.model = model;
        this.speedRating = speedRating;
        this.reliabilityRating = reliabilityRating;
    }

    private static void validateRating(int rating, String label) {
        if (rating < 1 || rating > 100) {
            throw new IllegalArgumentException(label + " rating must be between 1 and 100.");
        }
    }

    public String getModel() { return model; }
    public int getSpeedRating() { return speedRating; }
    public int getReliabilityRating() { return reliabilityRating; }
}
