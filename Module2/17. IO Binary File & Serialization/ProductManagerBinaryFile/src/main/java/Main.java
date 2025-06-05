import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị tất cả sản phẩm");
            System.out.println("3. Tìm kiếm sản phẩm theo mã");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Nhập mã sản phẩm: ");
                    String id = scanner.nextLine();
                    System.out.print("Nhập tên sản phẩm: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập giá: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Nhập hãng sản xuất: ");
                    String manufacturer = scanner.nextLine();
                    System.out.print("Nhập mô tả: ");
                    String description = scanner.nextLine();

                    Product product = new Product(id, name, price, manufacturer, description);
                    manager.addProduct(product);
                    System.out.println("Đã thêm sản phẩm!");
                    break;
                case 2:
                    System.out.println("\nDanh sách sản phẩm:");
                    for (Product p : manager.getAllProducts()) {
                        System.out.println(p);
                    }
                    break;
                case 3:
                    System.out.print("Nhập mã sản phẩm cần tìm: ");
                    String searchId = scanner.nextLine();
                    Product result = manager.searchById(searchId);
                    if (result != null) {
                        System.out.println("Tìm thấy: " + result);
                    } else {
                        System.out.println("Không tìm thấy sản phẩm.");
                    }
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }

        } while (choice != 0);
    }
}
