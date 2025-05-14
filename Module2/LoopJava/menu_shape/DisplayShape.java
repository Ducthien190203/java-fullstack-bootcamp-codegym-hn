package LoopJava.menu_shape;

import java.util.Scanner;

public class DisplayShape {
    public static void main(String[] args) {
        int choice = -1;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Menu");
            System.out.println("1. Draw the rectangle");
            System.out.println("2. Draw the triangle");
            System.out.println("3. Draw the reverse triangle");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 2:
                    // for (int i = 1; i <= 5; i++) {
                    //     for (int j = 0; j < i; j++) {
                    //         System.out.print("* ");
                    //     }
                    //     System.out.println();
                    // }
                    for (int i = 1; i <= 5; i++) {
                        System.out.println("* ".repeat(i));
                    }
                    break;
                case 3:
                    // for (int i = 0; i < 5; i++) {
                    //     for (int j = 0; j < 6; j++) {
                    //         System.out.print("* ");
                    //     }
                    //     System.out.println();
                    // }
                    for (int i = 5; i >= 1; i--) {
                        System.out.println("* ".repeat(i));
                    }
                    break;
                case 1:
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 8; j++) {
                            System.out.print("* ");
                        }
                        System.out.println();
                    }
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("No choice!");
            }
        } while (choice != 0);
        input.close();
    }
}