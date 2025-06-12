import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr1 = new int[5];
        int[] arr2 = new int[3];
        int[] arr3 = new int[arr1.length + arr2.length];

        System.out.println("Enter elements for the first array (5 elements):");
        addElement(arr1, sc, "Array 1");
        System.out.println("------------------");
        System.out.println("Enter elements for the second array (3 elements):");
        addElement(arr2, sc, "Array 2");

        System.out.print("First array: ");
        printArray(arr1);
        System.out.println();
        System.out.print("Second array: ");
        printArray(arr2);
        mergeArray(arr3, arr1, arr2);
        System.out.println("\n------------------");
        System.out.println("Array after merging: ");
        printArray(arr3);
    }

    private static void mergeArray(int[] arr3, int[] arr1, int[] arr2) {
        int indexArr2 = 0;
        for (int i = 0; i < arr3.length; i++) {
            if (i < arr1.length) {
                arr3[i] = arr1[i];
            } else {
                arr3[i] = arr2[indexArr2++];
            }
        }
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + "\t");
        }
        System.out.println();
    }

    private static void addElement(int[] arr, Scanner sc, String arrayName) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arrayName + " - Enter element " + (i + 1) + ": ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Please enter an integer: ");
                sc.next(); // Clear invalid input
            }
            arr[i] = sc.nextInt();
        }
    }

}
