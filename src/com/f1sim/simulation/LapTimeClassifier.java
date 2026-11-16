
package com.f1sim.simulation;

/**
 * Classifies lap times into performance categories.
 * Java 27 primitive type patterns in switch expressions.
 */
public final class LapTimeClassifier {

    private LapTimeClassifier() {
        
    }

    /**
     * Classifies a supplied value as a lap-time performance category.
     *
     * @param value the value being checked
     * @return the lap-time classification
     */
    public static String classify(Object value) {
        return switch (value) {
            case null -> "No lap time";

            case double time when !Double.isFinite(time) || time <= 0
                    -> "Invalid lap time";

            case double time when time < 95.0
                    -> "Exceptional";

            case double time when time < 100.0
                    -> "Fast";

            case double time
                    -> "Average";

            default -> "Not a numeric lap time";
        };
    }
}
