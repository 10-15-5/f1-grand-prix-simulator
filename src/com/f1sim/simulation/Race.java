package com.f1sim.simulation;

import com.f1sim.models.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Race {
    private final String name;
    private final int numberOfLaps;
    private final LocalDate raceDate;
    private final List<Team> teams;

    public Race(String name, int numberOfLaps, LocalDate raceDate, List<Team> teams)
            throws RaceSetupException {
        if (name == null || name.isBlank()) throw new RaceSetupException("Race name is required.");
        if (numberOfLaps < 1) throw new RaceSetupException("Race must contain at least one lap.");
        if (teams == null || teams.isEmpty()) throw new RaceSetupException("At least one team is required.");
        for (Team team : teams) {
            if (team.getCar() == null) throw new RaceSetupException(team.getName() + " has no car.");
            if (team.getDrivers().isEmpty()) throw new RaceSetupException(team.getName() + " has no drivers.");
        }
        this.name = name;
        this.numberOfLaps = numberOfLaps;
        this.raceDate = raceDate == null ? LocalDate.now() : raceDate;
        this.teams = new ArrayList<>(teams);
    }

    public String getName() { return name; }
    public int getNumberOfLaps() { return numberOfLaps; }
    public LocalDate getRaceDate() { return raceDate; }
    public List<Team> getTeams() { return new ArrayList<>(teams); }
}
