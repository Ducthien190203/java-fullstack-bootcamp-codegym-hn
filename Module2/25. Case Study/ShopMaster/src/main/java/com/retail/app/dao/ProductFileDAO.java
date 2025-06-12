package com.retail.app.dao;

import com.retail.app.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductFileDAO {
    private final String fileName = "products.txt";

    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return products;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    Product product = new Product(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
                    products.add(product);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean saveProducts(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products) {
                writer.write(product.getProductId() + "|" +
                        product.getName() + "|" +
                        product.getPrice() + "|" +
                        product.getStockQuantity());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
