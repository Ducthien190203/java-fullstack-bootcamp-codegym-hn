import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println();
            System.out.println("Chương trình quản lý sản phẩm");
            System.out.println("--------------------------------");
            System.out.println("1.Thêm sản phẩm");
            System.out.println("2.Hiển thị sản phẩm");
            System.out.println("3.Tìm kiếm sản phẩm theo tên");
            System.out.println("4.Cập nhật sản phẩm");
            System.out.println("5.Xoá sản phẩm");
            System.out.println("6.Danh sách sản phẩm sắp xếp tăng dần theo giá");
            System.out.println("7.Danh sách sản phẩm sắp xếp giảm dần theo giá");
            System.out.println("0.Thoát");
            System.out.print("Chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nhập tên: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập giá: ");
                    double price = scanner.nextDouble();
                    manager.addProduct(new Product(id, name, price));
                    break;
                case 2:
                    manager.displayProducts();
                    break;
                case 3:
                    System.out.print("Nhập tên cần tìm: ");
                    String searchName = scanner.nextLine();
                    manager.searchProductByName(searchName);
                    break;
                case 4:
                    System.out.print("Nhập ID cần sửa: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tên mới: ");
                    String newName = scanner.nextLine();
                    System.out.print("Giá mới: ");
                    double newPrice = scanner.nextDouble();
                    manager.updateProduct(updateId, newName, newPrice);
                    break;
                case 5:
                    System.out.print("Nhập ID cần xoá: ");
                    int deleteId = scanner.nextInt();
                    manager.deleteProduct(deleteId);
                    break;
                case 6:
                    manager.sortProductsByPriceAsc();
                    break;
                case 7:
                    manager.sortProductsByPriceDesc();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }

        } while (choice != 0);

        scanner.close();

    }
}
