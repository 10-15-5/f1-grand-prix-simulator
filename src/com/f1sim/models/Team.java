package com.f1sim.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a team participating in the F1 simulator.
 *
 * Each team has a name, a car and a collection of drivers.
 * A team can have a maximum of two drivers.
 */
public class Team {
    private final String name;
    private final List<Driver> drivers = new ArrayList<>();
    private Car car;


    /**
     * Creates a team with the specified name.
     *
     * Validates that the team name is not null or blank before
     * assigning it to the team.
     *
     * @param name the name of the team
     * @throws IllegalArgumentException if the team name is null or blank
     */
    public Team(String name) {
        if (name == null || name.isBlank()) 
            throw new IllegalArgumentException("Team name cannot be blank.");
        this.name = name;
    }

    public String getName() { return name; }

    
    /**
     * Returns a defensive copy of the team's driver list.
     *
     * Creates a new ArrayList containing the team's drivers so that
     * external code cannot directly modify the team's internal list.
     *
     * @return a new list containing the team's drivers
     */
    public List<Driver> getDrivers() {
        return new ArrayList<>(drivers);
    }


    /**
     * Adds a driver to the team.
     *
     * Checks that the supplied driver is not null and that the team
     * does not already contain two drivers before adding them.
     *
     * @param driver the driver to add to the team
     * @throws IllegalArgumentException if the supplied driver is null
     * @throws IllegalStateException if the team already has two drivers
     */
    public void addDriver(Driver driver) {
        if (driver == null) 
            throw new IllegalArgumentException("Driver cannot be null.");
        if (drivers.size() >= 2) 
            throw new IllegalStateException("A team cannot have more than two drivers.");
        drivers.add(driver);
    }

    public Car getCar() { return car; }

    public void setCar(Car car) {
        if (car == null) 
            throw new IllegalArgumentException("Car cannot be null.");
        this.car = car;
    }
}
