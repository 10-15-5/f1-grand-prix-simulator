package com.f1sim.simulation;

import java.util.Locale;

/**
 * Represents a race time in seconds.
 * 
 * It implements Comparable to allow race times 
 * to be compared and sorted.
 */
public final class RaceTime implements Comparable<RaceTime> {
    private final double seconds;


    /**
     * Creates a RaceTime with the specified number of seconds.
     *
     * @param seconds the race time in seconds; must be finite and non-negative
     * @throws IllegalArgumentException if seconds is negative, NaN or infinite
     */
    public RaceTime(double seconds) {
        if (!Double.isFinite(seconds) || seconds < 0) {
            throw new IllegalArgumentException("Race time must be a non-negative number.");
        }
        this.seconds = seconds;
    }


    public double toSeconds() { return seconds; }


    /**
     * Adds another race time to this race time and returns the result
     * as a new RaceTime object.
     *
     * @param other the race time to add
     * @return a new RaceTime containing the combined time
     */
    public RaceTime plus(RaceTime other) {
        return new RaceTime(this.seconds + other.seconds);
    }


    /**
     * Compares this race time with another race time.
     *
     * @param other the race time to compare against
     * @return a negative number if this time is shorter, zero if equal,
     *         or a positive number if this time is longer
     */
    @Override
    public int compareTo(RaceTime other) {
        return Double.compare(this.seconds, other.seconds);
    }


    /**
     * Converts the race time into a formatted minutes-and-seconds string.
     * The seconds portion is displayed to three decimal places.
     *
     * @return the formatted race time, for example "1:24.567"
     */
    @Override
    public String toString() {
        int minutes = (int) (seconds / 60);
        double remainder = seconds % 60;
        return String.format(Locale.ROOT, "%d:%06.3f", minutes, remainder);
    }
}