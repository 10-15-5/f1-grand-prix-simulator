package com.f1sim.models;

import com.f1sim.enums.TyreCompound;

/**
 * Represents a driver participating in the F1 simulator.
 *
 * Stores the driver's personal details, team, car number,
 * skill rating and currently selected tyre.
 * Provides methods to access and update these details.
 */
public class Driver {
    private String name;
    private String team;
    private int carNumber;
    private int skillRating;
    private TyreCompound tyreCompound;


    /**
     * Creates a driver with the supplied details and assigns medium tyres by default.
     *
     * Uses constructor chaining to call the second constructor.
     *
     * @param name the driver's name
     * @param team the team the driver belongs to
     * @param carNumber the driver's car number
     * @param skillRating the driver's skill rating (1-100)
     */
    public Driver(String name, String team, int carNumber, int skillRating) {
        this(name, team, carNumber, skillRating, TyreCompound.MEDIUM);
    }


    /**
     * Creates a driver with the supplied details and tyre compound.
     *
     * Validates the driver's name, team, car number and skill rating.
     * If no tyre compound is supplied, medium tyres are assigned by default.
     *
     * @param name the driver's name
     * @param team the team the driver belongs to
     * @param carNumber the driver's car number
     * @param skillRating the driver's skill rating (1-100)
     * @param tyreCompound the driver's selected tyre compound
     * @throws IllegalArgumentException if the name or team is blank,
     * the car number is invalid or the skill rating is outside the permitted range
     */
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


    /**
     * Checks whether a driver's skill rating is within the permitted range.
     *
     * @param rating the skill rating being checked
     * @throws IllegalArgumentException if the rating is below 1 or above 100
    */
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


    /**
     * Returns a readable text representation of the driver.
     *
     * Displays the driver's name and car number.
     *
     * @return the driver's name and car number in a formatted String
    */
    @Override
    public String toString() {
        return name + " (#" + carNumber + ")";
    }
}
