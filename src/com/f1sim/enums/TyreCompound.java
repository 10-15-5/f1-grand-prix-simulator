package com.f1sim.enums;

/**
 * Represents the three tyre compounds available in the F1 simulator.
 *
 * Each compound has different pace and durability bonuses that
 * influence the driver's performance during a race.
 */
public enum TyreCompound {
    SOFT(0.8, 1.4),
    MEDIUM(0.0, 0.7),
    HARD(0.4, 0.2);

    private final double paceBonus;
    private final double durabilityBonus;

    /**
     * Creates a tyre compound with its specified pace and durability bonuses.
     *
     * @param paceBonus the bonus applied to the driver's speed
     * @param durabilityBonus the bonus applied to tyre durability
     */
    TyreCompound(double paceBonus, double durabilityBonus) {
        this.paceBonus = paceBonus;
        this.durabilityBonus = durabilityBonus;
    }


    /**
     * Returns the pace bonus associated with this tyre compound.
     *
     * @return the pace bonus value
     */
    public double getPaceBonus() {
        return paceBonus;
    }


    /**
     * Returns the durability bonus associated with this tyre compound.
     *
     * @return the durability bonus value
     */
    public double getDurabilityBonus() {
        return durabilityBonus;
    }
}
