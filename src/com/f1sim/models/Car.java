package com.f1sim.models;

/**
 * Represents a car used by a team in the F1 simulator.
 *
 * Each car has a model name, a speed rating and a reliability rating.
 * These ratings influence the car's performance during a race.
 */
public class Car {
    private final String model;
    private final int speedRating;
    private final int reliabilityRating;


    /**
     * Creates a car with the specified model, speed rating and reliability rating.
     *
     * Validates the model name and ensures both ratings are between 1 and 100.
     *
     * @param model the name of the car model
     * @param speedRating the car's speed rating (1-100)
     * @param reliabilityRating the car's reliability rating (1-100)
     * @throws IllegalArgumentException if the model is blank or either rating is outside the permitted range
     * 
     */
    public Car(String model, int speedRating, int reliabilityRating) {
        if (model == null || model.isBlank()) 
            throw new IllegalArgumentException("Model cannot be blank.");
        validateRating(speedRating, "Speed");
        validateRating(reliabilityRating, "Reliability");
        this.model = model;
        this.speedRating = speedRating;
        this.reliabilityRating = reliabilityRating;
    }


    /**
     * Checks whether a car rating falls within the permitted range.
     * 
     * Both speedRating and reliabilityRating need to be validated in the
     * same way. This method was made instead of duplicating validation.
     *
     * @param rating the rating value being checked
     * @param label the name of the rating, used in the error message
     * @throws IllegalArgumentException if the rating is below 1 or above 100
     */
    private static void validateRating(int rating, String label) {
        if (rating < 1 || rating > 100) {
            throw new IllegalArgumentException(label + " rating must be between 1 and 100.");
        }
    }


    /**
     * Returns the model name of the car.
     *
     * @return the car's model name
     */
    public String getModel() { 
        return model; 
    }


    /**
     * Returns the speed rating of the car.
     *
     * @return the car's speed rating
     */
    public int getSpeedRating() { 
        return speedRating; 
    }
    
    
    /**
     * Returns the reliability rating of the car.
     *
     * @return the car's reliability rating
     */
    public int getReliabilityRating() { 
        return reliabilityRating; 
    }
}
