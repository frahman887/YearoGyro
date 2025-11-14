package com.pluralsight;

import com.pluralsight.YearoGyro.model.enums.*;
import com.pluralsight.YearoGyro.model.product.*;
import com.pluralsight.YearoGyro.model.topping.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

// receipt and files from each order
public class Receipt {
    private static final String RECEIPTS_FOLDER = "receipts/";
    private static final DateTimeFormatter FILE_NAME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    private static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");  // For display: 11/14/2025 03:30:00 PM
    private final Order order;

    public Receipt(Order order) {
        this.order = order;
    }

    //return the detailed receipt info from the order
    public String generateReceipt() {
    StringBuilder sb = new StringBuilder();

    // Header with box border (width: 54 characters inside)
        sb.append("\n");
        sb.append("╔══════════════════════════════════════════════════════╗\n");
        sb.append("║                    YEARO GYRO                        ║\n");
        sb.append("║               Custom Sandwich Shop                   ║\n");
        sb.append("║            123 Somewhere, NearYou, YU                ║\n");
        sb.append("║               Tel: (123) 345-6789                    ║\n");
        sb.append("╠══════════════════════════════════════════════════════╣\n");

    // Order info
    String formattedDate = order.getOrderTime().format(DISPLAY_FORMATTER);
        sb.append(String.format("║  Order Date: %-40s║\n", formattedDate));
        sb.append("╠══════════════════════════════════════════════════════╣\n");
        sb.append("║                  ORDER DETAILS                       ║\n");
        sb.append("╠══════════════════════════════════════════════════════╣\n");

    // Sandwiches
        if (!order.getSandwiches().isEmpty()) {
        sb.append("║                                                      ║\n");
        sb.append("║   SANDWICHES:                                        ║\n");
        sb.append("║  ──────────────────────────────────────────────      ║\n");
        for (int i = 0; i < order.getSandwiches().size(); i++) {
            Sandwich sandwich = order.getSandwiches().get(i);
            String sandwichInfo = String.format("  %d. %s", i + 1,
                    sandwich.getSize().getDisplayName() + " " +
                            sandwich.getBread().getDisplayName() +
                            (sandwich.isToasted() ? " (Toasted)" : ""));
            sb.append(String.format("║%-54s║\n", sandwichInfo));

            // Toppings
            for (Topping topping : sandwich.getToppings()) {
                String toppingLine = "     - " + topping.toString();
                if (topping.getPrice(sandwich.getSize()) > 0) {
                    toppingLine += String.format(" ($%.2f)", topping.getPrice(sandwich.getSize()));
                }
                if (toppingLine.length() > 54) {
                    toppingLine = toppingLine.substring(0, 51) + "...";
                }
                sb.append(String.format("║%-54s║\n", toppingLine));
            }

            String priceInfo = String.format("     Total: $%.2f", sandwich.getPrice());
            sb.append(String.format("║%-54s║\n", priceInfo));
            sb.append("║                                                      ║\n");
        }
    }

    // Drinks
        if (!order.getDrinks().isEmpty()) {
        sb.append("║   DRINKS:                                            ║\n");
        sb.append("║  ──────────────────────────────────────────────      ║\n");
        for (int i = 0; i < order.getDrinks().size(); i++) {
            Drink drink = order.getDrinks().get(i);
            String drinkLine = String.format("  %d. %s %s - $%.2f",
                    i + 1,
                    drink.getSize().getDisplayName(),
                    drink.getFlavor(),
                    drink.getPrice());
            sb.append(String.format("║%-54s║\n", drinkLine));
        }
        sb.append("║                                                      ║\n");
    }

    // Chips
        if (!order.getChips().isEmpty()) {
        sb.append("║   CHIPS:                                             ║\n");
        sb.append("║  ──────────────────────────────────────────────      ║\n");
        for (int i = 0; i < order.getChips().size(); i++) {
            Chips chip = order.getChips().get(i);
            String chipLine = String.format("  %d. %s - $%.2f",
                    i + 1,
                    chip.getType() + " Chips",
                    chip.getPrice());
            sb.append(String.format("║%-54s║\n", chipLine));
        }
        sb.append("║                                                      ║\n");
    }

    // Total
        sb.append("╠══════════════════════════════════════════════════════╣\n");
    String totalLine = String.format("   TOTAL: $%.2f", order.calculateTotal());
        sb.append(String.format("║%-54s║\n", totalLine));
        sb.append("╠══════════════════════════════════════════════════════╣\n");
        sb.append("║         Thank you for your order!                    ║\n");
        sb.append("║            Please come again!                        ║\n");
        sb.append("╚══════════════════════════════════════════════════════╝\n");

        return sb.toString();
}
    //each receipt has the time as its name like how on mac screenshots are timestamps
    private String getFileName() {
        String timestamp = order.getOrderTime().format(FILE_NAME_FORMATTER);
        return RECEIPTS_FOLDER + timestamp + ".txt";
    }

    public boolean saveToFile() {
        String fileName = getFileName();
        String receiptContent = generateReceipt();

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(receiptContent);
            System.out.println("Receipt saved successfully: " + fileName);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving receipt: " + e.getMessage());
            return false;
        }
    }


    public void printToConsole() {
        System.out.println(generateReceipt());
    }


    public String getReceiptFileName() {
        return getFileName();
    }
}