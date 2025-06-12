package vn.codegym.module2.util;

import java.util.Scanner;

public class InputValidator {
    public static int getSize(int maxSize, Scanner sc) {
        int size;
        do {
            System.out.print("Enter number of properties (max " + maxSize + "): ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter an integer: ");
                sc.next();
            }
            size = sc.nextInt();
            if (size <= 0 || size > maxSize) {
                System.out.println("Size must be between 1 and " + maxSize);
            }
        } while (size <= 0 || size > maxSize);
        return size;
    }

    public static void inputProperties(int[] properties, Scanner sc) {
        for (int i = 0; i < properties.length; i++) {
            System.out.print("Enter property " + (i + 1) + ": ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter an integer: ");
                sc.next();
            }
            properties[i] = sc.nextInt();
        }
    }
}
