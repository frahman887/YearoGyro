package com.pluralsight.deli.model.product;

public class Chips {
    private static final double CHIPS_PRICE = 1.50; //price of chips does not change
    private final String type;

    public Chips(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return CHIPS_PRICE;
    }

    @Override
    public String toString() {
        return String.format("%s Chips - $%.2f", getType(), getPrice());
    }

    //shows the full type of chip will be helpful for receipts
    public String getDetailedString() {
        return getType() + " Chips";
    }
}