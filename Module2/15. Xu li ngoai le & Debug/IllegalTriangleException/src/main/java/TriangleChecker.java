import java.util.Scanner;

class IllegalTriangleException extends Exception {
    public IllegalTriangleException(String message) {
        super(message);
    }
}

public class TriangleChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập cạnh a:");
        int a = scanner.nextInt();
        System.out.println("Nhập cạnh b:");
        int b = scanner.nextInt();
        System.out.println("Nhập cạnh c:");
        int c = scanner.nextInt();
        scanner.close();

        try {
            checkTriangle(a, b, c);
            System.out.println("Đây là tam giác hợp lệ");
        } catch (IllegalTriangleException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    // Đặt phương thức này bên trong class TriangleChecker
    public static void checkTriangle(int a, int b, int c) throws IllegalTriangleException {
        if (a + b <= c || a + c <= b || b + c <= a || a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalTriangleException("Đây không phải là tam giác hợp lệ");
        }
    }
}
