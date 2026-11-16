package com.f1sim.simulation;

import com.f1sim.models.Driver;

/**
 * Represents a driver's final standing in a race.
 *
 * @param driver the driver participating in the race
 * @param totalTime the driver's total race time
 * @param points the championship points awarded
 * @param fastestLap indicates whether the driver achieved the fastest lap
 */
public record DriverStanding(Driver driver, RaceTime totalTime, int points, boolean fastestLap) {


    /**
     * Validates the record components when a DriverStanding is created.
     *
     * @throws IllegalArgumentException if the driver or total time is null
     */
    public DriverStanding {
        if (driver == null || totalTime == null) {
            throw new IllegalArgumentException("Driver and total time are required.");
        }
    }
}