package com.retail.app.service;

import com.retail.app.model.Order;
import com.retail.app.model.OrderItem;
import com.retail.app.model.Product;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService {
    private final String fileName = "orders.txt";
    private List<Order> orderList;

    public OrderService() {
        orderList = loadOrdersFromFile();
    }

    public List<Order> getAllOrders() {
        return orderList;
    }

    public boolean addOrder(Order order) {
        if (order == null || order.getOrderId().trim().isEmpty()) {
            return false;
        }
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        orderList.add(order);
        return saveOrdersToFile();
    }

    public boolean updateOrder(Order updatedOrder) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderId().equals(updatedOrder.getOrderId())) {
                orderList.set(i, updatedOrder);
                return saveOrdersToFile();
            }
        }
        return false;
    }

    public boolean deleteOrder(String orderId) {
        boolean removed = orderList.removeIf(order -> order.getOrderId().equals(orderId));
        if (removed) {
            return saveOrdersToFile();
        }
        return false;
    }

    public boolean exportPayment(Order order, String paymentMethod) {
        String fileName = "order_payment.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            writer.write("Order ID: " + order.getOrderId() +
                    ", Customer: " + order.getCustomerName() +
                    ", Phone: " + order.getCustomerPhone() +
                    ", Total: " + order.getTotalAmount() +
                    ", Order Date: " + dateFormat.format(order.getOrderDate()) +
                    ", Status: " + order.getStatus() +
                    ", Payment Method: " + paymentMethod);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<Order> loadOrdersFromFile() {
        List<Order> loadedOrders = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return loadedOrders;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    String orderId = parts[0];
                    String customerName = parts[1];
                    String customerPhone = parts[2];
                    String orderNote = parts[3];
                    double totalAmount = Double.parseDouble(parts[4]);
                    Date orderDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(parts[5]);
                    String status = parts[6];
                    loadedOrders.add(new Order(orderId, customerName, customerPhone, orderNote, totalAmount, orderDate, status, new ArrayList<>()));
                }
            }
        } catch (IOException | NumberFormatException | java.text.ParseException e) {
            e.printStackTrace();
        }
        return loadedOrders;
    }

    private boolean saveOrdersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            for (Order order : orderList) {
                writer.write(order.getOrderId() + "|" +
                        order.getCustomerName() + "|" +
                        order.getCustomerPhone() + "|" +
                        order.getOrderNote() + "|" +
                        order.getTotalAmount() + "|" +
                        dateFormat.format(order.getOrderDate()) + "|" +
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
