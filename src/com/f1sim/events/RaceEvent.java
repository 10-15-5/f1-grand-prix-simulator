package com.f1sim.events;

/**
 * Abstract parent class representing an event that can occur during a race.
 * Stores shared event information and defines methods that specific event
 * types must implement.
 *
 * This is a sealed class, restricting inheritance to the permitted subclasses.
 */
public sealed abstract class RaceEvent permits OvertakingEvent, SafetyCarEvent, MechanicalFailureEvent, FastestLapEvent {

    private final int lapNumber;
    private final String driverName;


    /**
     * Creates a race event for a particular lap and driver.
     *
     * @param lapNumber the lap on which the event occurs
     * @param driverName the name of the driver involved
     */
    protected RaceEvent(int lapNumber, String driverName) {
        this.lapNumber = lapNumber;
        this.driverName = driverName;
    }


    public int getLapNumber() { 
        return lapNumber; 
    }


    public String getDriverName() { 
        return driverName; 
    }


    /**
     * Provides a text description of the specific race event.
     *
     * @return a description of the event
     */
    public abstract String describe();


    /**
     * Applies the event's effect to a driver's lap time.
     *
     * @param lapTimeSeconds the original lap time in seconds
     * @return the adjusted lap time in seconds
     */
    public abstract double adjustLapTime(double lapTimeSeconds);

    
    /**
     * Returns the event's description as its string representation.
     *
     * @return the description of the event
     */
    @Override
    public String toString() {
        return describe();
    }
}