package com.f1sim;

import com.f1sim.models.Driver;
import com.f1sim.models.Team;
import com.f1sim.enums.TyreCompound;
import com.f1sim.models.Car;

public class Main {

    public static void main(String[] args) {

        Team team = new Team("Apex Racing");

        Car car = new Car("Apex V1", 92, 88);
        team.setCar(car);

        System.out.println("Car: " + team.getCar().getModel());
        System.out.println("Speed: " + team.getCar().getSpeedRating());
        System.out.println("Reliability: " + team.getCar().getReliabilityRating());

        Driver driver1 = new Driver("Max Sterling", "Apex Racing", 7, 95);
        Driver driver2 = new Driver("Lucas Hart", "Apex Racing", 12, 88);

        driver1.setTyreCompound(TyreCompound.MEDIUM);
        driver2.setTyreCompound(TyreCompound.SOFT);

        team.addDriver(driver1);
        team.addDriver(driver2);

        System.out.println("F1 Grand Prix Simulator");
        System.out.println("-----------------------");
        System.out.println("Team: " + team.getName());

        for (Driver driver : team.getDrivers()) {
            System.out.println(
                driver.getName() + " | Car #" + driver.getCarNumber()
                + " | Skill: " + driver.getSkillRating()
                + " | Tyres: " + driver.getTyreCompound()
            );
        }
    }
}