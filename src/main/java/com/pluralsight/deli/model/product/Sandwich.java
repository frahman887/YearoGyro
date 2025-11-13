package com.pluralsight.deli.model.product;

import com.pluralsight.deli.model.topping.*;
import com.pluralsight.deli.model.enums.*;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private final SandwichSize size;
    private final BreadType bread;
    private final List<Topping> toppings;
    private boolean toasted;

    public Sandwich(SandwichSize size, BreadType bread) {
        this.size = size;
        this.bread = bread;
        this.toppings = new ArrayList<>();
        this.toasted = false;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public SandwichSize getSize() {
        return size;
    }

    public BreadType getBread() {
        return bread;
    }

    public List<Topping> getToppings() {
        return new ArrayList<>(toppings); // Return copy for encapsulation
    }

    public boolean isToasted() {
        return toasted;
    }

    /**
     * Calculates the total price of this sandwich
     * Base price (includes bread) + all topping prices
     */
    public double getPrice() {
        double total = size.getBasePrice();

        for (Topping topping : toppings) {
            total += topping.getPrice(size);
        }

        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s Sandwich", size.getDisplayName(), bread.getDisplayName()));

        if (toasted) {
            sb.append(" (Toasted)");
        }

        if (!toppings.isEmpty()) {
            sb.append(" with ");
            for (int i = 0; i < toppings.size(); i++) {
                sb.append(toppings.get(i).getName());
                if (i < toppings.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        sb.append(String.format(" - $%.2f", getPrice()));
        return sb.toString();
    }

    /**
     * Gets a detailed multi-line string for receipt printing
     */
    public String getDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s Sandwich%s\n",
                size.getDisplayName(),
                bread.getDisplayName(),
                toasted ? " (Toasted)" : ""));

        sb.append(String.format("  Base Price: $%.2f\n", size.getBasePrice()));

        if (!toppings.isEmpty()) {
            sb.append("  Toppings:\n");
            for (Topping topping : toppings) {
                double price = topping.getPrice(size);
                if (price > 0) {
                    sb.append(String.format("    - %s: $%.2f\n",
                            topping.getName() + (topping.isExtra() ? " (Extra)" : ""),
                            price));
                } else {
                    sb.append(String.format("    - %s\n", topping.getName()));
                }
            }
        }

        sb.append(String.format("  Total: $%.2f", getPrice()));
        return sb.toString();
    }
}