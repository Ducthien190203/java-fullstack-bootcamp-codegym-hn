package com.retail.app.service;

import com.retail.app.model.Order;
import com.retail.app.model.Product;
import com.retail.app.util.CSVUtil;
import com.retail.app.util.OrderIDGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    private final String FILE_PATH = "data/order_payment.csv";
    private final List<Order> orders = new ArrayList<>();
    private final InventoryService inventoryService = new InventoryService();
    private final Scanner scanner = new Scanner(System.in);

    public OrderService() {
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
                System.out.println("Không thể tạo file đơn hàng: " + e.getMessage());
            }
        }
        orders.addAll(CSVUtil.readOrders(FILE_PATH, inventoryService.getInventory()));
    }

    public void createOrder() {
        List<Product> selected = new ArrayList<>();
        inventoryService.listProducts();

        while (true) {
            System.out.print("Nhập ID sản phẩm (hoặc 'x' để hoàn tất): ");
            String id = scanner.nextLine();
            if (id.equalsIgnoreCase("x")) break;

            Product found = inventoryService.getInventory().stream()
                    .filter(p -> p.getId().equals(id)).findFirst().orElse(null);

            if (found != null) {
                selected.add(found);
                System.out.println("Đã thêm: " + found.getName());
            } else {
                System.out.println("Không tìm thấy sản phẩm.");
            }
        }

        double total = selected.stream().mapToDouble(Product::getPrice).sum();
        Order order = new Order(OrderIDGenerator.generate(), selected, total);
        orders.add(order);

        CSVUtil.writeOrders(FILE_PATH, orders);
        System.out.println("Đơn hàng đã được tạo.");
    }

    public void listOrders() {
        System.out.println("\nDanh sách đơn hàng:");
        orders.forEach(System.out::println);
    }
}