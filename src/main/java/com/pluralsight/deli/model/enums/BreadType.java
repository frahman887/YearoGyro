package com.pluralsight.deli.model.enums;
//enums stops customers from getting stuff outside of the options also helps check for things
//in "range" so i do dont have to go around using try catch or qualifying if statements
//yes i had a discussion with the goat Ajith and Malika about the usage of enums
//even though we didnt go over it in class

public enum BreadType {
    WHITE("White"),
    WHEAT("Wheat"),
    RYE("Rye"),
    WRAP("Wrap");

    private final String displayName;
// displays type of bread
    BreadType(String displayName) {
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