package com.retail.app.controller;

import com.retail.app.model.Order;
import com.retail.app.model.OrderItem;
import com.retail.app.model.Product;
import com.retail.app.service.InventoryService;
import com.retail.app.util.OrderIDGenerator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderFormController {

    private static final Logger logger = Logger.getLogger(OrderFormController.class.getName());

    @FXML
    private TextField orderIdField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerPhoneField;
    @FXML
    private TextArea orderNoteField;
    @FXML
    private TextField totalAmountField;
    @FXML
    private TextField tableNumberField;
    @FXML
    private RadioButton dineInRadio;
    @FXML
    private RadioButton takeAwayRadio;

    @FXML
    private GridPane menuGrid;
    @FXML
    private VBox selectedItemsList;

    private ToggleGroup orderTypeGroup;

    private Order confirmedOrder = null;
    private ObservableList<OrderItemRow> selectedItems = FXCollections.observableArrayList();

    private List<Product> menu = new ArrayList<>();
    private InventoryService inventoryService = new InventoryService();

    @FXML
    public void initialize() {
        logger.info("Initializing OrderFormController");
        // Load menu from inventory
        menu = inventoryService.getAllProducts();

        // Setup menu grid
        loadMenuGrid();

        // Setup total field
        totalAmountField.setText("0");
        totalAmountField.setEditable(false);

        // Render selected items list
        selectedItems.addListener((javafx.collections.ListChangeListener<OrderItemRow>) c -> renderSelectedItemsList());
        renderSelectedItemsList();

        // Auto-generate OrderID for new order
        if (orderIdField.getText() == null || orderIdField.getText().isEmpty()) {
            orderIdField.setText(OrderIDGenerator.peekNextOrderId());
        }

        // Setup order type toggle group
        orderTypeGroup = new ToggleGroup();
        dineInRadio.setToggleGroup(orderTypeGroup);
        takeAwayRadio.setToggleGroup(orderTypeGroup);
        dineInRadio.setSelected(true);
    }

    private void loadMenuGrid() {
        logger.info("Loading menu grid with products");
        menuGrid.getChildren().clear();
        int col = 0, row = 0;
        for (Product product : menu) {
            Button btn = new Button(product.getName() + "\n" + String.format("%,.0f₫", product.getPrice()));
            btn.setPrefSize(100, 100);
            btn.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-font-size:13px;");
            btn.setOnAction(e -> addOrIncreaseDish(product));
            menuGrid.add(btn, col, row);
            col++;
            if (col >= 3) {
                col = 0;
                row++;
            }
        }
    }

    private void addOrIncreaseDish(Product product) {
        logger.info("Adding or increasing dish: " + product.getProductId());
        for (OrderItemRow row : selectedItems) {
            if (row.getProduct().getProductId().equals(product.getProductId())) {
                row.increase();
                updateTotal();
                renderSelectedItemsList();
                return;
            }
        }
        OrderItemRow newRow = new OrderItemRow(product, 1, this::updateTotal);
        selectedItems.add(newRow);
        updateTotal();
        renderSelectedItemsList();
    }

    private void removeRow(OrderItemRow row) {
        logger.info("Removing row for product: " + row.getProduct().getProductId());
        selectedItems.remove(row);
        updateTotal();
        renderSelectedItemsList();
    }

    private void renderSelectedItemsList() {
        selectedItemsList.getChildren().clear();
        for (OrderItemRow row : selectedItems) {
            HBox itemBox = new HBox(2);
            itemBox.setAlignment(Pos.CENTER_LEFT);
            itemBox.setStyle(
                    "-fx-background-radius:10; -fx-background-color:#f7fafc; -fx-padding:8 10 8 10; " +
                            "-fx-border-radius:10; -fx-border-color:#e0e7ef; -fx-effect:dropshadow(gaussian,rgba(0,0,0,0.04),2,0,0,1);"
            );

            Label nameLabel = new Label(row.getProduct().getName());
            nameLabel.setStyle("-fx-font-size:15px; -fx-font-weight:600; -fx-text-fill:#22223b;");
            nameLabel.setPrefWidth(120);

            Spinner<Integer> qtySpinner = new Spinner<>();
            // Ensure value factory is always set before use
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999, row.getQuantity());
            qtySpinner.setValueFactory(valueFactory);
            qtySpinner.setEditable(true);
            qtySpinner.setPrefWidth(56);
            qtySpinner.getEditor().setStyle("-fx-font-size:15px; -fx-background-radius:6; -fx-border-radius:6;");
            // Only allow numeric input
            qtySpinner.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal.matches("\\d*")) {
                    qtySpinner.getEditor().setText(newVal.replaceAll("[^\\d]", ""));
                }
            });
            qtySpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null && newVal > 0) {
                    row.setQuantity(newVal);
                    updateTotal();
                }
            });

            Label subtotalLabel = new Label(String.format("%,.0f₫", row.getSubtotal()));
            subtotalLabel.setStyle("-fx-font-size:15px; -fx-font-weight:600; -fx-text-fill:#16a34a;");
            subtotalLabel.setPrefWidth(60);

            Button removeBtn = new Button("X");
            removeBtn.setStyle(
                    "-fx-background-color:transparent; -fx-text-fill:#ef4444; -fx-font-size:18px; -fx-font-weight:bold; -fx-background-radius:50%;"
            );
            removeBtn.setPrefWidth(28);
            removeBtn.setOnAction(e -> removeRow(row));
            removeBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> removeBtn.setStyle(
                    "-fx-background-color:#fee2e2; -fx-text-fill:#ef4444; -fx-font-size:18px; -fx-font-weight:bold; -fx-background-radius:50%;"
            ));
            removeBtn.addEventHandler(MouseEvent.MOUSE_EXITED, e -> removeBtn.setStyle(
                    "-fx-background-color:transparent; -fx-text-fill:#ef4444; -fx-font-size:18px; -fx-font-weight:bold; -fx-background-radius:50%;"
            ));

            itemBox.getChildren().addAll(
                    nameLabel,
                    qtySpinner,
                    subtotalLabel,
                    removeBtn
            );
            selectedItemsList.getChildren().add(itemBox);
        }
    }

    private void updateTotal() {
        double total = 0;
        for (OrderItemRow row : selectedItems) {
            total += row.getSubtotal();
        }
        totalAmountField.setText(String.format("%,.0f", total));
    }

    @FXML
    private void handleConfirmOrder() {
        logger.info("Confirm Order button clicked");
        if (customerNameField.getText() == null || customerNameField.getText().trim().isEmpty()) {
            logger.warning("Order confirmation failed: Customer name is empty");
            showAlert(Alert.AlertType.WARNING, "Warning", "Customer name is required.");
            return;
        }
        if (selectedItems.isEmpty()) {
            logger.warning("Order confirmation failed: No items selected");
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select at least one dish.");
            return;
        }
        // Check stock availability before confirming order
        for (OrderItemRow row : selectedItems) {
            Product product = row.getProduct();
            int requestedQty = row.getQuantity();
            if (requestedQty > product.getStockQuantity()) {
                logger.warning("Order confirmation failed: Not enough stock for product " + product.getProductId());
                showAlert(Alert.AlertType.ERROR, "Stock Error",
                        "Not enough stock for product: " + product.getName() +
                                ". Available: " + product.getStockQuantity() +
                                ", Requested: " + requestedQty);
                return;
            }
        }
        try {
            String orderId = orderIdField.getText();
            String customerName = customerNameField.getText();
            String customerPhone = customerPhoneField.getText();
            String orderNote = orderNoteField.getText();
            String tableNumber = tableNumberField.getText();
            String orderType = dineInRadio.isSelected() ? "Dine-in" : "Take-away";
            double totalAmount = Double.parseDouble(totalAmountField.getText().replace(",", ""));

            // Merge items with same productId (for edit/add more)
            Map<String, OrderItem> merged = new LinkedHashMap<>();
            for (OrderItemRow row : selectedItems) {
                String pid = row.getProduct().getProductId();
                if (merged.containsKey(pid)) {
                    merged.get(pid).setQuantity(merged.get(pid).getQuantity() + row.getQuantity());
                } else {
                    merged.put(pid, new OrderItem(row.getProduct(), row.getQuantity()));
                }
            }
            List<OrderItem> items = new ArrayList<>(merged.values());

            // Subtract ordered quantity from inventory and update file
            for (OrderItem item : items) {
                Product product = item.getProduct();
                int newStock = product.getStockQuantity() - item.getQuantity();
                product.setStockQuantity(newStock);
                inventoryService.updateProduct(product);
                logger.info("Inventory updated for product: " + product.getProductId() + ", new stock: " + newStock);
            }

            // Save tableNumber and orderType in orderNote for now (extend Order model if needed)
            String fullNote = orderNote;
            if (tableNumber != null && !tableNumber.trim().isEmpty()) {
                fullNote = "[Table: " + tableNumber + "] " + fullNote;
            }
            if (orderType != null && !orderType.trim().isEmpty()) {
                fullNote = "[Type: " + orderType + "] " + fullNote;
            }

            confirmedOrder = new Order(orderId, customerName, customerPhone, fullNote, totalAmount, null, "Pending", items);

            // Consume order ID if this is a new order
            if (orderId.equals(OrderIDGenerator.peekNextOrderId())) {
                OrderIDGenerator.consumeOrderId();
            }

            logger.info("Order confirmed: " + orderId);
            Stage stage = (Stage) orderIdField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Order confirmation failed: Invalid total amount", e);
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input! Total amount must be a numeric value.");
        }
    }

    @FXML
    private void handleExit() {
        logger.info("Order form exit/cancelled");
        confirmedOrder = null;
        Stage stage = (Stage) orderIdField.getScene().getWindow();
        stage.close();
    }

    public Order getOrder() {
        return confirmedOrder;
    }

    public void setOrder(Order order) {
        if (order != null) {
            logger.info("Setting order in form: " + order.getOrderId());
            orderIdField.setText(order.getOrderId());
            customerNameField.setText(order.getCustomerName());
            customerPhoneField.setText(order.getCustomerPhone());
            // Parse tableNumber and orderType from orderNote if present
            String note = order.getOrderNote();
            String tableNumber = "";
            String orderType = "";
            if (note != null) {
                if (note.contains("[Table:")) {
                    int start = note.indexOf("[Table:") + 7;
                    int end = note.indexOf("]", start);
                    if (end > start) {
                        tableNumber = note.substring(start, end).trim();
                    }
                }
                if (note.contains("[Type:")) {
                    int start = note.indexOf("[Type:") + 6;
                    int end = note.indexOf("]", start);
                    if (end > start) {
                        orderType = note.substring(start, end).trim();
                    }
                }
            }
            tableNumberField.setText(tableNumber);
            if ("Take-away".equalsIgnoreCase(orderType)) {
                takeAwayRadio.setSelected(true);
            } else {
                dineInRadio.setSelected(true);
            }
            // Remove tags from note
            if (note != null) {
                note = note.replaceAll("\\[Table:[^\\]]*\\]", "").replaceAll("\\[Type:[^\\]]*\\]", "").trim();
            }
            orderNoteField.setText(note);

            // Populate selected items if editing
            selectedItems.clear();
            if (order.getItems() != null) {
                for (OrderItem item : order.getItems()) {
                    selectedItems.add(new OrderItemRow(item.getProduct(), item.getQuantity(), this::updateTotal));
                }
            }
            updateTotal();
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

    // Helper class for selected dish row with quantity controls and remove button
    public static class OrderItemRow {
        private final Product product;
        private final IntegerProperty quantity = new SimpleIntegerProperty(1);
        private final DoubleProperty subtotal = new SimpleDoubleProperty(0);

        public OrderItemRow(Product product, int qty, Runnable onChange) {
            this.product = product;
            this.quantity.set(qty);
            this.subtotal.set(product.getPrice() * qty);

            this.quantity.addListener((obs, oldVal, newVal) -> {
                this.subtotal.set(product.getPrice() * newVal.intValue());
                if (onChange != null) onChange.run();
            });
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity.get();
        }

        public void setQuantity(int qty) {
            quantity.set(qty);
        }

        public void increase() {
            quantity.set(getQuantity() + 1);
        }

        public void decrease() {
            if (getQuantity() > 1) {
                quantity.set(getQuantity() - 1);
            }
        }

        public double getSubtotal() {
            return subtotal.get();
        }
    }
}
