package com.f1sim.simulation;

import java.util.Locale;

// A small custom immutable type representing a race time in seconds.
public final class RaceTime implements Comparable<RaceTime> {
    private final double seconds;


    public RaceTime(double seconds) {
        if (!Double.isFinite(seconds) || seconds < 0) {
            throw new IllegalArgumentException("Race time must be a non-negative number.");
        }
        this.seconds = seconds;
    }


    public double toSeconds() { return seconds; }


    public RaceTime plus(RaceTime other) {
        return new RaceTime(this.seconds + other.seconds);
    }


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
