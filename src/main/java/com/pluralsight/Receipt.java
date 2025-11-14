package com.pluralsight;

import com.pluralsight.deli.model.product.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

// receipt and files from each order
public class Receipt {
    private static final String RECEIPTS_FOLDER = "receipts/";
    private static final DateTimeFormatter FILE_NAME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    private final Order order;

    public Receipt(Order order) {
        this.order = order;
    }

    //return the detailed receipt info from the order
    public String generateReceipt() {
        return order.getDetailedOrderString();
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