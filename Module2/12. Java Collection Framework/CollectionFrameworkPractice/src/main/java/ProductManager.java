import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ProductManager {
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Không thể thêm sản phẩm null.");
            return;
        }

        for (Product p : productList) {
            if (p.getId() == product.getId()) {
                System.out.println("ID sản phẩm đã tồn tại. Vui lòng nhập ID khác.");
                return;
            }
        }

        productList.add(product);
        System.out.println("Đã thêm sản phẩm: " + product);
    }

    public void displayProducts() {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm rỗng.");
            return;
        }

        System.out.println("Danh sách sản phẩm:");
        System.out.printf("%-5s %-20s %-10s%n", "ID", "Tên sản phẩm", "Giá");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public void searchProductByName(String name) {
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm nào để tìm kiếm.");
            return;
        }

        boolean found = false;
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(product);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm nào chứa tên: " + name);
        }
    }

    public void updateProduct(int id, String newName, double newPrice) {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm rỗng. Không thể cập nhật.");
            return;
        }

        if (newName == null || newName.trim().isEmpty() || newPrice < 0) {
            System.out.println("Tên hoặc giá sản phẩm không hợp lệ.");
            return;
        }

        for (Product product : productList) {
            if (product.getId() == id) {
                product.setName(newName);
                product.setPrice(newPrice);
                System.out.println("Đã cập nhật sản phẩm: " + product);
                return;
            }
        }

        System.out.println("Không tìm thấy sản phẩm có ID: " + id);
    }


    public void deleteProduct(int id) {
        if (productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm rỗng. Không thể xoá.");
            return;
        }

        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                System.out.println("Đã xoá sản phẩm có ID: " + id);
                return;
            }
        }

        System.out.println("Không tìm thấy sản phẩm có ID: " + id);
    }


    public void sortProductsByPriceAsc() {
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm để sắp xếp.");
            return;
        }

        productList.sort(Comparator.comparingDouble(Product::getPrice));
        System.out.println("Danh sách sau khi sắp xếp tăng dần theo giá:");
        displayProducts();
    }

    public void sortProductsByPriceDesc() {
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm để sắp xếp.");
            return;
        }

        productList.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        System.out.println("Danh sách sau khi sắp xếp giảm dần theo giá:");
        displayProducts();
    }
}
