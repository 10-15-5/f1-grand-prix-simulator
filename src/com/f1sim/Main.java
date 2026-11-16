package com.f1sim;

import com.f1sim.enums.TyreCompound;
import com.f1sim.models.Driver;
import com.f1sim.models.Team;
import com.f1sim.simulation.Championship;
import com.f1sim.simulation.Race;
import com.f1sim.simulation.RaceResult;
import com.f1sim.simulation.RaceReport;
import com.f1sim.simulation.RaceSetupException;
import com.f1sim.simulation.RaceSimulator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Team> teams = createTeams();
            List<Driver> drivers = getAllDrivers(teams);
            Championship championship = new Championship("OOP1 Grand Prix Championship");
            RaceSimulator simulator = new RaceSimulator();

            System.out.println("=================================");
            System.out.println("       F1 GRAND PRIX SIMULATOR");
            System.out.println("=================================");
            System.out.println("Welcome to the fictional Grand Prix championship!");

            Driver selectedDriver = selectDriver(scanner, drivers);

            boolean continueRacing = true;
            int round = 1;

            while (continueRacing && round <= 3) {
                System.out.println("\nChoose your tyre compound:");
                TyreCompound[] compounds = TyreCompound.values();
                for (int i = 0; i < compounds.length; i++) {
                    System.out.println((i + 1) + ". " + compounds[i]);
                }

                int tyreChoice = readNumber(scanner, "Tyre choice: ", 1, compounds.length);
                selectedDriver.setTyreCompound(compounds[tyreChoice - 1]);

                Race race = new Race("Grand Prix " + round, 5, LocalDate.now(), teams);
                System.out.println("\nPreparing " + race.getName() + "...");
                System.out.println("Your driver: " + selectedDriver.getName()
                        + " (" + selectedDriver.getTeam() + ")");
                System.out.println("Tyres: " + selectedDriver.getTyreCompound());

                RaceResult result = simulator.runRace(race);
                printRaceResult(result, selectedDriver);
                championship.addRaceResult(result);

                System.out.println("\nChampionship standings:");
                championship.printStandings();

                round++;
                if (round <= 3) {
                    System.out.print("\nRun the next Grand Prix? (y/n): ");
                    continueRacing = scanner.nextLine().trim().equalsIgnoreCase("y");
                }
            }

            System.out.println("\nThanks for racing!");
        } catch (RaceSetupException exception) {
            System.out.println("Unable to start the race: " + exception.getMessage());
        }
    }

    private static List<Team> createTeams() {
        List<Team> teams = new ArrayList<>();

        Team apex = new Team("Apex Racing");
        apex.setCar(new com.f1sim.models.Car("Apex V1", 92, 90));
        apex.addDriver(new Driver("Max Sterling", "Apex Racing", 7, 95));
        apex.addDriver(new Driver("Lucas Hart", "Apex Racing", 12, 88));

        Team velocity = new Team("Velocity Motorsport");
        velocity.setCar(new com.f1sim.models.Car("Velocity X", 89, 94));
        velocity.addDriver(new Driver("Oliver Knight", "Velocity Motorsport", 21, 91));
        velocity.addDriver(new Driver("Daniel Frost", "Velocity Motorsport", 16, 85));

        Team titan = new Team("Titan GP");
        titan.setCar(new com.f1sim.models.Car("Titan R", 95, 84));
        titan.addDriver(new Driver("Ethan Cole", "Titan GP", 33, 89));
        titan.addDriver(new Driver("Jack Rowan", "Titan GP", 44, 82));

        Team phoenix = new Team("Phoenix Racing");
        phoenix.setCar(new com.f1sim.models.Car("Phoenix P2", 87, 96));
        phoenix.addDriver(new Driver("Noah Blake", "Phoenix Racing", 55, 93));
        phoenix.addDriver(new Driver("Ryan Vale", "Phoenix Racing", 19, 86));

        Team storm = new Team("Storm Autosport");
        storm.setCar(new com.f1sim.models.Car("Storm S", 91, 88));
        storm.addDriver(new Driver("Alex Mercer", "Storm Autosport", 77, 90));
        storm.addDriver(new Driver("Leo Quinn", "Storm Autosport", 24, 84));

        teams.addAll(List.of(apex, velocity, titan, phoenix, storm));
        return teams;
    }

    private static List<Driver> getAllDrivers(List<Team> teams) {
        List<Driver> drivers = new ArrayList<>();
        for (Team team : teams) {
            drivers.addAll(team.getDrivers());
        }
        return drivers;
    }

    private static Driver selectDriver(Scanner scanner, List<Driver> drivers) {
        System.out.println("\nAvailable drivers:");
        for (int i = 0; i < drivers.size(); i++) {
            Driver driver = drivers.get(i);
            System.out.printf("%2d. %-15s | %-20s | Car #%d%n",
                    i + 1, driver.getName(), driver.getTeam(), driver.getCarNumber());
        }
        int choice = readNumber(scanner, "Select your driver: ", 1, drivers.size());
        return drivers.get(choice - 1);
    }

    private static int readNumber(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Ask again when the input is not an integer.
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    private static void printRaceResult(RaceResult result, Driver selectedDriver) {
        System.out.println("\n========== " + result.getRaceName() + " RESULTS ==========");
        System.out.println("Race date: " + result.getRaceDate());
        System.out.println("------------------------------------------------");
        System.out.printf("%-4s %-16s %-22s %-12s %-50s%n", "Pos", "Driver", "Team", "Total time", "");
        int position = 1;
        for (var standing : result.getStandings()) {
            if (standing.driver().equals(selectedDriver)) {
                System.out.printf("%-4d %-16s %-22s %-12s %-50s%n",
                    position++, standing.driver().getName(), standing.driver().getTeam(),
                    standing.totalTime(), " <-- Your driver");
            }
            else {
                System.out.printf("%-4d %-16s %-22s %-12s%n",
                    position++, standing.driver().getName(), standing.driver().getTeam(),
                    standing.totalTime());
            }
        }
        System.out.println("------------------------------------------------");
        // System.out.println("Race events:");
        // System.out.print(RaceReport.formatEvents(result.getEvents().toArray(
        //         com.f1sim.events.RaceEvent[]::new)));
    }
}
