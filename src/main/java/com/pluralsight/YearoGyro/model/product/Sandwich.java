package com.pluralsight.YearoGyro.model.product;

import com.pluralsight.YearoGyro.model.enums.BreadType;
import com.pluralsight.YearoGyro.model.enums.SandwichSize;
import com.pluralsight.YearoGyro.model.topping.Topping;
import com.pluralsight.YearoGyro.model.topping.*;
import com.pluralsight.YearoGyro.model.enums.*;

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
        return new ArrayList<>(toppings); // Return copy so if cleared original toppings does not change
    }

    public boolean isToasted() {
        return toasted;
    }

    //enums helps here because of size and default pricing
    public double getPrice() {
        double total = size.getBasePrice();

        for (Topping topping : toppings) {
            total += topping.getPrice(size);
        }

        return total;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s %s Sandwich", size.getDisplayName(), bread.getDisplayName()));

        if (isToasted()) {
            stringBuilder.append(" (Toasted)");
        }

        if (!toppings.isEmpty()) {
            stringBuilder.append(" with ");
            for (int i = 0; i < toppings.size(); i++) {
                stringBuilder.append(toppings.get(i).toString());
                if (i < toppings.size() - 1) {
                    stringBuilder.append(", ");
                }
            }
        }

        stringBuilder.append(String.format(" - $%.2f", getPrice()));
        return stringBuilder.toString();
    }

    /**
     * Gets a detailed multi-line string for receipt printing
     */
    public String getDetailedString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s %s Sandwich%s\n",
                size.getDisplayName(),
                bread.getDisplayName(),
                toasted ? " (Toasted)" : ""));

        stringBuilder.append(String.format("  Base Price: $%.2f\n", size.getBasePrice()));

        if (!toppings.isEmpty()) {
            stringBuilder.append("  Toppings:\n");
            for (Topping topping : toppings) {
                double price = topping.getPrice(size);
                if (price > 0) {
                    stringBuilder.append(String.format("    - %s: $%.2f\n",
                            topping.getName() + (topping.isExtra() ? " (Extra)" : ""),
                            price));
                } else {
                    stringBuilder.append(String.format("    - %s\n", topping.getName()));
                }
            }
        }

        stringBuilder.append(String.format("  Total: $%.2f", getPrice()));
        return stringBuilder.toString();
    }
}