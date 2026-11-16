package com.f1sim.models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String name;
    private final List<Driver> drivers = new ArrayList<>();
    private Car car;

    public Team(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Team name cannot be blank.");
        this.name = name;
    }

    public String getName() { return name; }

    // Defensive copy: callers cannot modify the team's internal list.
    public List<Driver> getDrivers() {
        return new ArrayList<>(drivers);
    }

    public void addDriver(Driver driver) {
        if (driver == null) throw new IllegalArgumentException("Driver cannot be null.");
        if (drivers.size() >= 2) throw new IllegalStateException("A team cannot have more than two drivers.");
        drivers.add(driver);
    }

    public Car getCar() { return car; }

    public void setCar(Car car) {
        if (car == null) throw new IllegalArgumentException("Car cannot be null.");
        this.car = car;
    }
}
