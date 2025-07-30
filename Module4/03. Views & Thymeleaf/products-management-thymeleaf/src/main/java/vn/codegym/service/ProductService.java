package vn.codegym.service;

import vn.codegym.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    private static final Map<Integer, Product> products;
    private static int nextId = 0;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "IPhone 13", 1000.0, "Newest iPhone", "Apple"));
        products.put(2, new Product(2, "Samsung Galaxy S22", 900.0, "Newest Samsung", "Samsung"));
        products.put(3, new Product(3, "Google Pixel 6", 800.0, "Newest Pixel", "Google"));
        products.put(4, new Product(4, "Xiaomi 12", 700.0, "Newest Xiaomi", "Xiaomi"));
        products.put(5, new Product(5, "OnePlus 10 Pro", 850.0, "Newest OnePlus", "OnePlus"));
        nextId = products.size() + 1;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        if (product.getId() == 0) {
            product.setId(nextId++);
        }
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
}