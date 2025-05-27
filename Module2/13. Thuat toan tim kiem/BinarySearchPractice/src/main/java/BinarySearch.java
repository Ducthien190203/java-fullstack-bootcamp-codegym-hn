public class BinarySearch {
    static int[] list = {2, 4, 7, 10, 45, 50, 59, 60, 66, 69, 70, 79};

    static int binarySearch(int[] list, int key) {
        int low = 0;
        int high = list.length - 1;

        while (high >= low) {
            int mid = (low + high) / 2;
            if (key < list[mid]) {
                high = mid - 1;
            } else if (key == list[mid]) {
                return mid;
            } else {
                low = mid + 1; // ✅ Đã sửa ở đây
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch(list, 2));   // index 0
        System.out.println(binarySearch(list, 11));  // không có => -1
        System.out.println(binarySearch(list, 79));  // index 11
        System.out.println(binarySearch(list, 1));   // không có => -1
        System.out.println(binarySearch(list, 5));   // không có => -1
        System.out.println(binarySearch(list, 80));  // không có => -1
    }
}
