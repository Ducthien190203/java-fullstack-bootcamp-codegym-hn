import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private static final String FILE_PATH = "products.dat";

    public void addProduct(Product product) {
        List<Product> products = getAllProducts();
        products.add(product);
        writeToFile(products);
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            products = (List<Product>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File chưa tồn tại thì trả về danh sách rỗng
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public void writeToFile(List<Product> products) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Product searchById(String id) {
        for (Product p : getAllProducts()) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
}
