import java.util.Random;

public class StopWatchTest {
    public static void main(String[] args) {
        int size = 100_000;
        int[] arr = new int[size];
        Random rand = new Random();

        // Khởi tạo mảng với số ngẫu nhiên
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1_000_000);
        }

        StopWatch watch = new StopWatch();
        watch.start();

        // Thực hiện Selection Sort
        selectionSort(arr);

        watch.stop();
        System.out.println("Time taken to sort 100,000 elements using Selection Sort: "
                + watch.getElapsedTime() + " milliseconds");
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
