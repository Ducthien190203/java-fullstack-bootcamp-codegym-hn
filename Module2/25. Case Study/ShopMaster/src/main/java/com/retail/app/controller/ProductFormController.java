package com.retail.app.controller;

import com.retail.app.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductFormController {

    private static final Logger logger = Logger.getLogger(ProductFormController.class.getName());

    @FXML
    private TextField productIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField stockField;

    private Product confirmedProduct = null;

    // Phương thức này sẽ được gọi khi người dùng nhấn nút "Save"
    @FXML
    private void handleConfirm() {
        logger.info("Product form: Save button clicked");
        // Kiểm tra tên sản phẩm không để trống
        if (nameField.getText() == null || nameField.getText().trim().isEmpty()) {
            logger.warning("Product form: Name is empty");
            showAlert(Alert.AlertType.WARNING, "Warning", "Product name is required.");
            return;
        }
        try {
            String productId = productIdField.getText();
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stockQuantity = Integer.parseInt(stockField.getText());

            confirmedProduct = new Product(productId, name, price, stockQuantity);

            logger.info("Product form: Product confirmed - " + productId);
            // Đóng cửa sổ khi lưu thành công
            Stage stage = (Stage) productIdField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Product form: Invalid input for price or stock", e);
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input! Price and stock must be numeric.");
        }
    }

    // Xử lý nút "Cancel" đóng cửa sổ mà không lưu
    @FXML
    private void handleCancel() {
        logger.info("Product form: Cancel button clicked");
        confirmedProduct = null;
        Stage stage = (Stage) productIdField.getScene().getWindow();
        stage.close();
    }

    // Phương thức trả về sản phẩm đã được nhập (hoặc null nếu hủy)
    public Product getProduct() {
        return confirmedProduct;
    }

    // Thiết lập dữ liệu cho form khi chỉnh sửa sản phẩm
    public void setProduct(Product product) {
        if (product != null) {
            logger.info("Product form: Editing product - " + product.getProductId());
            productIdField.setText(product.getProductId());
            nameField.setText(product.getName());
            priceField.setText(String.valueOf(product.getPrice()));
            stockField.setText(String.valueOf(product.getStockQuantity()));
        }
    }

    // Phương thức hiển thị thông báo
    private void showAlert(Alert.AlertType type, String title, String message) {
        logger.info("Product form: Showing alert [" + type + "] " + title + ": " + message);
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
