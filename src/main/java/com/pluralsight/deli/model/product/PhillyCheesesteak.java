package com.pluralsight.deli.model.product;

import com.pluralsight.deli.model.enums.*;
import com.pluralsight.deli.model.topping.*;

//Philly Cheese Steak Signature Sandwich
//8" white bread with Steak, American Cheese, Peppers, Mayo, Toasted
public class PhillyCheesesteak extends Sandwich {

    public PhillyCheesesteak() {
        // call sandwich with medium size and pricing as well as the type of bread
        super(SandwichSize.MEDIUM, BreadType.WHITE);

        // Add signature toppings / basic philly build
        addTopping(new Meat(MeatType.STEAK, false));
        addTopping(new Cheese(CheeseType.AMERICAN, false));
        addTopping(RegularTopping.peppers());
        addTopping(Sauce.mayo());

        setToasted(true);
    }

    //special creation name
    public String getSignatureName() {
        return "Philly Cheese Steak";
    }

    @Override
    public String toString() {
        return getSignatureName() + " - " + super.toString();
    }
}