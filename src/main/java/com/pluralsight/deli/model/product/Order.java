package com.pluralsight.deli.model.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Sandwich> sandwiches;
    private final List<Drink> drinks;
    private final List<Chips> chips;
    private final LocalDateTime orderTime;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);

    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addChips(Chips chips) {
        this.chips.add(chips);
    }

    public List<Sandwich> getSandwiches() {
        return new ArrayList<>(sandwiches);
    }

    public List<Drink> getDrinks() {
        return new ArrayList<>(drinks);
    }

    public List<Chips> getChips() {
        return new ArrayList<>(chips);
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    //checks to see if order is valid aka at least one thing is being ordered
    public boolean isValid() {
        if (sandwiches.isEmpty()) {
            return !drinks.isEmpty() || !chips.isEmpty();  //returns the value of the statements
            // !drinks.isEmpty() is true when isEmpty is false (there is at least one drink)
            //that one drink OR chip is enough to make a valid order even if there is no sandwich
            //so when either statement is true you return true
        }
        return true; //if sandwich is not empty the order is valid
        //since that means that at least one thing that is being ordered
    }

    //order total
    public double calculateTotal() {
        double total = 0.0;

        for (Sandwich sandwich : sandwiches) { //goes through list of sandwiches
            total += sandwich.getPrice(); // total is price of each wich added up
        }

        for (Drink drink : drinks) { //same logic as above but with drinks
            total += drink.getPrice();
        }

        for (Chips chip : chips) {
            total += chip.getPrice();
        }

        return total; //after going through each sandwich and drink etc
    }

    //order summary
    //labeling borders in case i want to change how they look so i dont have to look too hard
    public String getOrderSummary() {
        //formatting
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=".repeat(50)).append("\n"); //basic border might change this moving forward
        stringBuilder.append("ORDER SUMMARY\n");
        stringBuilder.append("=".repeat(50)).append("\n\n");

        //MAKES A HEADING FOR EACH CATEGORY THEN GOES THROUGH THE LIST AND NUMBERS THEM OUT UNLESS EMPTY

        if (!sandwiches.isEmpty()) { //if at least one sandwich
            stringBuilder.append("SANDWICHES:\n");
            for (int i = 0; i < sandwiches.size(); i++) { //traverse through
                stringBuilder.append(String.format("%d. %s\n", i + 1, sandwiches.get(i)));
                //i starts from 0 so i+1 makes it like normal counting
                /*  ie SANDWICHES:
                 *  1. HAM AND CHEESE
                 */
            }
            stringBuilder.append("\n");
        }

        if (!drinks.isEmpty()) {
            stringBuilder.append("DRINKS:\n");
            for (int i = 0; i < drinks.size(); i++) {
                stringBuilder.append(String.format("%d. %s\n", i + 1, drinks.get(i)));
            }
            stringBuilder.append("\n");
        }

        if (!chips.isEmpty()) {
            stringBuilder.append("CHIPS:\n");
            for (int i = 0; i < chips.size(); i++) {
                stringBuilder.append(String.format("%d. %s\n", i + 1, chips.get(i)));
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append("-".repeat(50)).append("\n"); //border
        stringBuilder.append(String.format("TOTAL: $%.2f\n", calculateTotal())); //formatted for usd
        stringBuilder.append("=".repeat(50)).append("\n"); //border

        return stringBuilder.toString();
    }

    //gets detailed order information for receipt
    //i hate typing out the full sb but alas i have to for "readability"
    public String getDetailedOrderString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=".repeat(60)).append("\n"); //border
        stringBuilder.append("                    Yearo Gyro\n");
        stringBuilder.append("                 Order Receipt\n");
        stringBuilder.append("=".repeat(60)).append("\n"); //border
        stringBuilder.append(String.format("Order Time: %s\n", orderTime.toString()));
        stringBuilder.append("=".repeat(60)).append("\n\n"); //border

        if (!sandwiches.isEmpty()) {
            stringBuilder.append("SANDWICHES:\n");
            stringBuilder.append("-".repeat(60)).append("\n"); //border
            for (int i = 0; i < sandwiches.size(); i++) {
                stringBuilder.append(String.format("Sandwich #%d:\n", i + 1));
                stringBuilder.append(sandwiches.get(i).getDetailedString()).append("\n\n"); //get detail string of each
                //and appending them
            }
        }

        if (!drinks.isEmpty()) {
            stringBuilder.append("DRINKS:\n");
            stringBuilder.append("-".repeat(60)).append("\n");
            for (int i = 0; i < drinks.size(); i++) {
                stringBuilder.append(String.format("%d. %s - $%.2f\n",
                        i + 1,
                        drinks.get(i).getDetailedString(),
                        drinks.get(i).getPrice()));
            }
            stringBuilder.append("\n");
        }

        if (!chips.isEmpty()) {
            stringBuilder.append("CHIPS:\n");
            stringBuilder.append("-".repeat(60)).append("\n");
            for (int i = 0; i < chips.size(); i++) {
                stringBuilder.append(String.format("%d. %s - $%.2f\n",
                        i + 1,
                        chips.get(i).getDetailedString(),
                        chips.get(i).getPrice()));
            }
            stringBuilder.append("\n");
        }

        stringBuilder.append("=".repeat(60)).append("\n");
        stringBuilder.append(String.format("TOTAL: $%.2f\n", calculateTotal()));
        stringBuilder.append("=".repeat(60)).append("\n");
        stringBuilder.append("Thank you for your order!\n");

        return stringBuilder.toString();
    }

    //check if empty
    public boolean isEmpty() {
        return sandwiches.isEmpty() && drinks.isEmpty() && chips.isEmpty();
    }
}