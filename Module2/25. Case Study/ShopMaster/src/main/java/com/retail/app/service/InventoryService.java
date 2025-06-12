package com.retail.app.service;

import com.retail.app.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private final String fileName = "products.txt";
    private List<Product> products;

    public InventoryService() {
        products = loadProductsFromFile();
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public boolean addProduct(Product product) {
        if (product == null || product.getProductId().trim().isEmpty()) {
            return false;
        }
        products.add(product);
        return saveProductsToFile();
    }

    public boolean updateProduct(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(updatedProduct.getProductId())) {
                products.set(i, updatedProduct);
                return saveProductsToFile();
            }
        }
        return false;
    }

    public boolean deleteProduct(String productId) {
        boolean removed = products.removeIf(product -> product.getProductId().equals(productId));
        if (removed) {
            return saveProductsToFile();
        }
        return false;
    }

    private List<Product> loadProductsFromFile() {
        List<Product> loadedProducts = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return loadedProducts;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String productId = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int stockQuantity = Integer.parseInt(parts[3]);
                    loadedProducts.add(new Product(productId, name, price, stockQuantity));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return loadedProducts;
    }

    private boolean saveProductsToFile() {
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
