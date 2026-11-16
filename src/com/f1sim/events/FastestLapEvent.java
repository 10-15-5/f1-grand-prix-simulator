package com.f1sim.events;

public final class FastestLapEvent extends RaceEvent {
    public FastestLapEvent(int lapNumber, String driverName) {
        super(lapNumber, driverName);
    }

    @Override
    public String describe() {
        return getDriverName() + " sets the fastest lap on lap " + getLapNumber() + "!";
    }

    @Override
    public double adjustLapTime(double lapTimeSeconds) {
        return Math.max(1, lapTimeSeconds - 1.2);
    }
}
