package vn.codegym.module2;

import java.util.Scanner;

import static vn.codegym.module2.service.ArrayAnalyzer.printMaxProperty;
import static vn.codegym.module2.service.ArrayAnalyzer.printProperties;
import static vn.codegym.module2.util.InputValidator.getSize;
import static vn.codegym.module2.util.InputValidator.inputProperties;

public class Main {
    public static void main(String[] args) {
        final int MAX_SIZE = 20;
        Scanner sc = new Scanner(System.in);

        int size = getSize(MAX_SIZE, sc);
        int[] properties = new int[size];

        inputProperties(properties, sc);

        System.out.print("Original properties: ");
        printProperties(properties);

        printMaxProperty(properties);

        sc.close();
    }


}
