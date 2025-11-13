package com.pluralsight.deli.model.topping;

import com.pluralsight.deli.model.enums.*;

public class RegularTopping implements Topping {
    private final String name;

    public RegularTopping(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice(SandwichSize size) {
        return 0.0; // Regular toppings are free
    }

    @Override
    public boolean isExtra() {
        return false; // Regular toppings don't have "extra" option
    }

    @Override
    public String getDisplayString(SandwichSize size) {
        return name; // No price needed since it's free
    }

    @Override
    public String toString() {
        return name;
    }

    // Static factory methods for common regular toppings
    public static RegularTopping lettuce() {
        return new RegularTopping("Lettuce");
    }

    public static RegularTopping peppers() {
        return new RegularTopping("Peppers");
    }

    public static RegularTopping onions() {
        return new RegularTopping("Onions");
    }

    public static RegularTopping tomatoes() {
        return new RegularTopping("Tomatoes");
    }

    public static RegularTopping jalapenos() {
        return new RegularTopping("Jalapeños");
    }

    public static RegularTopping cucumbers() {
        return new RegularTopping("Cucumbers");
    }

    public static RegularTopping pickles() {
        return new RegularTopping("Pickles");
    }

    public static RegularTopping guacamole() {
        return new RegularTopping("Guacamole");
    }

    public static RegularTopping mushrooms() {
        return new RegularTopping("Mushrooms");
    }
}