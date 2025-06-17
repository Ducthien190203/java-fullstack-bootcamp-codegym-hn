package com.retail.app.util;

import com.retail.app.model.Product;
import com.retail.app.model.Order;

import java.io.*;
import java.util.*;

public class CSVUtil {

    public static List<Product> readProducts(String path) {
        List<Product> products = new ArrayList<>();
        int EXPECTED_PRODUCT_FIELDS = 4;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < EXPECTED_PRODUCT_FIELDS) continue;

                String id = parts[0].trim();
                String name = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                int quantity = Integer.parseInt(parts[3].trim());

                products.add(new Product(id, name, price, quantity));
            }
        } catch (IOException e) {
            System.out.println("Không thể đọc file sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }

    public static void writeProducts(String path, List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Product p : products) {
                String line = String.join(",",
                        p.getId(),
                        p.getName(),
                        String.valueOf(p.getPrice()),
                        String.valueOf(p.getQuantity()));
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Ghi file sản phẩm thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Order> readOrders(String path, List<Product> inventory) {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;
                String orderId = parts[0].trim();
                double total = Double.parseDouble(parts[1].trim());
                int productCount = Integer.parseInt(parts[2].trim());
                // For demo: we don't store product IDs in file, so just add empty list or all products
                orders.add(new Order(orderId, new ArrayList<>(inventory), total));
            }
        } catch (IOException e) {
            // Ignore if file does not exist
        }
        return orders;
    }

    public static void writeOrders(String path, List<Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Order o : orders) {
                String line = String.join(",",
                        o.getOrderId(),
                        String.valueOf(o.getTotal()),
                        String.valueOf(o.getProducts().size())); // số lượng sản phẩm
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Ghi file đơn hàng thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi ghi file đơn hàng: " + e.getMessage());
            e.printStackTrace();
        }
    }
}