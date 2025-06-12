import java.util.Scanner;

public class AddElementToArray {
    public static void main(String[] args) {
        final int MAX_SIZE = 10;
        int[] arr = new int[MAX_SIZE];
        int[] initial = {5, 4, 3, 2, 1};
        int currentSize = initial.length;

        Scanner sc = new Scanner(System.in);
        System.arraycopy(initial, 0, arr, 0, initial.length);

        System.out.println("Original array:");
        printArray(arr, currentSize);

        // Lấy số và chỉ mục cần chèn
        int numberToInsert = getNumber(sc);
        int indexToInsert = getIndex(sc, currentSize);

        // Kiểm tra giới hạn
        if (indexToInsert < 0 || indexToInsert > currentSize || currentSize >= MAX_SIZE) {
            System.out.println("Cannot insert element at the specified index.");
        } else {
            // Dời phần tử và chèn
            currentSize = insertElement(currentSize, indexToInsert, arr, numberToInsert);

            System.out.println("\nArray after insertion:");
            printArray(arr, currentSize);
        }
    }

    private static int insertElement(int currentSize, int indexToInsert, int[] arr, int numberToInsert) {
        for (int i = currentSize; i > indexToInsert; i--) {
            arr[i] = arr[i - 1];
        }
        arr[indexToInsert] = numberToInsert;
        currentSize++;
        return currentSize;
    }

    private static void printArray(int[] arr, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

    private static int getIndex(Scanner sc, int currentSize) {
        int inputIndex;
        while (true) {
            System.out.print("\nEnter the index you want to add in (0 to " + currentSize + "): ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            } else {
                inputIndex = sc.nextInt();
                if (inputIndex >= 0 && inputIndex <= currentSize) {
                    return inputIndex;
                } else {
                    System.out.println("Index out of bounds. Please try again.");
                }
            }
        }
    }

    private static int getNumber(Scanner sc) {
        int inputNumber;
        while (true) {
            System.out.print("\nEnter the number you want to add: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            } else {
                inputNumber = sc.nextInt();
                return inputNumber;
            }
        }
    }
}
