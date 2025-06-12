package com.retail.app.controller;

import com.retail.app.model.Product;
import com.retail.app.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductFormController {

    private static final Logger logger = Logger.getLogger(ProductFormController.class.getName());

    @FXML
    private TextField productIdField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField stockField;

    private Product confirmedProduct = null;
    private boolean isEditMode = false;
    private Product editingProduct = null;

    private InventoryService inventoryService = new InventoryService();

    @FXML
    public void initialize() {
        // Add category ComboBox options
        categoryComboBox.setItems(FXCollections.observableArrayList("Food", "Drink"));
        categoryComboBox.setDisable(false);
        productIdField.setEditable(false);

        // Listen for category selection changes (only in Add mode)
        categoryComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!isEditMode && newVal != null) {
                String nextId = generateNextProductId(newVal);
                productIdField.setText(nextId);
            }
        });
    }

    @FXML
    private void handleConfirm() {
        logger.info("Product form: Save button clicked");
        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            logger.warning("Product form: Name is empty");
            showAlert(Alert.AlertType.WARNING, "Warning", "Product name is required.");
            return;
        }
        if (!isEditMode && (categoryComboBox.getValue() == null || categoryComboBox.getValue().isEmpty())) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a category.");
            return;
        }
        try {
            String productId = productIdField.getText();
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stockQuantity = Integer.parseInt(stockField.getText());

            confirmedProduct = new Product(productId, name, price, stockQuantity);

            logger.info("Product form: Product confirmed - " + productId);
            Stage stage = (Stage) productIdField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Product form: Invalid input for price or stock", e);
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input! Price and stock must be numeric.");
        }
    }

    @FXML
    private void handleCancel() {
        logger.info("Product form: Cancel button clicked");
        confirmedProduct = null;
        Stage stage = (Stage) productIdField.getScene().getWindow();
        stage.close();
    }

    public Product getProduct() {
        return confirmedProduct;
    }

    // Called when editing a product
    public void setProduct(Product product) {
        if (product != null) {
            logger.info("Product form: Editing product - " + product.getProductId());
            isEditMode = true;
            editingProduct = product;
            productIdField.setText(product.getProductId());
            nameField.setText(product.getName());
            priceField.setText(String.valueOf(product.getPrice()));
            stockField.setText(String.valueOf(product.getStockQuantity()));
            // Set category based on productId prefix
            if (product.getProductId().startsWith("FOOD")) {
                categoryComboBox.setValue("Food");
            } else if (product.getProductId().startsWith("DRINK")) {
                categoryComboBox.setValue("Drink");
            } else {
                categoryComboBox.setValue(null);
            }
            categoryComboBox.setDisable(true); // Prevent changing category in edit mode
        }
    }

    // Generate next available product ID for the selected category
    private String generateNextProductId(String category) {
        List<Product> allProducts = inventoryService.getAllProducts();
        String prefix = category.equalsIgnoreCase("Food") ? "FOOD" : "DRINK";
        int max = 0;
        for (Product p : allProducts) {
            if (p.getProductId() != null && p.getProductId().startsWith(prefix)) {
                try {
                    String numPart = p.getProductId().substring(prefix.length());
                    int num = Integer.parseInt(numPart);
                    if (num > max) max = num;
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return prefix + String.format("%03d", max + 1);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        logger.info("Product form: Showing alert [" + type + "] " + title + ": " + message);
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
