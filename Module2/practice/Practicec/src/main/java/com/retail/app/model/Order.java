package com.retail.app.model;

import java.util.List;

public class Order {
    private String orderId;
    private List<Product> products;
    private double total;

    public Order(String orderId, List<Product> products, double total) {
        this.orderId = orderId;
        this.products = products;
        this.total = total;
    }

    public String getOrderId() { return orderId; }
    public List<Product> getProducts() { return products; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Mã đơn hàng: " + orderId + ", Tổng tiền: " + total + " VNĐ, Sản phẩm: " + products.size();
    }
}
