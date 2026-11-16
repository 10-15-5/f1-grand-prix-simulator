package com.f1sim.simulation;

import com.f1sim.models.Driver;

public record DriverStanding(Driver driver, RaceTime totalTime, int points, boolean fastestLap) {
    public DriverStanding {
        if (driver == null || totalTime == null) {
            throw new IllegalArgumentException("Driver and total time are required.");
        }
    }
}
