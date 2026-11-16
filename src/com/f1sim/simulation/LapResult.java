package com.f1sim.simulation;

import com.f1sim.events.RaceEvent;

public record LapResult(int lapNumber, RaceTime lapTime, RaceEvent event) {
    public LapResult {
        if (lapNumber < 1) throw new IllegalArgumentException("Lap number must be positive.");
        if (lapTime == null) throw new IllegalArgumentException("Lap time is required.");
    }
}
