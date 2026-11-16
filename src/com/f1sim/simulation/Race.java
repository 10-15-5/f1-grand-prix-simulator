package com.f1sim.simulation;

import com.f1sim.models.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Grand Prix race in the F1 simulator.
 *
 * Stores the race name, number of laps, scheduled date and
 * participating teams. Validates the race setup before
 * allowing the race to be created.
 */
public class Race {
    private final String name;
    private final int numberOfLaps;
    private final LocalDate raceDate;
    private final List<Team> teams;


    /**
     * Creates a race with the specified name, number of laps, date and teams.
     *
     * Validates the race details and ensures every participating team
     * has a car and at least one driver. If no date is supplied,
     * the current date is used.
     *
     * @param name the name of the race
     * @param numberOfLaps the number of laps in the race
     * @param raceDate the scheduled date of the race
     * @param teams the teams participating in the race
     * @throws RaceSetupException if the race name is invalid, the number
     * of laps is less than one, no teams are supplied, 
     * or a team has no car or drivers
     */
    public Race(String name, int numberOfLaps, LocalDate raceDate, List<Team> teams)
            throws RaceSetupException {
        if (name == null || name.isBlank()) 
            throw new RaceSetupException("Race name is required.");
        if (numberOfLaps < 1) 
            throw new RaceSetupException("Race must contain at least one lap.");
        if (teams == null || teams.isEmpty()) 
            throw new RaceSetupException("At least one team is required.");
        for (Team team : teams) {
            if (team.getCar() == null) 
                throw new RaceSetupException(team.getName() + " has no car.");
            if (team.getDrivers().isEmpty()) 
                throw new RaceSetupException(team.getName() + " has no drivers.");
        }
        this.name = name;
        this.numberOfLaps = numberOfLaps;
        this.raceDate = raceDate == null ? LocalDate.now() : raceDate;
        this.teams = new ArrayList<>(teams);
    }


    public String getName() { 
        return name; 
    }


    public int getNumberOfLaps() { 
        return numberOfLaps; 
    }


    public LocalDate getRaceDate() { 
        return raceDate; 
    }


    /**
     * Returns a defensive copy of the participating teams list.
     *
     * Creates a new ArrayList containing the teams so that external
     * code cannot directly modify the race's internal team list.
     *
     * @return a new list containing the participating teams
     */
    public List<Team> getTeams() { 
        return new ArrayList<>(teams); 
    }
}
