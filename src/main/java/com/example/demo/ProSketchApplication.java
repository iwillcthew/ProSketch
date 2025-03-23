package com.example.demo;

import controller.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProSketchApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/HomeView.fxml"));
        Parent root = loader.load();
        HomeController homeController = loader.getController();
        homeController.setStage(primaryStage);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/demo/style.css").toExternalForm());
        primaryStage.setTitle("ProSketch - Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}