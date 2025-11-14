package com.pluralsight.YearoGyro.model.topping;
import com.pluralsight.YearoGyro.model.enums.CheeseType;
import com.pluralsight.YearoGyro.model.enums.SandwichSize;
import com.pluralsight.YearoGyro.model.enums.*;

public class Cheese implements Topping {
    private final CheeseType type;
    private final boolean extra;

    public Cheese(CheeseType type, boolean extra) {
        this.type = type;
        this.extra = extra;
    }

    @Override
    public String getName() {
        return type.getDisplayName();
    }

    @Override
    public double getPrice(SandwichSize size) {
        double basePrice = size.getCheesePrice();
        if (extra) {
            basePrice += size.getExtraCheesePrice();
        }
        return basePrice;
    }

    @Override
    public boolean isExtra() {
        return extra;
    }

    public CheeseType getType() {
        return type;
    }

    @Override
    public String getDisplayString(SandwichSize size) {
        String extraText = extra ? " (Extra)" : ""; //if true return extra else nothing
        return String.format("%s%s - $%.2f", type.getDisplayName(), extraText, getPrice(size));
    }

    @Override
    public String toString() {
        return extra ? type.getDisplayName() + " (Extra)" : type.getDisplayName(); //if return extra else just type
    }
}