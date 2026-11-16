package com.f1sim.simulation;

import com.f1sim.events.RaceEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class RaceResult {
    private final String raceName;
    private final LocalDate raceDate;
    private final List<DriverStanding> standings;
    private final List<RaceEvent> events;

    public RaceResult(String raceName, LocalDate raceDate,
                      List<DriverStanding> standings, List<RaceEvent> events) {
        this.raceName = raceName;
        this.raceDate = raceDate;
        this.standings = List.copyOf(standings);
        this.events = events.stream()
            .sorted(Comparator.comparingInt(RaceEvent::getLapNumber))
            .toList();
    }

    public String getRaceName() { return raceName; }
    public LocalDate getRaceDate() { return raceDate; }
    public List<DriverStanding> getStandings() { return new ArrayList<>(standings); }
    public List<RaceEvent> getEvents() { return new ArrayList<>(events); }
}
