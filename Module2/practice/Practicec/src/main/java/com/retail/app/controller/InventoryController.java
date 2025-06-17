package com.retail.app.controller;

import com.retail.app.model.Product;
import com.retail.app.service.InventoryService;

import java.util.Scanner;

public class InventoryController {
    private final InventoryService service = new InventoryService();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n-- Quản lý Tồn kho --");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Danh sách sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": addProduct(); break;
                case "2": service.listProducts(); break;
                case "3": removeProduct(); break;
                case "0": return;
                default: System.out.println("Không hợp lệ.");
            }
        }
    }

    private void addProduct() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Tên: ");
        String name = scanner.nextLine();
        System.out.print("Giá: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Số lượng: ");
        int qty = Integer.parseInt(scanner.nextLine());

        service.addProduct(new Product(id, name, price, qty));
    }

    private void removeProduct() {
        System.out.print("ID sản phẩm cần xóa: ");
        String id = scanner.nextLine();
        service.removeProduct(id);
    }
}
