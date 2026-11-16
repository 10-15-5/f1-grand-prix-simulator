package com.f1sim.simulation;

import com.f1sim.events.FastestLapEvent;
import com.f1sim.events.MechanicalFailureEvent;
import com.f1sim.events.OvertakingEvent;
import com.f1sim.events.RaceEvent;
import com.f1sim.events.SafetyCarEvent;

/**
 * Utility class for formatting race events into a readable text report.
 * This class cannot be instantiated and provides static methods instead.
 */
public final class RaceReport {


    /**
     * Private constructor prevents this utility class from being instantiated.
     */
    private RaceReport() {
    }


    /**
     * Formats a collection of race events into a readable report.
     * Each event is identified by its type and its description is added
     * to the report on a separate line.
     *
     * @param events the race events to include in the report
     * @return a formatted report, or a message if there are no major incidents
     */
    public static String formatEvents(RaceEvent... events) {
        StringBuilder report = new StringBuilder();

        if (events.length == 0) {
            return "No major incidents this race.";
        }

        for (RaceEvent event : events) {
            report.append("- ");

            // Pattern matching with instanceof gives us a safely typed variable.
            if (event instanceof OvertakingEvent overtake) {
                report.append(overtake.describe());
            } else if (event instanceof SafetyCarEvent safetyCar) {
                report.append(safetyCar.describe());
            } else if (event instanceof MechanicalFailureEvent failure) {
                report.append(failure.describe());
            } else if (event instanceof FastestLapEvent fastestLap) {
                report.append(fastestLap.describe());
            } else {
                report.append(event.describe());
            }

            report.append(System.lineSeparator());
        }

        return report.toString();
    }
}
