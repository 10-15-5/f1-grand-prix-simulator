package com.f1sim.models;

import com.f1sim.enums.TyreCompound;

public class Driver {

    private String name;
    private String team;
    private int carNumber;
    private int skillRating;
    private TyreCompound tyreCompound;

    public Driver(String name, String team, int carNumber, int skillRating) {
        if (skillRating < 1 || skillRating > 100) {
            throw new IllegalArgumentException("Skill rating must be between 1 and 100.");
        }

        if (carNumber < 1) {
            throw new IllegalArgumentException("Car number must be positive.");
        }

        this.name = name;
        this.team = team;
        this.carNumber = carNumber;
        this.skillRating = skillRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        if (carNumber < 1) {
            throw new IllegalArgumentException("Car number must be positive.");
        }
        this.carNumber = carNumber;
    }

    public int getSkillRating() {
        return skillRating;
    }

    public void setSkillRating(int skillRating) {
        if (skillRating < 1 || skillRating > 100) {
            throw new IllegalArgumentException("Skill rating must be between 1 and 100.");
        }
        this.skillRating = skillRating;
    }

    public TyreCompound getTyreCompound() {
        return tyreCompound;
    }

    public void setTyreCompound(TyreCompound tyreCompound) {
        this.tyreCompound = tyreCompound;
    }

}