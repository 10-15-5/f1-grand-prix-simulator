package com.f1sim.events;

/**
 * Represents a fastest lap event during a race.
 * Reduces the driver's lap time to reward setting the fastest lap.
 */
public final class FastestLapEvent extends RaceEvent {

    
    /**
     * Creates a fastest lap event for a specific lap and driver.
     *
     * @param lapNumber the lap on which the fastest lap occurs
     * @param driverName the name of the driver setting the fastest lap
     */
    public FastestLapEvent(int lapNumber, String driverName) {
        super(lapNumber, driverName);
    }


    /**
     * Describes the fastest lap event.
     *
     * @return a message identifying the driver and lap
     */
    @Override
    public String describe() {
        return getDriverName() + " sets the fastest lap on lap " + getLapNumber() + "!";
    }

    
    /**
     * Reduces the driver's lap time by 1.2 seconds.
     *
     * @param lapTimeSeconds the original lap time in seconds
     * @return the adjusted lap time, with a minimum of one second
     */
    @Override
    public double adjustLapTime(double lapTimeSeconds) {
        return Math.max(1, lapTimeSeconds - 1.2);
    }
}