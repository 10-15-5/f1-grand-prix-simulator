package com.f1sim.enums;

public enum TyreCompound {
    SOFT(0.8, 1.4),
    MEDIUM(0.0, 0.7),
    HARD(0.4, 0.2);

    private final double paceBonus;
    private final double durabilityBonus;

    TyreCompound(double paceBonus, double durabilityBonus) {
        this.paceBonus = paceBonus;
        this.durabilityBonus = durabilityBonus;
    }

    public double getPaceBonus() {
        return paceBonus;
    }

    public double getDurabilityBonus() {
        return durabilityBonus;
    }
}
