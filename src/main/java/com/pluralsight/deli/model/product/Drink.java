package com.pluralsight.deli.model.product;
import com.pluralsight.deli.model.enums.*;

public class Drink {
    private final DrinkSize size;
    private final String flavor;

    public Drink(DrinkSize size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public DrinkSize getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    public double getPrice() {
        return size.getPrice();
    }

    @Override
    public String toString() {
        return String.format("%s %s - $%.2f", size.getDisplayName(), flavor, getPrice());
    }


    public String getDetailedString() {
        return String.format("%s %s Drink", size.getDisplayName(), flavor);
    }
}