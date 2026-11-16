package com.f1sim.events;

/**
 * Represents an overtaking event during a race.
 * An overtake reduces the driver's lap time slightly.
 */
public final class OvertakingEvent extends RaceEvent {


    /**
     * Creates an overtaking event for a specific lap and driver.
     *
     * @param lapNumber the lap on which the overtake occurs
     * @param driverName the name of the driver making the overtake
     */
    public OvertakingEvent(int lapNumber, String driverName) {
        super(lapNumber, driverName);
    }


    /**
     * Describes the overtaking event.
     *
     * @return a message identifying the driver and lap
     */
    @Override
    public String describe() {
        return super.getDriverName() + " overtakes on lap " + super.getLapNumber() + ".";
    }

    
    /**
     * Reduces the driver's lap time by 0.6 seconds to represent
     * the benefit of making an overtake.
     *
     * @param lapTimeSeconds the original lap time in seconds
     * @return the adjusted lap time, with a minimum of one second
     */
    @Override
    public double adjustLapTime(double lapTimeSeconds) {
        return Math.max(1, lapTimeSeconds - 0.6);
    }
}