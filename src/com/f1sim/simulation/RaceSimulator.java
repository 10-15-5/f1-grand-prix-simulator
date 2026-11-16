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

/**
 * Simulates Grand Prix races and calculates race results.
 *
 * Calculates lap times using driver skill, car performance,
 * tyre characteristics and random variation. Generates race events,
 * tracks total race times and produces the final driver standings.
 *
 * Implements the Raceable and Scorable interfaces.
 */
public class RaceSimulator implements Raceable, Scorable {
    private final Random random;


    /**
     * Creates a race simulator using a randomly generated seed.
     *
     * Initialises the Random object used to introduce variation
     * into lap times and generate random race events.
     */
    public RaceSimulator() {
        this(new Random());
    }


    /**
     * Creates a race simulator using a specified random seed.
     *
     * Allows repeatable simulation results by using the same seed
     * when generating random numbers.
     *
     * @param seed the seed used to initialise the Random object
     */
    public RaceSimulator(long seed) {
        this(new Random(seed));
    }


    /**
     * Initialises the race simulator with the supplied Random object.
     *
     * Used internally by the other constructors to initialise
     * the random number generator.
     *
     * @param random the Random object used for simulation randomness
     */
    private RaceSimulator(Random random) {
        this.random = random;
    }


    /**
     * Calculates the lap time for a driver based on their performance
     * and the characteristics of their car and selected tyre compound.
     *
     * Takes into account driver skill, car speed, tyre pace bonus,
     * tyre degradation and random variation.
     *
     * @param driver the driver completing the lap
     * @param lapNumber the current lap number
     * @return the calculated lap time in seconds
     * @throws IllegalStateException if the race teams have not been set
     * @throws IllegalArgumentException if the driver's team cannot be found
     */
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


    /**
     * Runs a race using the teams supplied in the Race object.
     *
     * Temporarily stores the active teams so that other simulation
     * methods can access each driver's team and car information.
     * Ensures the active team reference is cleared after the race,
     * even if an exception occurs.
     *
     * @param race the race to be simulated
     * @return the completed race results
     * @throws InterruptedException if the race simulation is interrupted
     */
    public RaceResult runRace(Race race) throws InterruptedException {
        this.activeTeams = race.getTeams();
        try {
            return simulateRace(race);
        } finally {
            this.activeTeams = null;
        }
    }


    /**
     * Simulates the individual laps of a race and calculates the final standings.
     *
     * Collects all eligible drivers, calculates their lap times, generates
     * random race events and sums their total race times.
     * Drivers are then sorted by their total times and awarded
     * points according to their finishing positions.
     *
     * @param race the race being simulated
     * @return a RaceResult containing the final standings and race events
     * @throws InterruptedException if the simulation is interrupted
     * @throws IllegalStateException if there are no eligible drivers
     */
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
                    System.out.print(RaceReport.formatEvents(event));
                    allEvents.add(event);
                    // Sleep for 1.5seconds so user can read events
                    Thread.sleep(1500);
                }

                if (lapTime < 95) {
                    // Display the driver's lap time and performance classification.
                    System.out.printf("- %s's lap %d time - %.2f seconds - %s%n",
                            driver.getName(),
                            lap,
                            lapTime,
                            LapTimeClassifier.classify(lapTime));
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


    /**
     * Randomly determines whether a race event occurs for a driver.
     *
     * Mechanical failure probability is influenced by the reliability
     * rating of the driver's car. Other events use fixed probabilities.
     *
     * @param lap the current lap number
     * @param driver the driver being checked for a race event
     * @return the generated RaceEvent, or null if no event occurs
     */
    private RaceEvent maybeCreateEvent(int lap, Driver driver) {

        int reliability = findTeamForDriver(driver)
            .getCar()
            .getReliabilityRating();

        // Calculate mechanical failure probability.
        // chatgpt created this formula for me
        double failureChance = 1.0 + (100 - reliability) * 0.2;

        // Check whether a mechanical failure occurs.
        if (random.nextDouble() * 100 < failureChance) {
            return new MechanicalFailureEvent(lap, driver.getName());
        }

        // Check for other race events.
        int roll = random.nextInt(100);

        if (roll < 5) return new SafetyCarEvent(lap, driver.getName());
        if (roll < 17) return new OvertakingEvent(lap, driver.getName());
        if (roll < 20) return new FastestLapEvent(lap, driver.getName());

        return null;
    }


    /**
     * Finds the team that a driver belongs to.
     *
     * Searches the active race teams using the driver's team name
     * and returns the matching Team object.
     *
     * @param driver the driver whose team needs to be located
     * @return the Team object associated with the driver
     * @throws IllegalStateException if the active race teams have not been set
     * @throws IllegalArgumentException if the driver's team cannot be found
     */
    private Team findTeamForDriver(Driver driver) {
        if (activeTeams == null) {
            throw new IllegalStateException("Race teams have not been set.");
        }
        return activeTeams.stream()
                .filter(team -> team.getName().equals(driver.getTeam()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Driver's team was not found."));
    }

    private List<Team> activeTeams;


    /**
     * Determines the championship points awarded for a finishing position.
     *
     * Uses a switch expression to assign points based on the driver's
     * final race position. Drivers finishing outside the top ten
     * receive zero points.
     *
     * @param position the driver's finishing position
     * @return the championship points awarded
     */
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
