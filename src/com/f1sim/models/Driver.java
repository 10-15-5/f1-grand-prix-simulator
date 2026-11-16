package com.f1sim.models;

import com.f1sim.enums.TyreCompound;

public class Driver {
    private String name;
    private String team;
    private int carNumber;
    private int skillRating;
    private TyreCompound tyreCompound;

    public Driver(String name, String team, int carNumber, int skillRating) {
        this(name, team, carNumber, skillRating, TyreCompound.MEDIUM);
    }

    public Driver(String name, String team, int carNumber, int skillRating,
                  TyreCompound tyreCompound) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Driver name cannot be blank.");
        }
        if (team == null || team.isBlank()) {
            throw new IllegalArgumentException("Team name cannot be blank.");
        }
        if (carNumber < 1) {
            throw new IllegalArgumentException("Car number must be positive.");
        }
        validateSkillRating(skillRating);
        this.name = name;
        this.team = team;
        this.carNumber = carNumber;
        this.skillRating = skillRating;
        this.tyreCompound = tyreCompound == null ? TyreCompound.MEDIUM : tyreCompound;
    }

    private static void validateSkillRating(int rating) {
        if (rating < 1 || rating > 100) {
            throw new IllegalArgumentException("Skill rating must be between 1 and 100.");
        }
    }

    public String getName() { return name; }
    public String getTeam() { return team; }
    public int getCarNumber() { return carNumber; }
    public int getSkillRating() { return skillRating; }
    public TyreCompound getTyreCompound() { return tyreCompound; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be blank.");
        this.name = name;
    }

    public void setTeam(String team) {
        if (team == null || team.isBlank()) throw new IllegalArgumentException("Team cannot be blank.");
        this.team = team;
    }

    public void setCarNumber(int carNumber) {
        if (carNumber < 1) throw new IllegalArgumentException("Car number must be positive.");
        this.carNumber = carNumber;
    }

    public void setSkillRating(int skillRating) {
        validateSkillRating(skillRating);
        this.skillRating = skillRating;
    }

    public void setTyreCompound(TyreCompound tyreCompound) {
        if (tyreCompound == null) throw new IllegalArgumentException("Tyre compound is required.");
        this.tyreCompound = tyreCompound;
    }

    @Override
    public String toString() {
        return name + " (#" + carNumber + ")";
    }
}
