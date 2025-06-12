package com.retail.app.controller;

import com.retail.app.model.Product;
import com.retail.app.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventoryController {

    private static final Logger logger = Logger.getLogger(InventoryController.class.getName());

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> productIdColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, Integer> stockColumn;

    private ObservableList<Product> productList;
    private InventoryService inventoryService = new InventoryService();

    @FXML
    public void initialize() {
        logger.info("Initializing InventoryController");
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));

        loadProducts();
    }

    private void loadProducts() {
        logger.info("Loading products into inventory table");
        List<Product> products = inventoryService.getAllProducts();
        productList = FXCollections.observableArrayList(products);
        productTable.setItems(productList);

        // Áp dụng chính sách tự động điều chỉnh kích thước cột
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void handleAddProduct() {
        logger.info("Add Product button clicked");
        openProductForm(null);
    }

    @FXML
    private void handleEditProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            logger.warning("Edit Product attempted with no selection");
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a product to edit.");
            return;
        }
        logger.info("Editing product: " + selectedProduct.getProductId());
        openProductForm(selectedProduct);
    }

    @FXML
    private void handleDeleteProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            logger.warning("Delete Product attempted with no selection");
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a product to delete.");
            return;
        }
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete product: " + selectedProduct.getName() + "?");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            logger.info("Deleting product: " + selectedProduct.getProductId());
            boolean success = inventoryService.deleteProduct(selectedProduct.getProductId());
            if (success) {
                logger.info("Product deleted: " + selectedProduct.getProductId());
                loadProducts();
            } else {
                logger.severe("Failed to delete product: " + selectedProduct.getProductId());
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete product!");
            }
        } else {
            logger.info("Product deletion cancelled by user");
        }
    }

    @FXML
    private void handleExportReport() {
        logger.info("Export Report button clicked");
        List<Product> products = inventoryService.getAllProducts();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory_report.txt"))) {
            writer.write("Inventory Report:\n");
            writer.write("=================\n\n");
            for (Product product : products) {
                writer.write("Product ID: " + product.getProductId() + "\n");
                writer.write("Product Name: " + product.getName() + "\n");
                writer.write("Price: " + product.getPrice() + "\n");
                writer.write("Stock Quantity: " + product.getStockQuantity() + "\n");
                writer.write("---------------------\n");
            }
            logger.info("Inventory report exported to inventory_report.txt");
            showAlert(Alert.AlertType.INFORMATION, "Export Report", "Report exported successfully to inventory_report.txt");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to export inventory report", e);
            showAlert(Alert.AlertType.ERROR, "Export Error", "Failed to export report!");
        }
    }

    @FXML
    private void handleBack() {
        logger.info("Back button clicked, returning to Main Menu");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retail/app/view/MainMenu.fxml"));
            Parent mainRoot = loader.load();
            Stage currentStage = (Stage) productTable.getScene().getWindow();
            currentStage.setScene(new Scene(mainRoot, 800, 600));
            currentStage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Cannot go back to Main Menu", e);
            showAlert(Alert.AlertType.ERROR, "Error", "Cannot go back to Main Menu!");
        }
    }

    private void openProductForm(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/retail/app/view/ProductForm.fxml"));
            Parent formRoot = loader.load();
            ProductFormController formController = loader.getController();
            if (product != null) {
                formController.setProduct(product);
            }
            Stage formStage = new Stage();
            formStage.setTitle(product == null ? "Add New Product" : "Edit Product");
            formStage.setScene(new Scene(formRoot));
            formStage.initModality(Modality.APPLICATION_MODAL);
            formStage.showAndWait();
            Product updatedProduct = formController.getProduct();
            if (updatedProduct != null) {
                if (product == null) {
                    logger.info("Adding new product: " + updatedProduct.getProductId());
                    inventoryService.addProduct(updatedProduct);
                } else {
                    logger.info("Updating product: " + updatedProduct.getProductId());
                    inventoryService.updateProduct(updatedProduct);
                }
                loadProducts();
            } else {
                logger.info("Product form closed without saving");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Cannot open product form", e);
            showAlert(Alert.AlertType.ERROR, "Error", "Cannot open product form!");
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
