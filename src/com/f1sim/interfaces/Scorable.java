package com.f1sim.interfaces;

/**
 * Defines the behaviour required for calculating championship points
 * in the Grand Prix simulator.
 */
public interface Scorable {


    /**
     * Determines the championship points awarded for a finishing position.
     *
     * @param position the driver's finishing position
     * @return the points awarded for that position
     */
    int pointsForPosition(int position);


    /**
     * Calculates the additional points awarded for achieving the fastest lap.
     *
     * @param fastestLap indicates whether the driver achieved the fastest lap
     * @return one bonus point if the driver achieved the fastest lap, otherwise zero
     */
    default int fastestLapBonus(boolean fastestLap) {
        return fastestLap ? bonusPoint() : 0;
    }


    /**
     * Returns the name of the championship points system.
     *
     * @return the name of the points system
     */
    static String pointsSystemName() {
        return "Grand Prix 25-18-15 points system";
    }

    
    /**
     * Provides the number of bonus points awarded for the fastest lap.
     *
     * @return one bonus point
     */
    private int bonusPoint() {
        return 1;
    }
}