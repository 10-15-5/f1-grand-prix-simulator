package com.f1sim.interfaces;

import com.f1sim.models.Driver;

/**
 * Defines the behaviour required for something that can participate
 * in race-related calculations.
 */
public interface Raceable {


    /**
     * Calculates the lap time for a driver on a specified lap.
     *
     * @param driver the driver completing the lap
     * @param lapNumber the current lap number
     * @return the calculated lap time in seconds
     */
    double calculateLapTime(Driver driver, int lapNumber);


    /**
     * Provides a default race message for a driver.
     *
     * @param driver the driver currently on track
     * @return a formatted race control message
     */
    default String raceMessage(Driver driver) {
        return formatMessage(driver.getName() + " is on track");
    }


    /**
     * Checks whether a lap number is valid.
     *
     * @param lapNumber the lap number to check
     * @return true if the lap number is greater than zero
     */
    static boolean isValidLapNumber(int lapNumber) {
        return lapNumber > 0;
    }

    
    /**
     * Formats a message with the race control prefix.
     *
     * @param message the message to format
     * @return the formatted race control message
     */
    private String formatMessage(String message) {
        return "[RACE CONTROL] " + message;
    }
}