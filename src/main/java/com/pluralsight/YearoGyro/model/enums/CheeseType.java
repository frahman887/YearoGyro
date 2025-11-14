package com.pluralsight.YearoGyro.model.enums;

public enum CheeseType {
    AMERICAN("American"),
    PROVOLONE("Provolone"),
    CHEDDAR("Cheddar"),
    SWISS("Swiss");

    private final String displayName;
    //type of cheese = displayname
    CheeseType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}