package com.f1sim.events;

public sealed abstract class RaceEvent
        permits OvertakingEvent, SafetyCarEvent, MechanicalFailureEvent, FastestLapEvent {

    private final int lapNumber;
    private final String driverName;

    protected RaceEvent(int lapNumber, String driverName) {
        this.lapNumber = lapNumber;
        this.driverName = driverName;
    }

    public int getLapNumber() { return lapNumber; }
    public String getDriverName() { return driverName; }

    public abstract String describe();

    public abstract double adjustLapTime(double lapTimeSeconds);
}
