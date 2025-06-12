import java.util.Scanner;

public class DeleteElementByIndex {

    public static void main(String[] args) {
        int[] arr = {11, 22, 33, 44, 55, 66, 77, 88, 99, 100};
        Scanner sc = new Scanner(System.in);
        int size = arr.length;

        int inputIndex = getInputIndex(sc, size);
        size = deleteElementAtIndex(arr, inputIndex, size);

        System.out.println("Updated array:");
        printArray(arr, size);
    }

    private static int getInputIndex(Scanner sc, int size) {
        int inputIndex;
        do {
            System.out.print("Enter the index of the element you want to delete: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }
            inputIndex = sc.nextInt();
        } while (inputIndex < 0 || inputIndex >= size);
        return inputIndex;
    }

    private static int deleteElementAtIndex(int[] arr, int index, int size) {
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        System.out.println("Element deleted.");
        return size - 1;
    }

    private static void printArray(int[] arr, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
