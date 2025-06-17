package com.retail.app.controller;

import com.retail.app.service.OrderService;

import java.util.Scanner;

public class OrderController {
    private final OrderService service = new OrderService();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n-- Quản lý Đơn hàng --");
            System.out.println("1. Tạo đơn hàng");
            System.out.println("2. Danh sách đơn hàng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": service.createOrder(); break;
                case "2": service.listOrders(); break;
                case "0": return;
                default: System.out.println("Không hợp lệ.");
            }
        }
    }
}
