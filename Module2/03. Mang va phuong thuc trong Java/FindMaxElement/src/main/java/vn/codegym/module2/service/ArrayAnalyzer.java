package vn.codegym.module2.service;

public class ArrayAnalyzer {
    public static void printProperties(int[] properties) {
        for (int prop : properties) {
            System.out.print(prop + " ");
        }
        System.out.println();
    }

    public static void printMaxProperty(int[] properties) {
        int max = properties[0];
        int index = 0;
        for (int i = 1; i < properties.length; i++) {
            if (properties[i] > max) {
                max = properties[i];
                index = i;
            }
        }
        System.out.println("Maximum property value: " + max + " at index " + index);
    }

}
