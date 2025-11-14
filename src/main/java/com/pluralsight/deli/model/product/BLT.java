package com.pluralsight.deli.model.product;

import com.pluralsight.deli.model.enums.*;
import com.pluralsight.deli.model.topping.*;

/**
 * BLT Signature Sandwich
 * 8" white bread with Bacon, Cheddar, Lettuce, Tomato, Ranch, Toasted
 */
public class BLT extends Sandwich {

    public BLT() {
        // Call sandwich parent constructor with bread size and pricing and type being white bread
        super(SandwichSize.MEDIUM, BreadType.WHITE);

        // Add signature toppings for basic BLT
        addTopping(new Meat(MeatType.BACON, false));
        addTopping(new Cheese(CheeseType.CHEDDAR, false));
        addTopping(RegularTopping.lettuce());
        addTopping(RegularTopping.tomatoes());
        addTopping(Sauce.ranch());

        // Toast it
        setToasted(true);
    }

    //name this build BLT
    public String getSignatureName() {
        return "BLT";
    }

    @Override
    public String toString() {
        return getSignatureName() + " - " + super.toString();
    }
}
