package com.f1sim.simulation;

import com.f1sim.models.Driver;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the overall F1 championship standings.
 *
 * Stores the points accumulated by each driver across multiple
 * Grand Prix races and provides functionality to update and
 * display the championship leaderboard.
 */
public class Championship {
    private final String name;
    private final Map<Driver, Integer> pointsTable = new HashMap<>();


    /**
     * Creates a championship with the specified name.
     *
     * Initialises an empty points table to track each driver's
     * accumulated championship points.
     *
     * @param name the name of the championship
     */
    public Championship(String name) {
        this.name = name;
    }


    /**
     * Updates the championship points using the results of a completed race.
     *
     * Iterates through the final driver standings and adds the points
     * earned in the race to each driver's existing championship total.
     *
     * @param result the results of the completed race
     */
    public void addRaceResult(RaceResult result) {
        for (DriverStanding standing : result.getStandings()) {
            pointsTable.merge(standing.driver(), standing.points(), Integer::sum);
        }
    }


    /**
     * Displays the current championship standings.
     *
     * Sorts drivers by their accumulated championship points in
     * descending order and prints each driver's name and total points.
     */
    public void printStandings() {
        System.out.println(name);
        pointsTable.entrySet().stream()
                .sorted(Map.Entry.<Driver, Integer>comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.printf("%-18s %3d points%n",
                        entry.getKey().getName(), entry.getValue()));
    }
}
