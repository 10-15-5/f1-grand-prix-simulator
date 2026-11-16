package com.f1sim.simulation;

import com.f1sim.events.FastestLapEvent;
import com.f1sim.events.MechanicalFailureEvent;
import com.f1sim.events.OvertakingEvent;
import com.f1sim.events.RaceEvent;
import com.f1sim.events.SafetyCarEvent;

public final class RaceReport {

    private RaceReport() {
        // Utility class: instances are not required.
    }

    // Varargs allows callers to pass any number of race events.
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
