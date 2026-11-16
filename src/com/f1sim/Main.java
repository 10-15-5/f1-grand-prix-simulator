package com.f1sim;

import com.f1sim.enums.TyreCompound;
import com.f1sim.models.Driver;
import com.f1sim.models.Team;
import com.f1sim.simulation.Championship;
import com.f1sim.simulation.Race;
import com.f1sim.simulation.RaceCar;
import com.f1sim.simulation.RaceResult;
import com.f1sim.simulation.RaceSetupException;
import com.f1sim.simulation.RaceSimulator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    /**
     * The main method for the F1 Grand Prix Simulator.
     *
     * Initialises the championship, creates the teams and drivers,
     * allows the user to select a driver and tyre compound, and runs
     * up to three Grand Prix races.
     *
     * After each race, the results and updated championship standings
     * are displayed. User then can choose whether to continue racing.
     *
     * @param args
     * @throws InterruptedException if the race simulation is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Team> teams = createTeams();
            List<Driver> drivers = getAllDrivers(teams);
            Championship championship = new Championship("OOP1 Grand Prix Championship");
            RaceSimulator simulator = new RaceSimulator();

            RaceCar.display();

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

        int finalPosition = championship.getDriverPosition(selectedDriver);

        System.out.println("\n=================================");
        System.out.println("  FINAL CHAMPIONSHIP RESULT");
        System.out.println("=================================");

        String suffix = switch (finalPosition) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };

        if (finalPosition == 1) {
            System.out.println("WORLD CHAMPION! Congratulations, "
                    + selectedDriver.getName() + "!");
        } else if (finalPosition <= 3 && finalPosition > 0) {
            System.out.println("Congratulations! " + selectedDriver.getName()
                    + " finished " + finalPosition + suffix + " in the championship!");
        } else if (finalPosition > 0) {
            System.out.println("Unfortunately, " + selectedDriver.getName()
                    + " finished " + finalPosition + suffix
                    + " in the championship. Better luck next season!");
        } else {
            System.out.println("Your driver did not score any championship points.");
        }

        System.out.println("\nThanks for racing!");
        } catch (RaceSetupException exception) {
            System.out.println("Unable to start the race: " + exception.getMessage());
        }
    }

    
    /**
     * Creates and initialises the five teams for the championship.
     *
     * Each team is assigned a car with set performance ratings and
     * two drivers with their own names, car numbers and skill ratings.
     *
     * @return a list containing all five teams and their drivers
     */
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


    /**
     * Collects all drivers from the supplied teams into a single list.
     *
     * Iterates through each team and adds its drivers to the list,
     * making it easier to access all championship drivers in one place.
     *
     * @param teams the list of teams participating in the championship
     * @return a list containing all drivers from the supplied teams
     */
    private static List<Driver> getAllDrivers(List<Team> teams) {
        List<Driver> drivers = new ArrayList<>();
        for (Team team : teams) {
            drivers.addAll(team.getDrivers());
        }
        return drivers;
    }


    /**
     * Displays all available drivers and allows the user to select one.
     *
     * Each driver's name, team and car number are displayed.
     * The user's selection is validated using the readNumber method.
     *
     * @param scanner the Scanner used to read user input
     * @param drivers the list of available drivers
     * @return the Driver object selected by the user
     */
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


    /**
     * Reads and validates an integer entered by the user.
     *
     * Repeatedly aks the user until a valid integer within the
     * range is entered. Any invalid input 
     * causes the user to be prompted again.
     *
     * @param scanner the Scanner used to read user input
     * @param prompt the message displayed when requesting input
     * @param min the minimum permitted value
     * @param max the maximum permitted value
     * @return the validated integer entered by the user
     */
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


    /**
     * Displays the results of a completed Grand Prix.
     *
     * Prints the race name, date and final driver standings, including
     * each driver's position, name, team and total race time.
     * The user's selected driver is highlighted in the results.
     *
     * @param result the results of the completed race
     * @param selectedDriver the driver selected by the user
     */
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
    }
}
