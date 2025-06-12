package com.retail.app.dao;

import com.retail.app.model.Order;
import com.retail.app.model.OrderItem;
import com.retail.app.model.Product;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private final String fileName = "orders.csv";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public boolean saveOrdersToFile(List<Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Order order : orders) {
                writer.write(escape(order.getOrderId()) + "," +
                        escape(order.getCustomerName()) + "," +
                        escape(order.getCustomerPhone()) + "," +
                        escape(order.getOrderNote()) + "," +
                        order.getTotalAmount() + "," +
                        (order.getOrderDate() != null ? dateFormat.format(order.getOrderDate()) : "") + "," +
                        escape(order.getStatus()) + "," +
                        escape(serializeItems(order.getItems())));
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Order> loadOrdersFromFile() {
        List<Order> orders = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return orders;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = splitCsv(line, 8);
                // orderId,customerName,customerPhone,orderNote,totalAmount,orderDate,status,items
                if (parts.length >= 8) {
                    Order order = new Order();
                    order.setOrderId(unescape(parts[0]));
                    order.setCustomerName(unescape(parts[1]));
                    order.setCustomerPhone(unescape(parts[2]));
                    order.setOrderNote(unescape(parts[3]));
                    try {
                        order.setTotalAmount(Double.parseDouble(parts[4]));
                    } catch (NumberFormatException e) {
                        order.setTotalAmount(0);
                    }
                    try {
                        order.setOrderDate(parts[5].isEmpty() ? null : dateFormat.parse(parts[5]));
                    } catch (ParseException e) {
                        order.setOrderDate(null);
                    }
                    order.setStatus(unescape(parts[6]));
                    order.setItems(deserializeItems(unescape(parts[7])));
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private String serializeItems(List<OrderItem> items) {
        StringBuilder sb = new StringBuilder();
        for (OrderItem item : items) {
            if (sb.length() > 0) sb.append(";");
            Product p = item.getProduct();
            sb.append(escape(p.getProductId())).append(":")
                    .append(escape(p.getName())).append(":")
                    .append(item.getQuantity()).append(":")
                    .append(p.getPrice());
        }
        return sb.toString();
    }

    private List<OrderItem> deserializeItems(String itemsStr) {
        List<OrderItem> items = new ArrayList<>();
        if (itemsStr == null || itemsStr.trim().isEmpty()) return items;
        String[] itemArr = itemsStr.split(";");
        for (String itemStr : itemArr) {
            String[] parts = itemStr.split(":", 4);
            if (parts.length == 4) {
                String productId = unescape(parts[0]);
                String productName = unescape(parts[1]);
                int quantity = 1;
                double price = 0;
                try {
                    quantity = Integer.parseInt(parts[2]);
                    price = Double.parseDouble(parts[3]);
                } catch (NumberFormatException ignored) {
                }
                Product product = new Product();
                product.setProductId(productId);
                product.setName(productName);
                product.setPrice(price);
                OrderItem item = new OrderItem(product, quantity);
                items.add(item);
            }
        }
        return items;
    }

    // Helper to escape commas and quotes for CSV
    private String escape(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            value = value.replace("\"", "\"\"");
            return "\"" + value + "\"";
        }
        return value;
    }

    private String unescape(String value) {
        if (value == null) return "";
        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1).replace("\"\"", "\"");
        }
        return value;
    }

    // Split CSV line with support for quoted fields
    private String[] splitCsv(String line, int expectedParts) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(sb.toString());
                sb.setLength(0);
                count++;
                if (count == expectedParts - 1) {
                    result.add(line.substring(i + 1));
                    break;
                }
            } else {
                sb.append(c);
            }
        }
        if (result.size() < expectedParts) {
            result.add(sb.toString());
        }
        return result.toArray(new String[0]);
    }
}
