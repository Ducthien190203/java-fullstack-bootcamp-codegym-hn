import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. EnQueue\n2. DeQueue\n3. Display Queue\n4. Exit");
            System.out.print("Chọn thao tác: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Nhập giá trị cần thêm: ");
                    int value = scanner.nextInt();
                    queue.enQueue(value);
                    break;
                case 2:
                    System.out.println("DeQueue: " + queue.deQueue());
                    break;
                case 3:
                    queue.displayQueue();
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
