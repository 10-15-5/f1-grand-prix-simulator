package com.f1sim.interfaces;

public interface Scorable {
    int pointsForPosition(int position);

    default int fastestLapBonus(boolean fastestLap) {
        return fastestLap ? bonusPoint() : 0;
    }

    static String pointsSystemName() {
        return "Grand Prix 25-18-15 points system";
    }

    private int bonusPoint() {
        return 1;
    }
}
