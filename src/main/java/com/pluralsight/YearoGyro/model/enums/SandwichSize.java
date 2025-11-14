package com.pluralsight.YearoGyro.model.enums;

//give " value and prices for all possible add ons
//since all are tied to the size i just lumped it all together
//ie price of extra meat is dependant on the size of the sandwich so when choosing small all of those
//variables have been accounted for
//no need for complicated if or for loop statements

public enum SandwichSize {
    SMALL("4\"", 5.50, 1.00, 0.50, 0.75, 0.30),
    MEDIUM("8\"", 7.00, 2.00, 1.00, 1.50, 0.60),
    LARGE("12\"", 8.50, 3.00, 1.50, 2.25, 0.90);

    private final String displayName;
    private final double basePrice;
    private final double meatPrice;
    private final double extraMeatPrice;
    private final double cheesePrice;
    private final double extraCheesePrice;

    SandwichSize(String displayName, double basePrice, double meatPrice,
                 double extraMeatPrice, double cheesePrice, double extraCheesePrice) {
        this.displayName = displayName;
        this.basePrice = basePrice;
        this.meatPrice = meatPrice;
        this.extraMeatPrice = extraMeatPrice;
        this.cheesePrice = cheesePrice;
        this.extraCheesePrice = extraCheesePrice;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getMeatPrice() {
        return meatPrice;
    }

    public double getExtraMeatPrice() {
        return extraMeatPrice;
    }

    public double getCheesePrice() {
        return cheesePrice;
    }

    public double getExtraCheesePrice() {
        return extraCheesePrice;
    }

    @Override
    public String toString() {
        return displayName;
    }
}