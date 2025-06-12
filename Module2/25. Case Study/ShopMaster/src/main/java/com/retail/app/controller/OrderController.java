package com.retail.app.controller;

import com.retail.app.model.Order;
import com.retail.app.service.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderController {

    private static final Logger logger = Logger.getLogger(OrderController.class.getName());

    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> orderIdColumn;
    @FXML
    private TableColumn<Order, String> customerColumn;
    @FXML
    private TableColumn<Order, Double> totalColumn;
    @FXML
    private TableColumn<Order, String> dateColumn;
    @FXML
    private TableColumn<Order, String> statusColumn;

    private ObservableList<Order> orderList;
    private OrderService orderService = new OrderService();

    @FXML
    public void initialize() {
        logger.info("Initializing OrderController");
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        dateColumn.setCellValueFactory(cellData -> {
            Date date = cellData.getValue().getOrderDate();
            String formatted = date != null ? new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date) : "";
            return new javafx.beans.property.SimpleStringProperty(formatted);
        });
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadOrders();
    }

    private void loadOrders() {
        logger.info("Loading orders into order table");
        List<Order> orders = orderService.getAllOrders();
        orderList = FXCollections.observableArrayList(orders);
        orderTable.setItems(orderList);
    }

    @FXML
    private void handleAddOrder() {
        logger.info("Add Order button clicked");
        openOrderForm(null);
    }

    @FXML
    private void handleEditOrder() {
        Order selectedOrder = orderTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            logger.warning("Edit Order attempted with no selection");
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select an order to edit.");
            return;
        }
        logger.info("Editing order: " + selectedOrder.getOrderId());
        openOrderForm(selectedOrder);
    }

    private void openOrderForm(Order order) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retail/app/view/OrderForm.fxml"));
            Parent formRoot = loader.load();

            OrderFormController formController = loader.getController();
            if (order != null) {
                // Pass a deep copy to avoid modifying the original if cancelled
                formController.setOrder(new Order(
                        order.getOrderId(),
                        order.getCustomerName(),
                        order.getCustomerPhone(),
                        order.getOrderNote(),
                        order.getTotalAmount(),
                        order.getOrderDate(),
                        order.getStatus(),
                        order.getItems() != null ? new java.util.ArrayList<>(order.getItems()) : null
                ));
            }

            Stage formStage = new Stage();
            formStage.setTitle(order == null ? "Add New Order" : "Edit Order");
            formStage.setScene(new Scene(formRoot));
            formStage.initModality(Modality.APPLICATION_MODAL);
            formStage.setMaximized(true); // Open in fullscreen mode
            formStage.showAndWait();

            Order updatedOrder = formController.getOrder();
            if (updatedOrder != null) {
                if (order == null) {
                    logger.info("Adding new order: " + updatedOrder.getOrderId());
                    orderService.addOrder(updatedOrder);
                } else {
                    logger.info("Updating order: " + order.getOrderId());
                    // Merge items and total if more food is ordered
                    order.setCustomerName(updatedOrder.getCustomerName());
                    order.setCustomerPhone(updatedOrder.getCustomerPhone());
                    order.setOrderNote(updatedOrder.getOrderNote());
                    // Merge items: sum quantities for same product
                    java.util.Map<String, com.retail.app.model.OrderItem> itemMap = new java.util.HashMap<>();
                    for (com.retail.app.model.OrderItem item : order.getItems()) {
                        itemMap.put(item.getProduct().getProductId(), item);
                    }
                    for (com.retail.app.model.OrderItem newItem : updatedOrder.getItems()) {
                        String pid = newItem.getProduct().getProductId();
                        if (itemMap.containsKey(pid)) {
                            itemMap.get(pid).setQuantity(itemMap.get(pid).getQuantity() + newItem.getQuantity());
                        } else {
                            itemMap.put(pid, new com.retail.app.model.OrderItem(newItem.getProduct(), newItem.getQuantity()));
                        }
                    }
                    order.setItems(new java.util.ArrayList<>(itemMap.values()));
                    // Update total
                    double newTotal = 0;
                    for (com.retail.app.model.OrderItem item : order.getItems()) {
                        newTotal += item.getSubtotal();
                    }
                    order.setTotalAmount(newTotal);
                    orderService.updateOrder(order);
                }
                loadOrders();
            } else {
                logger.info("Order form closed without saving");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Cannot open order form", e);
            showAlert(Alert.AlertType.ERROR, "Error", "Cannot open order form!");
        }
    }

    @FXML
    private void handlePayment() {
        Order selectedOrder = orderTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            logger.warning("Payment attempted with no order selected");
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select an order to pay.");
            return;
        }
        if ("Paid".equalsIgnoreCase(selectedOrder.getStatus())) {
            logger.info("Payment attempted on already paid order: " + selectedOrder.getOrderId());
            showAlert(Alert.AlertType.INFORMATION, "Info", "This order is already paid.");
            return;
        }
        // Ask for payment method
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Cash", "Cash", "Card", "E-wallet");
        dialog.setTitle("Payment");
        dialog.setHeaderText("Select payment method for Order " + selectedOrder.getOrderId());
        dialog.setContentText("Payment method:");
        java.util.Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String paymentMethod = result.get();
            logger.info("Order " + selectedOrder.getOrderId() + " paid with method: " + paymentMethod);
            selectedOrder.setStatus("Paid");
            orderService.updateOrder(selectedOrder);
            // Force refresh of the TableView to reflect the status change
            orderTable.refresh();
            orderService.exportPayment(selectedOrder, paymentMethod);
            loadOrders();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Payment successful!\nMethod: " + paymentMethod);
        } else {
            logger.info("Payment cancelled by user for order: " + selectedOrder.getOrderId());
        }
    }

    @FXML
    private void handleBack() {
        logger.info("Back button clicked, returning to Main Menu");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retail/app/view/MainMenu.fxml"));
            Parent mainRoot = loader.load();
            Stage currentStage = (Stage) orderTable.getScene().getWindow();
            currentStage.setScene(new Scene(mainRoot, 800, 600));
            currentStage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Cannot go back to Main Menu", e);
            showAlert(Alert.AlertType.ERROR, "Error", "Cannot go back to Main Menu!");
        }
    }

    @FXML
    private void handleDeleteOrder() {
        Order selectedOrder = orderTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            logger.warning("Delete Order attempted with no selection");
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select an order to delete.");
            return;
        }
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete order: " + selectedOrder.getOrderId() + "?");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            logger.info("Deleting order: " + selectedOrder.getOrderId());
            boolean success = orderService.deleteOrder(selectedOrder.getOrderId());
            if (success) {
                logger.info("Order deleted: " + selectedOrder.getOrderId());
                loadOrders();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Order deleted successfully.");
            } else {
                logger.severe("Failed to delete order: " + selectedOrder.getOrderId());
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete order!");
            }
        } else {
            logger.info("Order deletion cancelled by user");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        logger.info("Showing alert [" + type + "] " + title + ": " + message);
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}