package com.retail.app.service;

import com.retail.app.dao.OrderDAO;
import com.retail.app.model.Order;

import java.util.Date;
import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAO();
    private List<Order> orderList;

    public OrderService() {
        orderList = orderDAO.loadOrdersFromFile();
    }

    public List<Order> getAllOrders() {
        // Always reload from file to ensure latest data
        orderList = orderDAO.loadOrdersFromFile();
        return orderList;
    }

    public boolean addOrder(Order order) {
        if (order == null || order.getOrderId() == null || order.getOrderId().trim().isEmpty()) {
            return false;
        }
        order.setOrderDate(new Date());
        order.setStatus("Paid");
        orderList.add(order);
        return orderDAO.saveOrdersToFile(orderList);
    }

    public boolean updateOrder(Order updatedOrder) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderId().equals(updatedOrder.getOrderId())) {
                orderList.set(i, updatedOrder);
                return orderDAO.saveOrdersToFile(orderList);
            }
        }
        return false;
    }

    public boolean deleteOrder(String orderId) {
        boolean removed = orderList.removeIf(order -> order.getOrderId().equals(orderId));
        if (removed) {
            return orderDAO.saveOrdersToFile(orderList);
        }
        return false;
    }

    public void saveOrders() {
        orderDAO.saveOrdersToFile(orderList);
    }
}
