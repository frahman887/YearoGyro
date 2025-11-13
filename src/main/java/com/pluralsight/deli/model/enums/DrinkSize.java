package com.pluralsight.deli.model.enums;
//enums just like before keeps people tied to  these size options while keeping a defined price as well
public enum DrinkSize {
    SMALL("Small", 2.00),
    MEDIUM("Medium", 2.50),
    LARGE("Large", 3.00);

    private final String displayName;
    private final double price;

    DrinkSize(String displayName, double price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return displayName;
    }
}