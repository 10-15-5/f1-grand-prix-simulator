package com.f1sim.events;

/**
 * Represents a safety car deployment during a race.
 * Increases lap times to simulate the slower pace behind the safety car.
 */
public final class SafetyCarEvent extends RaceEvent {


    /**
     * Creates a safety car event for a specific lap and driver.
     *
     * @param lapNumber the lap on which the safety car is deployed
     * @param driverName the name of the associated driver
     */
    public SafetyCarEvent(int lapNumber, String driverName) {
        super(lapNumber, driverName);
    }


    /**
     * Describes the safety car deployment.
     *
     * @return a message identifying the lap on which the safety car is deployed
     */
    @Override
    public String describe() {
        return "Safety car deployed on lap " + getLapNumber() + ".";
    }

    
    /**
     * Adds four seconds to the driver's lap time to represent
     * the slower pace behind the safety car.
     *
     * @param lapTimeSeconds the original lap time in seconds
     * @return the adjusted lap time
     */
    @Override
    public double adjustLapTime(double lapTimeSeconds) {
        return lapTimeSeconds + 4.0;
    }
}