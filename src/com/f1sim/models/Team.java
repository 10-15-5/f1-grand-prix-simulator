package com.f1sim.models;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String name;
    private List<Driver> drivers;
    private Car car;

    public Team(String name) {
        this.name = name;
        this.drivers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Driver> getDrivers() {
        return new ArrayList<>(drivers);
    }

    public void addDriver(Driver driver) {
        if (drivers.size() >= 2) {
            throw new IllegalStateException("A team cannot have more than two drivers.");
        }

        drivers.add(driver);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}