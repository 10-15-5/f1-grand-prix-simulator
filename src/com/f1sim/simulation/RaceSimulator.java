package com.f1sim.simulation;

import com.f1sim.enums.TyreCompound;
import com.f1sim.events.FastestLapEvent;
import com.f1sim.events.MechanicalFailureEvent;
import com.f1sim.events.OvertakingEvent;
import com.f1sim.events.RaceEvent;
import com.f1sim.events.SafetyCarEvent;
import com.f1sim.interfaces.Raceable;
import com.f1sim.interfaces.Scorable;
import com.f1sim.models.Car;
import com.f1sim.models.Driver;
import com.f1sim.models.Team;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

public class RaceSimulator implements Raceable, Scorable {
    private final Random random;

    public RaceSimulator() {
        this(new Random());
    }

    // Overloaded constructor makes tests/reproducible runs possible with a fixed seed.
    public RaceSimulator(long seed) {
        this(new Random(seed));
    }

    private RaceSimulator(Random random) {
        this.random = random;
    }

    @Override
    public double calculateLapTime(Driver driver, int lapNumber) {
        Team team = findTeamForDriver(driver);
        Car car = team.getCar();
        TyreCompound tyres = driver.getTyreCompound();

        double base = 100.0;
        double skillEffect = (driver.getSkillRating() - 70) * 0.12;
        double carEffect = (car.getSpeedRating() - 70) * 0.10;
        double tyreEffect = tyres.getPaceBonus();
        double degradation = Math.max(0, lapNumber - 1) * (0.12 - tyres.getDurabilityBonus() * 0.025);
        double randomVariation = random.nextDouble() * 1.4;

        return base - skillEffect - carEffect - tyreEffect + degradation + randomVariation;
    }

    public RaceResult runRace(Race race) throws InterruptedException {
        this.activeTeams = race.getTeams();
        try {
            return simulateRace(race);
        } finally {
            this.activeTeams = null;
        }
    }

    private RaceResult simulateRace(Race race) throws InterruptedException {
        List<Driver> drivers = new ArrayList<>();

        for (Team team : race.getTeams()) {
            drivers.addAll(team.getDrivers());
        }

        Predicate<Driver> eligibleDriver =
                driver -> driver.getSkillRating() >= 1;

        drivers.removeIf(eligibleDriver.negate());

        if (drivers.isEmpty()) {
            throw new IllegalStateException("There are no eligible drivers.");
        }

        Map<Driver, Double> totalTimes = new HashMap<>();
        List<RaceEvent> allEvents = new ArrayList<>();

        // Initialise every driver's race time.
        for (Driver driver : drivers) {
            totalTimes.put(driver, 0.0);
        }

        // Process the race one lap at a time.
        for (int lap = 1; lap <= race.getNumberOfLaps(); lap++) {

            System.out.println("\n--- LAP " + lap + " ---");

            for (Driver driver : drivers) {

                double lapTime = calculateLapTime(driver, lap);

                RaceEvent event = maybeCreateEvent(lap, driver);

                if (event != null) {
                    lapTime = event.adjustLapTime(lapTime);
                    System.out.println(event.toString());
                    allEvents.add(event);
                    Thread.sleep(1500);
                }

                totalTimes.merge(driver, lapTime, Double::sum);
            }
        }

        // Sort drivers by their total race time.
        drivers.sort(Comparator.comparingDouble(totalTimes::get));

        List<DriverStanding> standings = new ArrayList<>();

        for (int i = 0; i < drivers.size(); i++) {
            Driver driver = drivers.get(i);
            int points = pointsForPosition(i + 1);

            standings.add(new DriverStanding(
                    driver,
                    new RaceTime(totalTimes.get(driver)),
                    points,
                    false
            ));
        }

        return new RaceResult(
                race.getName(),
                race.getRaceDate(),
                standings,
                allEvents
        );
    }

    private RaceEvent maybeCreateEvent(int lap, Driver driver) {
        int roll = random.nextInt(100);
        if (roll < 5) return new MechanicalFailureEvent(lap, driver.getName());
        if (roll < 10) return new SafetyCarEvent(lap, driver.getName());
        if (roll < 22) return new OvertakingEvent(lap, driver.getName());
        if (roll < 25) return new FastestLapEvent(lap, driver.getName());
        return null;
    }

    private Team findTeamForDriver(Driver driver) {
        // The team name is stored on each driver; the simulator's team car is located by name.
        // A temporary lookup is populated from the active race in the overload below.
        if (activeTeams == null) {
            throw new IllegalStateException("Race teams have not been set.");
        }
        return activeTeams.stream()
                .filter(team -> team.getName().equals(driver.getTeam()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Driver's team was not found."));
    }

    private List<Team> activeTeams;

    @Override
    public int pointsForPosition(int position) {
        return switch (position) {
            case 1 -> 25;
            case 2 -> 18;
            case 3 -> 15;
            case 4 -> 12;
            case 5 -> 10;
            case 6 -> 8;
            case 7 -> 6;
            case 8 -> 4;
            case 9 -> 2;
            case 10 -> 1;
            default -> 0;
        };
    }
}
