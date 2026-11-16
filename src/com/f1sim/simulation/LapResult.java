package com.f1sim.simulation;

import com.f1sim.events.RaceEvent;

/**
 * Represents the result of a driver's individual lap during a race.
 *
 * @param lapNumber the lap number
 * @param lapTime the time taken to complete the lap
 * @param event the event that occurred during the lap, if any
 */
public record LapResult(int lapNumber, RaceTime lapTime, RaceEvent event) {

    
    /**
     * Validates the record components when a LapResult is created.
     *
     * @throws IllegalArgumentException if the lap number is less than one
     *                                   or the lap time is null
     */
    public LapResult {
        if (lapNumber < 1) throw new IllegalArgumentException("Lap number must be positive.");
        if (lapTime == null) throw new IllegalArgumentException("Lap time is required.");
    }
}