package com.retail.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenuController {

    private static final Logger logger = Logger.getLogger(MainMenuController.class.getName());

    @FXML
    private void handleManageOrders(ActionEvent event) {
        logger.info("Navigating to Order Management");
        switchScene(event, "/com/retail/app/view/OrderView.fxml", "Order Management");
    }

    @FXML
    private void handleManageInventory(ActionEvent event) {
        logger.info("Navigating to Inventory Management");
        switchScene(event, "/com/retail/app/view/InventoryView.fxml", "Inventory Management");
    }

    private void switchScene(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();
            logger.info("Switched scene to: " + title);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to switch scene to: " + title, e);
        }
    }
}
