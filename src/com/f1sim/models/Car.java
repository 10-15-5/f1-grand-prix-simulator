package com.f1sim.models;

public class Car {

    private String model;
    private int speedRating;
    private int reliabilityRating;

    public Car(String model, int speedRating, int reliabilityRating) {

        if (speedRating < 1 || speedRating > 100) {
            throw new IllegalArgumentException("Speed rating must be between 1 and 100.");
        }

        if (reliabilityRating < 1 || reliabilityRating > 100) {
            throw new IllegalArgumentException("Reliability rating must be between 1 and 100.");
        }

        this.model = model;
        this.speedRating = speedRating;
        this.reliabilityRating = reliabilityRating;
    }

    public String getModel() {
        return model;
    }

    public int getSpeedRating() {
        return speedRating;
    }

    public int getReliabilityRating() {
        return reliabilityRating;
    }
}