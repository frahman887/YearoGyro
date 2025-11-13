package com.pluralsight.deli.model.topping;

import com.pluralsight.deli.model.enums.SandwichSize;

public interface Topping {
    /**
     * Gets the name/description of the topping
     * @return The topping name
     */
    String getName();

    /**
     * Calculates the price of this topping based on sandwich size
     * @param size The size of the sandwich
     * @return The price for this topping
     */
    double getPrice(SandwichSize size);

    /**
     * Checks if this topping has extra quantity
     * @return true if extra, false otherwise
     */
    boolean isExtra();

    /**
     * Gets a formatted string for display/receipt purposes
     * @param size The sandwich size (for price display)
     * @return Formatted string representation
     */
    String getDisplayString(SandwichSize size);
}