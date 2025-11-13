package com.pluralsight.deli.model.topping;

import com.pluralsight.deli.model.enums.SandwichSize;

public interface Topping {
    //name of topping
    String getName();

    //takes size and returns price of topping
    double getPrice(SandwichSize size);

    //checks if topping is extra
    boolean isExtra();

    // formatted return for receipt or display
    String getDisplayString(SandwichSize size);
}