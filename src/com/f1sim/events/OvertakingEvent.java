package com.f1sim.events;

public final class OvertakingEvent extends RaceEvent {
    public OvertakingEvent(int lapNumber, String driverName) {
        super(lapNumber, driverName);
    }

    @Override
    public String describe() {
        return super.getDriverName() + " makes an overtake on lap " + super.getLapNumber() + ".";
    }

    @Override
    public double adjustLapTime(double lapTimeSeconds) {
        return Math.max(1, lapTimeSeconds - 0.6);
    }
}
