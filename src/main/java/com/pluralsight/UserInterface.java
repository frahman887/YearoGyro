package com.pluralsight;

import com.pluralsight.deli.model.enums.*;
import com.pluralsight.deli.model.product.*;
import com.pluralsight.deli.model.topping.*;
import java.util.*;
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
            System.out.println("2) Add Signature Sandwich");
            System.out.println("3) Add Drink");
            System.out.println("4) Add Chips");
            System.out.println("5) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("\nSelect an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addSandwich();
                    break;
                case "2":
                    addSignatureSandwich();
                    break;
                case "3":
                    addDrink();
                    break;
                case "4":
                    addChips();
                    break;
                case "5":
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
        // Add sides
        addSides(sandwich);
        // Toast option
        System.out.print("\nWould you like the sandwich toasted? (yes/no): ");
        String toastChoice = scanner.nextLine().trim().toLowerCase();
        sandwich.setToasted(toastChoice.equals("yes") || toastChoice.equals("y"));

        // lets you view the sandwich made and lets you customize like the specialties
        System.out.println("\nSandwich created!");
        System.out.println(sandwich);
        System.out.print("\nWould you like to customize this sandwich? (yes/no): ");
        String customizeChoice = scanner.nextLine().trim().toLowerCase();

        if (customizeChoice.equals("yes") || customizeChoice.equals("y")) {
            customizeSandwich(sandwich);
        }
        currentOrder.addSandwich(sandwich);
        System.out.println("\n Sandwich added to order!");
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
        System.out.println("6) Vinaigrette");
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
                default:
                    System.out.println("invalid choice: " + choice);
            }
        }
        System.out.println("Sauces added!");
    }

    private void addSides(Sandwich sandwich) {
        boolean addingSides = true;

        while (addingSides) {
            System.out.println("\nAdd sides? (or 'done' to finish)");
            System.out.println("1) Au Jus");
            System.out.println("2) Sauce");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("done") || choice.isEmpty()) {
                addingSides = false;
                continue;
            }

            switch (choice) {
                case "1":
                    sandwich.addTopping(Side.auJus());
                    System.out.println("Au Jus added!");
                    break;
                case "2":
                    // Show the sauces menu but add as sides
                    addSideSauces(sandwich);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // extra sauces are a side kind of like those packets of ranch at McD
    private void addSideSauces(Sandwich sandwich) {
        System.out.println("\nSelect sauce for side (comma-separated numbers, or 'done'):");
        System.out.println("1) Mayo          2) Mustard       3) Ketchup");
        System.out.println("4) Ranch         5) Thousand Islands");
        System.out.println("6) Vinaigrette");
        System.out.print("Choices: ");

        String input = scanner.nextLine().trim().toLowerCase();

        if (input.equals("done") || input.isEmpty()) {
            return;
        }

        String[] choices = input.split(",");

        for (int i = 0; i < choices.length; i++) {
            String choice = choices[i].trim();

            // Add as SIDE sauce (not regular sauce)
            switch (choice) {
                case "1": sandwich.addTopping(Side.mayo()); break;
                case "2": sandwich.addTopping(Side.mustard()); break;
                case "3": sandwich.addTopping(Side.ketchup()); break;
                case "4": sandwich.addTopping(Side.ranch()); break;
                case "5": sandwich.addTopping(Side.thousandIslands()); break;
                case "6": sandwich.addTopping(Side.vinaigrette()); break;
            }
        }
    }
    private void addSignatureSandwich() {
        System.out.println("\n*** Signature Sandwiches ***\n");
        System.out.println("1) BLT"); //only the two on the capstone BLT and Philly
        System.out.println("   8\" White bread, Bacon, Cheddar, Lettuce, Tomato, Ranch (Toasted)");
        System.out.println("   $" + new BLT().getPrice());
        System.out.println();
        System.out.println("2) Philly Cheese Steak");
        System.out.println("   8\" White bread, Steak, American, Peppers, Mayo (Toasted)");
        System.out.println("   $" + new PhillyCheesesteak().getPrice());
        System.out.println();
        System.out.println("0) Go Back");
        System.out.print("\nSelect a signature sandwich: ");

        String choice = scanner.nextLine().trim();
        Sandwich sandwich = null;

        switch (choice) {
            case "1":
                sandwich = new BLT();
                System.out.println("\n BLT selected!");
                break;
            case "2":
                sandwich = new PhillyCheesesteak();
                System.out.println("\n Philly Cheese Steak selected!");
                break;
            case "0":
                return;
            default:
                System.out.println("Invalid choice. Returning to menu.");
                return;
        }

        // Show current toppings
        System.out.println("\nCurrent sandwich: " + sandwich);
        System.out.println("\nWould you like to customize this sandwich? (yes/no): ");
        String customize = scanner.nextLine().trim().toLowerCase();

        if (customize.equals("yes") || customize.equals("y")) {
            customizeSandwich(sandwich);
        }

        currentOrder.addSandwich(sandwich);
        System.out.println("\n Signature sandwich added to order!");
        System.out.println(sandwich);
    }

    //basically another menuing thing
    private void customizeSandwich(Sandwich sandwich) {
        boolean customizing = true;

        while (customizing) { //gets current toppings
            System.out.println("\n=== CUSTOMIZE SANDWICH ===");
            System.out.println("Current toppings:");
            int index = 1;
            for (Topping topping : sandwich.getToppings()) {
                System.out.println("  " + index + ") " + topping.toString());
                index++;
            }

            System.out.println("\n1) Remove a topping");
            System.out.println("2) Add more meats");
            System.out.println("3) Add more cheese");
            System.out.println("4) Add more regular toppings");
            System.out.println("5) Add more sauces");
            System.out.println("6) Add sides");
            System.out.println("7) change toasted status? (Currently: " + (sandwich.isToasted() ? "Toasted" : "Not Toasted") + ")");
            System.out.println("0) Done customizing"); //if true returns Toasted if false returns "Not Toasted"
            System.out.print("\nChoice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    removeTopping(sandwich);
                    break;
                case "2":
                    addMeats(sandwich);
                    break;
                case "3":
                    addCheese(sandwich);
                    break;
                case "4":
                    addRegularToppings(sandwich);
                    break;
                case "5":
                    addSauces(sandwich);
                    break;
                case "6":
                    addSides(sandwich);
                    break;
                case "7":
                    sandwich.setToasted(!sandwich.isToasted()); //default is not toasted
                    System.out.println(" Sandwich is now " + (sandwich.isToasted() ? "toasted" : "not toasted"));
                    break;
                case "0":
                    customizing = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    //removes if the list of toppings isnt empty
    private void removeTopping(Sandwich sandwich) {
        List<Topping> toppings = sandwich.getToppings();

        if (toppings.isEmpty()) {
            System.out.println("No toppings to remove!");
            return;
        }

        System.out.println("\nSelect topping to remove:");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println((i + 1) + ") " + toppings.get(i).toString());
            //print list with regular count ie: 1)tomato, 2)cucumber, 3) instead of 0) let, 1)toma ,2,3
            //to string shows extra and name of topping
        }
        while (true) {
            System.out.print("Choice (0 to cancel): ");
            int choice;

            try {
                choice = scanner.nextInt();   // read an int
            } catch (java.util.InputMismatchException e) { //catch for bad input
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // eat input
                continue;           // go back to top of loop
            }

            scanner.nextLine(); // consume after nextInt

            if (choice == 0) {
                System.out.println("Canceled.");
                return; // exit
            }

            if (choice > 0 && choice <= toppings.size()) {
                // has to account for 0 base counting
                Topping removed = toppings.get(choice - 1);
                sandwich.removeTopping(removed);
                System.out.println("Removed: " + removed.toString());
                break;
            } else {
                System.out.println("Invalid choice. Enter a number between 1 and "
                        + toppings.size() + " or 0 to cancel.");
            }
        }

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
            System.out.println("\nYour order is empty! Add items to check out.");
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