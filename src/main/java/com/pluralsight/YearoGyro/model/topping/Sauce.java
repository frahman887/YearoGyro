package com.pluralsight.YearoGyro.model.topping;

import com.pluralsight.YearoGyro.model.enums.*;

public class Sauce implements Topping {
    private final String name;

    public Sauce(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice(SandwichSize size) {
        return 0.0; // Sauces are free
    }

    @Override
    public boolean isExtra() {
        return false; // Sauces don't have "extra" option
    }

    @Override
    public String getDisplayString(SandwichSize size) {
        return name; // No price needed since it's free
    }

    @Override
    public String toString() {
        return name;
    }

    // basic methods for common sauces
    public static Sauce mayo() {
        return new Sauce("Mayo");
    }

    public static Sauce mustard() {
        return new Sauce("Mustard");
    }

    public static Sauce ketchup() {
        return new Sauce("Ketchup");
    }

    public static Sauce ranch() {
        return new Sauce("Ranch");
    }

    public static Sauce thousandIslands() {
        return new Sauce("Thousand Islands");
    }

    public static Sauce vinaigrette() {
        return new Sauce("Vinaigrette");
    }

    public static Sauce auJus() {
        return new Sauce("Au Jus");
    }
}