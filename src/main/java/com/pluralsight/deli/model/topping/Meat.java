package com.pluralsight.deli.model.topping;
import com.pluralsight.deli.model.enums.*;

public class Meat implements Topping {
    private final MeatType type;
    private final boolean extra;

    public Meat(MeatType type, boolean extra) {
        this.type = type;
        this.extra = extra;
    }

    @Override
    public String getName() {
        return type.getDisplayName();
    }

    @Override
    public double getPrice(SandwichSize size) {
        double basePrice = size.getMeatPrice();
        if (extra) {
            basePrice += size.getExtraMeatPrice();
        }
        return basePrice;
    }

    @Override
    public boolean isExtra() {
        return extra;
    }

    public MeatType getType() {
        return type;
    }

    @Override
    public String getDisplayString(SandwichSize size) {
        String extraText = extra ? " (Extra)" : "";
        return String.format("%s%s - $%.2f", type.getDisplayName(), extraText, getPrice(size));
    }
//same as cheese
    @Override
    public String toString() {
        return extra ? type.getDisplayName() + " (Extra)" : type.getDisplayName();
    }
}