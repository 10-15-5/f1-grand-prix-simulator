package com.f1sim.simulation;

/**
 * Represents a checked exception thrown when a race cannot be
 * configured correctly.
 */
public class RaceSetupException extends Exception {

    
    /**
     * Creates a RaceSetupException with a descriptive error message.
     *
     * @param message the explanation of why race setup failed
     */
    public RaceSetupException(String message) {
        super(message);
    }
}