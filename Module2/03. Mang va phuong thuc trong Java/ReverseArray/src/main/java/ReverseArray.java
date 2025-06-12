import java.util.Scanner;

public class ReverseArray {
    public static void main(String[] args) {
        final int MAX_SIZE = 20;
        Scanner sc = new Scanner(System.in);

        int size;
        do {
            System.out.print("Enter a size (" + MAX_SIZE + "): ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Please enter an integer: ");
                sc.next();
            }
            size = sc.nextInt();
            if (size <= 0 || size > MAX_SIZE) {
                System.out.println("Size must be between 1 and " + MAX_SIZE + ".");

            }
        } while (size <= 0 || size >= MAX_SIZE);

        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Please enter an integer: ");
                sc.next();

            }
            arr[i] = sc.nextInt();
        }

        System.out.print("Original array: ");
        printArray(arr);

        reverseArray(size, arr);

        System.out.print("Reversed array: ");
        printArray(arr);
    }

    private static void reverseArray(int size, int[] arr) {
        for (int i = 0; i < size / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[size - 1 - i];
            arr[size - 1 - i] = temp;
        }
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + "\t");
        }
        System.out.println();
    }


}