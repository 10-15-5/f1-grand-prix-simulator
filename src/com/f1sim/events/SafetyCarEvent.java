package com.f1sim.events;

public final class SafetyCarEvent extends RaceEvent {
    public SafetyCarEvent(int lapNumber, String driverName) {
        super(lapNumber, driverName);
    }

    @Override
    public String describe() {
        return "Safety car deployed on lap " + getLapNumber() + ".";
    }

    @Override
    public double adjustLapTime(double lapTimeSeconds) {
        return lapTimeSeconds + 4.0;
    }
}
