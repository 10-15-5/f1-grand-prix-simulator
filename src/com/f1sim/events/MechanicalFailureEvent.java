package com.f1sim.events;

public final class MechanicalFailureEvent extends RaceEvent {
    public MechanicalFailureEvent(int lapNumber, String driverName) {
        super(lapNumber, driverName);
    }

    @Override
    public String describe() {
        return getDriverName() + " suffers a mechanical issue on lap " + getLapNumber() + ".";
    }

    @Override
    public double adjustLapTime(double lapTimeSeconds) {
        return lapTimeSeconds + 7.0;
    }
}
