package com.retail.app.controller;

import java.util.Scanner;

public class MainMenuController {
    private final InventoryController inventoryController = new InventoryController();
    private final OrderController orderController = new OrderController();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=== ShopMaster Console ===");
            System.out.println("1. Quản lý tồn kho");
            System.out.println("2. Quản lý đơn hàng");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": inventoryController.showMenu(); break;
                case "2": orderController.showMenu(); break;
                case "0": System.out.println("Thoát chương trình."); return;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
