package com.retail.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/retail/app/view/MainMenu.fxml"));
        Scene scene = new Scene(root, 800, 600); // Default window size
        scene.getStylesheets().add(getClass().getResource("/com/retail/app/css/style.css").toExternalForm());

        primaryStage.setTitle("ShopMaster");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Allow user to resize
        // No fullscreen or maximized settings
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}