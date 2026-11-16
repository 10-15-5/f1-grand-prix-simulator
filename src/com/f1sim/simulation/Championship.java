package com.f1sim.simulation;

import com.f1sim.models.Driver;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Championship {
    private final String name;
    private final Map<Driver, Integer> pointsTable = new HashMap<>();

    public Championship(String name) {
        this.name = name;
    }

    public void addRaceResult(RaceResult result) {
        for (DriverStanding standing : result.getStandings()) {
            pointsTable.merge(standing.driver(), standing.points(), Integer::sum);
        }
    }

    public void printStandings() {
        System.out.println(name);
        pointsTable.entrySet().stream()
                .sorted(Map.Entry.<Driver, Integer>comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.printf("%-18s %3d points%n",
                        entry.getKey().getName(), entry.getValue()));
    }
}
