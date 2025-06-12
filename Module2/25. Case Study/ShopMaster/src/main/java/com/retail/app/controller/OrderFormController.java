package com.retail.app.controller;

import com.retail.app.model.Order;
import com.retail.app.model.OrderItem;
import com.retail.app.model.Product;
import com.retail.app.service.InventoryService;
import com.retail.app.service.OrderService;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
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
    private TextField orderTimeField;
    @FXML
    private GridPane menuGrid;
    @FXML
    private VBox selectedItemsList;
    @FXML
    private VBox rootVBox; // Add fx:id="rootVBox" to the outermost VBox in FXML if you want to add the badge dynamically

    private Order confirmedOrder = null;
    private ObservableList<OrderItemRow> selectedItems = FXCollections.observableArrayList();

    private List<Product> menu = new ArrayList<>();
    private InventoryService inventoryService = new InventoryService();
    private OrderService orderService = new OrderService();

    private boolean isEditMode = false; // Add this flag

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

        // Set order time to current time
        orderTimeField.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()));
        orderTimeField.setEditable(false);

        // Render selected items list
        selectedItems.addListener((javafx.collections.ListChangeListener<OrderItemRow>) c -> renderSelectedItemsList());
        renderSelectedItemsList();

        // Auto-generate OrderID for new order
        if (orderIdField.getText() == null || orderIdField.getText().isEmpty()) {
            orderIdField.setText(OrderIDGenerator.peekNextOrderId());
        }

        // If rootVBox is not null, remove any existing PAID badge (for reuse)
        if (rootVBox != null) {
            rootVBox.getChildren().removeIf(node -> node.getId() != null && node.getId().equals("paidBadge"));
        }
        // Show Confirm & Pay button for new order
        javafx.application.Platform.runLater(() -> {
            if (!isEditMode) {
                javafx.scene.Scene scene = orderIdField.getScene();
                if (scene != null) {
                    Button confirmPayBtn = (Button) scene.lookup("#confirmPayBtn");
                    if (confirmPayBtn != null) confirmPayBtn.setVisible(true);
                }
            }
        });
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
        if (customerPhoneField.getText() == null || customerPhoneField.getText().trim().isEmpty()) {
            logger.warning("Order confirmation failed: Customer phone is empty");
            showAlert(Alert.AlertType.WARNING, "Warning", "Phone number is required.");
            return;
        }
        if (selectedItems.isEmpty()) {
            logger.warning("Order confirmation failed: No items selected");
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select at least one item.");
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
            String orderTime = orderTimeField.getText();
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

            // Save orderTime in orderNote for now (extend Order model if needed)
            String fullNote = orderNote;
            if (orderTime != null && !orderTime.trim().isEmpty()) {
                fullNote = "[OrderTime: " + orderTime + "] " + fullNote;
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
    private void handleConfirmAndPay() {
        // Modal dialog for payment method selection
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Select Payment Method");
        dialog.setHeaderText("Choose Payment Method");

        VBox vbox = new VBox(12);
        vbox.setPadding(new javafx.geometry.Insets(16));
        Label label = new Label("Choose Payment Method:");
        ComboBox<String> combo = new ComboBox<>(FXCollections.observableArrayList("Cash", "Momo", "ZaloPay", "Bank Transfer"));
        combo.setPrefWidth(180);
        combo.setPromptText("Select payment method");
        vbox.getChildren().addAll(label, combo);

        dialog.getDialogPane().setContent(vbox);
        ButtonType confirmBtnType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmBtnType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmBtnType) {
                return combo.getValue();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(paymentMethod -> {
            if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a payment method.");
                return;
            }
            if (!validateOrderInputs()) return;
            Order paidOrder = buildOrder("Paid", paymentMethod);
            if (paidOrder == null) return;
            confirmedOrder = paidOrder;
            // Persist the paid order
            orderService.addOrder(confirmedOrder); // Always add as new paid order
            showAlert(Alert.AlertType.INFORMATION, "Payment successful!", "Order has been paid and confirmed!");
            closeForm();
        });
    }

    private boolean validateOrderInputs() {
        if (customerNameField.getText() == null || customerNameField.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Customer name is required.");
            return false;
        }
        if (customerPhoneField.getText() == null || customerPhoneField.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Phone number is required.");
            return false;
        }
        if (selectedItems.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select at least one item.");
            return false;
        }
        // Stock check
        for (OrderItemRow row : selectedItems) {
            Product product = row.getProduct();
            int requestedQty = row.getQuantity();
            if (requestedQty > product.getStockQuantity()) {
                showAlert(Alert.AlertType.ERROR, "Stock Error",
                        "Not enough stock for product: " + product.getName() +
                                ". Available: " + product.getStockQuantity() +
                                ", Requested: " + requestedQty);
                return false;
            }
        }
        return true;
    }

    private Order buildOrder(String status, String paymentMethod) {
        try {
            String orderId = orderIdField.getText();
            String customerName = customerNameField.getText();
            String customerPhone = customerPhoneField.getText();
            String orderNote = orderNoteField.getText();
            String orderTime = orderTimeField.getText();
            double totalAmount = Double.parseDouble(totalAmountField.getText().replace(",", ""));

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

            // Subtract ordered quantity from inventory and update file (only if paid)
            if ("Paid".equals(status)) {
                for (OrderItem item : items) {
                    Product product = item.getProduct();
                    int newStock = product.getStockQuantity() - item.getQuantity();
                    product.setStockQuantity(newStock);
                    inventoryService.updateProduct(product);
                }
            }

            String fullNote = orderNote;
            if (orderTime != null && !orderTime.trim().isEmpty()) {
                fullNote = "[OrderTime: " + orderTime + "] " + fullNote;
            }
            if (paymentMethod != null && !paymentMethod.trim().isEmpty()) {
                fullNote = "[Payment: " + paymentMethod + "] " + fullNote;
            }

            // Consume order ID if this is a new order
            if (orderId.equals(OrderIDGenerator.peekNextOrderId())) {
                OrderIDGenerator.consumeOrderId();
            }

            return new Order(orderId, customerName, customerPhone, fullNote, totalAmount, null, "Paid", items);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input! Total amount must be a numeric value.");
            return null;
        }
    }

    @FXML
    private void handleExit() {
        confirmedOrder = null;
        closeForm();
    }

    private void closeForm() {
        Stage stage = (Stage) orderIdField.getScene().getWindow();
        stage.close();
    }

    public Order getOrder() {
        return confirmedOrder;
    }

    public void setOrder(Order order) {
        if (order != null) {
            logger.info("Setting order in form: " + order.getOrderId());
            isEditMode = true; // Set edit mode
            orderIdField.setText(order.getOrderId());
            customerNameField.setText(order.getCustomerName());
            customerPhoneField.setText(order.getCustomerPhone());
            // Parse orderTime from orderNote if present
            String note = order.getOrderNote();
            String orderTime = "";
            if (note != null && note.contains("[OrderTime:")) {
                int start = note.indexOf("[OrderTime:") + 11;
                int end = note.indexOf("]", start);
                if (end > start) {
                    orderTime = note.substring(start, end).trim();
                }
            }
            orderTimeField.setText(orderTime.isEmpty() ? new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()) : orderTime);
            // Remove tags from note
            if (note != null) {
                note = note.replaceAll("\\[OrderTime:[^\\]]*\\]", "").trim();
            }
            orderNoteField.setText(note);

            // --- Fix: Restore selected items and their quantities ---
            selectedItems.clear();
            if (order.getItems() != null) {
                for (OrderItem item : order.getItems()) {
                    // Defensive: ensure product is not null
                    if (item.getProduct() != null) {
                        selectedItems.add(new OrderItemRow(item.getProduct(), item.getQuantity(), this::updateTotal));
                    }
                }
            }
            renderSelectedItemsList();
            updateTotal();

            // --- UI for Paid Orders ---
            boolean isPaid = "Paid".equalsIgnoreCase(order.getStatus());
            setFormReadOnly(isPaid);
            if (isPaid) {
                showPaidBadge();
            }
            // Hide Confirm & Pay button when editing any order
            javafx.scene.Scene scene = orderIdField.getScene();
            if (scene != null) {
                Button confirmPayBtn = (Button) scene.lookup("#confirmPayBtn");
                if (confirmPayBtn != null) confirmPayBtn.setVisible(false);
            }
        } else {
            isEditMode = false; // Not edit mode for new order
        }
    }

    private void setFormReadOnly(boolean readOnly) {
        customerNameField.setDisable(readOnly);
        customerPhoneField.setDisable(readOnly);
        orderNoteField.setDisable(readOnly);
        // Disable all quantity spinners and remove buttons
        for (javafx.scene.Node node : selectedItemsList.getChildren()) {
            if (node instanceof HBox) {
                for (javafx.scene.Node child : ((HBox) node).getChildren()) {
                    if (child instanceof Spinner) {
                        child.setDisable(readOnly);
                    }
                    if (child instanceof Button) {
                        child.setDisable(readOnly);
                    }
                }
            }
        }
        // Disable menuGrid buttons
        for (javafx.scene.Node node : menuGrid.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(readOnly);
            }
        }
        // Hide or disable action buttons
        // Confirm & Pay, Save
        javafx.scene.Scene scene = orderIdField.getScene();
        if (scene != null) {
            Button confirmPayBtn = (Button) scene.lookup("#confirmPayBtn");
            Button saveBtn = (Button) scene.lookup("#saveBtn");
            Button exitBtn = (Button) scene.lookup("#exitBtn");
            if (confirmPayBtn != null) confirmPayBtn.setVisible(!readOnly);
            if (saveBtn != null) saveBtn.setVisible(!readOnly);
            if (exitBtn != null) exitBtn.setVisible(true); // Always show Exit
        }
    }

    private void showPaidBadge() {
        // Add a green "PAID" badge at the top of the form
        if (rootVBox != null && rootVBox.lookup("#paidBadge") == null) {
            Label paidLabel = new Label("PAID");
            paidLabel.setId("paidBadge");
            paidLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22));
            paidLabel.setTextFill(Color.WHITE);
            paidLabel.setStyle("-fx-background-color:#22c55e; -fx-background-radius:12; -fx-padding:8 24 8 24; -fx-effect:dropshadow(gaussian,rgba(34,197,94,0.18),8,0,0,2);");
            paidLabel.setAlignment(Pos.CENTER);
            rootVBox.getChildren().add(0, paidLabel);
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
