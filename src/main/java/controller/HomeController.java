package controller;

import model.DrawingModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void startNewDrawing() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/MainView.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setModel(new DrawingModel());
        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/demo/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("ProSketch - Drawing");
    }

    @FXML
    public void openFile() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/MainView.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        new FileHandler().open(new DrawingModel(), controller, stage);
        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/demo/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("ProSketch - Drawing");
    }

    @FXML
    public void exitApp() {
        stage.close();
    }
}