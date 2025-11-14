package com.pluralsight.YearoGyro.model.topping;

import com.pluralsight.YearoGyro.model.enums.SandwichSize;

public class Side implements Topping {
    private final String name;

    public Side(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name + " (side)";
    }

    @Override
    public double getPrice(SandwichSize size) {
        return 0.0; // Sides are free
    }

    @Override
    public boolean isExtra() {
        return false;
    }

    @Override
    public String getDisplayString(SandwichSize size) {
        return name + " (side)";
    }

    @Override
    public String toString() {
        return name + " (side)";
    }

    // extra sauces are sides like the packets you get in McD
    // Au jus is in a separate container too so it's a side
    public static Side auJus() {
        return new Side("Au Jus");
    }

    public static Side mayo() {
        return new Side("Mayo");
    }

    public static Side mustard() {
        return new Side("Mustard");
    }

    public static Side ketchup() {
        return new Side("Ketchup");
    }

    public static Side ranch() {
        return new Side("Ranch");
    }

    public static Side thousandIslands() {
        return new Side("Thousand Islands");
    }

    public static Side vinaigrette() {
        return new Side("Vinaigrette");
    }
}