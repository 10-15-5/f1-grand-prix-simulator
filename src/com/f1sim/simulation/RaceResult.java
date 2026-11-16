package com.f1sim.simulation;

import com.f1sim.events.RaceEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Stores the final results of a completed Grand Prix.
 *
 * Contains the race name, race date, final driver standings
 * and events that occurred during the race.
 *
 * The class is immutable, preventing its stored results from
 * being modified after the object has been created.
 */
public final class RaceResult {
    private final String raceName;
    private final LocalDate raceDate;
    private final List<DriverStanding> standings;
    private final List<RaceEvent> events;


    /**
     * Creates a RaceResult containing the details of a completed race.
     *
     * Stores defensive copies of the standings and events collections.
     * Events are sorted by lap number so that they are displayed
     * in chronological order.
     *
     * @param raceName the name of the completed race
     * @param raceDate the date the race took place
     * @param standings the final driver standings
     * @param events the events that occurred during the race
     */
    public RaceResult(String raceName, LocalDate raceDate,
                      List<DriverStanding> standings, List<RaceEvent> events) {
        this.raceName = raceName;
        this.raceDate = raceDate;
        this.standings = List.copyOf(standings);
        this.events = events.stream()
            .sorted(Comparator.comparingInt(RaceEvent::getLapNumber))
            .toList();
    }


    public String getRaceName() { 
        return raceName; 
    }


    public LocalDate getRaceDate() { 
        return raceDate; 
    }


    /**
     * Returns a defensive copy of the final driver standings.
     *
     * Creates a new ArrayList so that external code cannot directly
     * modify the standings stored within this RaceResult.
     *
     * @return a new list containing the final driver standings
     */
    public List<DriverStanding> getStandings() { 
        return new ArrayList<>(standings); 
    }


    /**
     * Returns a defensive copy of the race events.
     *
     * Creates a new ArrayList containing the events stored in
     * chronological order.
     *
     * @return a new list containing the race events
     */
    public List<RaceEvent> getEvents() { 
        return new ArrayList<>(events); 
    }
}
