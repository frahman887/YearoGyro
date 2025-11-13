package com.pluralsight;

import com.pluralsight.deli.model.product.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Handles receipt generation and file saving
 */
public class Receipt {
    private static final String RECEIPTS_FOLDER = "receipts/";
    private static final DateTimeFormatter FILE_NAME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    private final Order order;

    public Receipt(Order order) {
        this.order = order;
    }

    /**
     * Generates the receipt content as a string
     */
    public String generateReceipt() {
        return order.getDetailedOrderString();
    }

    /**
     * Generates the filename for this receipt based on order time
     * Format: yyyyMMdd-HHmmss.txt (e.g., 20230329-121523.txt)
     */
    private String getFileName() {
        String timestamp = order.getOrderTime().format(FILE_NAME_FORMATTER);
        return RECEIPTS_FOLDER + timestamp + ".txt";
    }

    /**
     * Saves the receipt to a file in the receipts folder
     * @return true if successful, false otherwise
     */
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

    /**
     * Prints the receipt to console
     */
    public void printToConsole() {
        System.out.println(generateReceipt());
    }

    /**
     * Gets the filename that will be used for this receipt
     */
    public String getReceiptFileName() {
        return getFileName();
    }
}