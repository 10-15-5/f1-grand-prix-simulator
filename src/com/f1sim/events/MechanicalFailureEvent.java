package com.f1sim.events;

/**
 * Represents a mechanical failure affecting a driver during a race.
 * Increases the driver's lap time to represent the mechanical issue.
 */
public final class MechanicalFailureEvent extends RaceEvent {


    /**
     * Creates a mechanical failure event for a specific lap and driver.
     *
     * @param lapNumber the lap on which the failure occurs
     * @param driverName the name of the affected driver
     */
    public MechanicalFailureEvent(int lapNumber, String driverName) {
        super(lapNumber, driverName);
    }


    /**
     * Describes the mechanical failure event.
     *
     * @return a message identifying the driver and lap
     */
    @Override
    public String describe() {
        return getDriverName() + " suffers a mechanical issue on lap " + getLapNumber() + ".";
    }

    
    /**
     * Adds seven seconds to the driver's lap time to represent
     * the impact of a mechanical failure.
     *
     * @param lapTimeSeconds the original lap time in seconds
     * @return the adjusted lap time
     */
    @Override
    public double adjustLapTime(double lapTimeSeconds) {
        return lapTimeSeconds + 7.0;
    }
}