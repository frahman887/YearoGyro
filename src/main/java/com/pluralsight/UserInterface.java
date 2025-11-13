package com.pluralsight;

import com.pluralsight.deli.model.enums.*;
import com.pluralsight.deli.model.product.*;
import com.pluralsight.deli.model.topping.*;
import java.util.Scanner;

//Warning most of UI is just: run a method that takes int input; runs cases based on that. very simple stuff
public class UserInterface {
    private final Scanner scanner;
    private Order currentOrder;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            displayHomeScreen();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    startNewOrder();
                    break;
                case "0":
                    System.out.println("Thank you for visiting Yearo Gyro! Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    //Home screen
    private void displayHomeScreen() {
        System.out.println("\n" + "=".repeat(50)); //yes i searched up how to make a border without typing it all
        System.out.println("         Welcome to Yearo Gyro!");
        System.out.println("=".repeat(50)); //makes the code cleaner
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("\nPlease select an option: ");
    }

    private void startNewOrder() {
        currentOrder = new Order();
        System.out.println("\n*** Starting New Order ***\n");
        displayOrderScreen();
    }

    //Order screen
    private void displayOrderScreen() {
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("ORDER MENU");
            System.out.println("=".repeat(50));
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addSandwich();
                    break;
                case "2":
                    addDrink();
                    break;
                case "3":
                    addChips();
                    break;
                case "4":
                    checkout();
                    ordering = false;
                    break;
                case "0":
                    System.out.println("Order cancelled.");
                    currentOrder = null;
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    //subway style ordering methodology
    private void addSandwich() {
        System.out.println("\n*** Add Sandwich ***\n");

        // Select size
        SandwichSize size = selectSize();
        if (size == null) return;

        // Select bread
        BreadType bread = selectBread();
        if (bread == null) return;

        // Create sandwich
        Sandwich sandwich = new Sandwich(size, bread);

        // Add meats
        addMeats(sandwich);

        // Add cheese
        addCheese(sandwich);

        // Add regular toppings
        addRegularToppings(sandwich);

        // Add sauces
        addSauces(sandwich);

        // Toast option
        System.out.print("\nWould you like the sandwich toasted? (yes/no): ");
        String toastChoice = scanner.nextLine().trim().toLowerCase();
        sandwich.setToasted(toastChoice.equals("yes") || toastChoice.equals("y"));

        currentOrder.addSandwich(sandwich);
        System.out.println("\n✓ Sandwich added to order!");
        System.out.println(sandwich);
    }


    private SandwichSize selectSize() {
        System.out.println("Select sandwich size:");
        System.out.println("1) 4\" - $" + SandwichSize.SMALL.getBasePrice());
        System.out.println("2) 8\" - $" + SandwichSize.MEDIUM.getBasePrice());
        System.out.println("3) 12\" - $" + SandwichSize.LARGE.getBasePrice());
        System.out.print("Choice: ");

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1": return SandwichSize.SMALL;
            case "2": return SandwichSize.MEDIUM;
            case "3": return SandwichSize.LARGE;
            default:
                System.out.println("Invalid choice. Sandwich cancelled.");
                return null;
        }
    }


    private BreadType selectBread() {
        System.out.println("\nSelect bread type:");
        System.out.println("1) White");
        System.out.println("2) Wheat");
        System.out.println("3) Rye");
        System.out.println("4) Wrap");
        System.out.print("Choice: ");

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1": return BreadType.WHITE;
            case "2": return BreadType.WHEAT;
            case "3": return BreadType.RYE;
            case "4": return BreadType.WRAP;
            default:
                System.out.println("Invalid choice. Sandwich cancelled.");
                return null;
        }
    }


    private void addMeats(Sandwich sandwich) {
        boolean addingMeats = true;

        while (addingMeats) {
            System.out.println("\nAdd meat? (or 'done' to finish)");
            System.out.println("1) Steak");
            System.out.println("2) Ham");
            System.out.println("3) Salami");
            System.out.println("4) Roast Beef");
            System.out.println("5) Chicken");
            System.out.println("6) Bacon");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("done")) {
                addingMeats = false;
                continue;
            }

            MeatType meatType = null;
            switch (choice) {
                case "1": meatType = MeatType.STEAK; break;
                case "2": meatType = MeatType.HAM; break;
                case "3": meatType = MeatType.SALAMI; break;
                case "4": meatType = MeatType.ROAST_BEEF; break;
                case "5": meatType = MeatType.CHICKEN; break;
                case "6": meatType = MeatType.BACON; break;
                default:
                    System.out.println("Invalid choice.");
                    continue;
            }
// extra option
            System.out.print("Extra meat? (yes/no): ");
            String extraChoice = scanner.nextLine().trim().toLowerCase();
            //normalize to lower case
            boolean extra = extraChoice.equals("yes") || extraChoice.equals("y");
//check for yes or y
            sandwich.addTopping(new Meat(meatType, extra));

            System.out.println(meatType.getDisplayName() + (extra ? " (Extra)" : "") + " added!");
            //gets meat type if extra is true "Extra added" else "x added"
        }
    }


    private void addCheese(Sandwich sandwich) {
        boolean addingCheese = true;

        while (addingCheese) {
            System.out.println("\nAdd cheese? (or 'done' to finish)");
            System.out.println("1) American");
            System.out.println("2) Provolone");
            System.out.println("3) Cheddar");
            System.out.println("4) Swiss");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("done")) {
                addingCheese = false;
                continue;
            }

            CheeseType cheeseType = null;
            switch (choice) {
                case "1": cheeseType = CheeseType.AMERICAN; break;
                case "2": cheeseType = CheeseType.PROVOLONE; break;
                case "3": cheeseType = CheeseType.CHEDDAR; break;
                case "4": cheeseType = CheeseType.SWISS; break;
                default:
                    System.out.println("Invalid choice.");
                    continue;
            }

            System.out.print("Extra cheese? (yes/no): ");
            String extraChoice = scanner.nextLine().trim().toLowerCase();
            boolean extra = extraChoice.equals("yes") || extraChoice.equals("y");

            sandwich.addTopping(new Cheese(cheeseType, extra));
            System.out.println(cheeseType.getDisplayName() + (extra ? " (Extra)" : "") + " added!");
        }
    }

    //standard toppings
    private void addRegularToppings(Sandwich sandwich) {
        System.out.println("\nAdd regular toppings (comma-separated numbers, or 'done'):");
        System.out.println("1) Lettuce    2) Peppers    3) Onions");
        System.out.println("4) Tomatoes   5) Jalapeños  6) Cucumbers");
        System.out.println("7) Pickles    8) Guacamole  9) Mushrooms");
        System.out.print("Choices: ");

        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("done") || input.isEmpty()) { // no toppings or whatever chosen
            return;
        }

        String[] choices = input.split(","); //take comma separated values and split by ,
        //each string value is now read
        for (String choice : choices) {
            choice = choice.trim();
            switch (choice) {
                case "1": sandwich.addTopping(RegularTopping.lettuce()); break;
                case "2": sandwich.addTopping(RegularTopping.peppers()); break;
                case "3": sandwich.addTopping(RegularTopping.onions()); break;
                case "4": sandwich.addTopping(RegularTopping.tomatoes()); break;
                case "5": sandwich.addTopping(RegularTopping.jalapenos()); break;
                case "6": sandwich.addTopping(RegularTopping.cucumbers()); break;
                case "7": sandwich.addTopping(RegularTopping.pickles()); break;
                case "8": sandwich.addTopping(RegularTopping.guacamole()); break;
                case "9": sandwich.addTopping(RegularTopping.mushrooms()); break;
                default:
                    System.out.println("invalid choice: " + choice); //if messed up; invalid
            }
        }
        System.out.println("Regular toppings added!");
    }


    private void addSauces(Sandwich sandwich) {
        System.out.println("\nAdd sauces (comma-separated numbers, or 'done'):");
        System.out.println("1) Mayo          2) Mustard       3) Ketchup");
        System.out.println("4) Ranch         5) Thousand Islands");
        System.out.println("6) Vinaigrette   7) Au Jus");
        System.out.print("Choices: ");

        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("done") || input.isEmpty()) {
            return;
        }

        String[] choices = input.split(",");
        for (String choice : choices) {
            choice = choice.trim();
            switch (choice) {
                case "1": sandwich.addTopping(Sauce.mayo()); break;
                case "2": sandwich.addTopping(Sauce.mustard()); break;
                case "3": sandwich.addTopping(Sauce.ketchup()); break;
                case "4": sandwich.addTopping(Sauce.ranch()); break;
                case "5": sandwich.addTopping(Sauce.thousandIslands()); break;
                case "6": sandwich.addTopping(Sauce.vinaigrette()); break;
                case "7": sandwich.addTopping(Sauce.auJus()); break;
                default:
                    System.out.println("invalid choice: " + choice);
            }
        }
        System.out.println("Sauces added!");
    }


    private void addDrink() {
        System.out.println("\n*** Add Drink ***\n");

        // Select size
        System.out.println("Select drink size:");
        System.out.println("1) Small - $" + DrinkSize.SMALL.getPrice());
        System.out.println("2) Medium - $" + DrinkSize.MEDIUM.getPrice());
        System.out.println("3) Large - $" + DrinkSize.LARGE.getPrice());
        System.out.print("Choice: ");

        String sizeChoice = scanner.nextLine().trim();
        DrinkSize size = null;

        switch (sizeChoice) {
            case "1": size = DrinkSize.SMALL; break;
            case "2": size = DrinkSize.MEDIUM; break;
            case "3": size = DrinkSize.LARGE; break;
            default:
                System.out.println("Invalid choice. Drink cancelled.");
                return;
        }

        // Select flavor
        System.out.print("\nEnter drink flavor: ");
        String flavor = scanner.nextLine().trim();

        if (flavor.isEmpty()) {
            System.out.println("Drink cancelled.");
            return;
        }

        Drink drink = new Drink(size, flavor);
        currentOrder.addDrink(drink);
        System.out.println("\nDrink added to order!");
        System.out.println(drink);
    }


    private void addChips() {
        System.out.println("\n*** Add Chips ***\n");
        System.out.print("Enter chip type: ");
        String type = scanner.nextLine().trim();

        if (type.isEmpty()) {
            System.out.println("Chips cancelled.");
            return;
        }

        Chips chips = new Chips(type);
        currentOrder.addChips(chips);
        System.out.println("\n Chips added to order!");
        System.out.println(chips);
    }


    private void checkout() {
        if (currentOrder.isEmpty()) {
            System.out.println("\nYour order is empty! Please add items before checking out.");
            return;
        }

        if (!currentOrder.isValid()) {
            System.out.println("\nInvalid order! If you don't order a sandwich, you must order chips or a drink.");
            return;
        }

        System.out.println("\n" + currentOrder.getOrderSummary());

        System.out.print("Confirm order? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("yes") || confirm.equals("y")) {
            Receipt receipt = new Receipt(currentOrder); //receipt object
            receipt.printToConsole();

            if (receipt.saveToFile()) {
                System.out.println("\n Order completed successfully!");
                System.out.println("Receipt saved to: " + receipt.getReceiptFileName());
            } else {
                System.out.println("\n Order completed but receipt could not be saved.");
            }

            currentOrder = null;
        } else {
            System.out.println("\nOrder cancelled. Returning to home screen.");
            currentOrder = null;
        }
    }
}