package com.f1sim.interfaces;

import com.f1sim.models.Driver;

public interface Raceable {
    double calculateLapTime(Driver driver, int lapNumber);

    default String raceMessage(Driver driver) {
        return formatMessage(driver.getName() + " is on track");
    }

    static boolean isValidLapNumber(int lapNumber) {
        return lapNumber > 0;
    }

    private String formatMessage(String message) {
        return "[RACE CONTROL] " + message;
    }
}
