package com.pluralsight.YearoGyro.model.enums;

public enum MeatType {
    STEAK("Steak"),
    HAM("Ham"),
    SALAMI("Salami"),
    ROAST_BEEF("Roast Beef"),
    CHICKEN("Chicken"),
    BACON("Bacon");

    private final String displayName;

    MeatType(String displayName) {
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
