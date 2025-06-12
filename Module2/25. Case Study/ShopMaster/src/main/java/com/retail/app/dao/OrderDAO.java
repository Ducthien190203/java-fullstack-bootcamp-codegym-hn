package com.retail.app.dao;

import com.retail.app.model.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private final String fileName = "orders.txt";

    public List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return orders;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 6) {
                    Order order = new Order();
                    order.setOrderId(parts[0]);
                    order.setCustomerName(parts[1]);
                    order.setCustomerPhone(parts[2]);
                    order.setOrderNote(parts[3]);
                    order.setTotalAmount(Double.parseDouble(parts[4]));
                    order.setStatus(parts[5]);
                    orders.add(order);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public boolean saveOrders(List<Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Order order : orders) {
                writer.write(order.getOrderId() + "|" +
                        order.getCustomerName() + "|" +
                        order.getCustomerPhone() + "|" +
                        order.getOrderNote() + "|" +
                        order.getTotalAmount() + "|" +
                        order.getStatus());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
