package com.retail.app.util;

import java.io.*;
import java.util.regex.*;

public class OrderIDGenerator {
    private static int nextOrderNumber = 1;
    private static boolean initialized = false;

    private static void initialize() {
        if (initialized) return;
        initialized = true;
        File file = new File("orders.txt");
        if (!file.exists()) return;
        int max = 0;
        Pattern pattern = Pattern.compile("^A(\\d{3})");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length > 0) {
                    Matcher m = pattern.matcher(parts[0]);
                    if (m.find()) {
                        int num = Integer.parseInt(m.group(1));
                        if (num > max) max = num;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            // ignore, fallback to 1
        }
        nextOrderNumber = max + 1;
    }

    // Trả về OrderID tiếp theo mà không làm tăng giá trị
    public static String peekNextOrderId() {
        initialize();
        return String.format("A%03d", nextOrderNumber);
    }

    // Trả về OrderID và tăng giá trị cho đơn hàng tiếp theo
    public static String consumeOrderId() {
        initialize();
        String orderId = peekNextOrderId();
        nextOrderNumber++;
        return orderId;
    }
}
