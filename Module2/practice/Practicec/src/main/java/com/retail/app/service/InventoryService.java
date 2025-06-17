package com.retail.app.service;

import com.retail.app.model.Product;
import com.retail.app.util.CSVUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private final String FILE_PATH = "data/inventory_report.csv";
    private final List<Product> inventory = new ArrayList<>();

    public InventoryService() {
        // Ensure file and parent directory exist
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Không thể tạo file tồn kho: " + e.getMessage());
            }
        }
        inventory.addAll(CSVUtil.readProducts(FILE_PATH));
    }

    public void addProduct(Product product) {
        inventory.add(product);

        // Ensure directory exists before writing
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        CSVUtil.writeProducts(FILE_PATH, inventory);
        System.out.println("Đã thêm sản phẩm.");
    }

    public void removeProduct(String id) {
        inventory.removeIf(p -> p.getId().equals(id));

        // Ensure directory exists before writing
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        CSVUtil.writeProducts(FILE_PATH, inventory);
        System.out.println("Đã xóa sản phẩm.");
    }

    public void listProducts() {
        System.out.println("\nDanh sách sản phẩm:");
        inventory.forEach(System.out::println);
    }

    public List<Product> getInventory() {
        return inventory;
    }
}