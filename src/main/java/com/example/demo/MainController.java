package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class MainController {
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ComboBox<ShapeType> shapeComboBox;

    private DrawingModel model = new DrawingModel();
    private GraphicsContext gc;
    private double startX, startY;

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2.0);
        gc.setStroke(model.getCurrentColor());

        shapeComboBox.getItems().addAll(ShapeType.RECTANGLE, ShapeType.CIRCLE, ShapeType.LINE);
        shapeComboBox.setOnAction(event -> {
            ShapeType selectedShape = shapeComboBox.getValue();
            if (selectedShape != null) {
                model.setCurrentShape(selectedShape);
            }
        });

        colorPicker.setValue(model.getCurrentColor());
    }

    @FXML
    public void setFreeDrawMode() {
        model.setCurrentShape(ShapeType.FREE_DRAW);
        shapeComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    public void setColor() {
        model.setCurrentColor(colorPicker.getValue());
        gc.setStroke(model.getCurrentColor());
    }

    @FXML
    public void startDrawing(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
        if (model.getCurrentShape() == ShapeType.FREE_DRAW) {
            gc.beginPath();
            gc.moveTo(startX, startY);
            model.addPoint(startX, startY);
        }
    }

    @FXML
    public void draw(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        if (model.getCurrentShape() == ShapeType.FREE_DRAW) {
            gc.lineTo(x, y);
            gc.stroke();
            model.addPoint(x, y);
        }
    }

    @FXML
    public void endDrawing(MouseEvent event) {
        double endX = event.getX();
        double endY = event.getY();

        if (model.getCurrentShape() != ShapeType.FREE_DRAW) {
            DrawingModel.ShapeData shape = new DrawingModel.ShapeData(
                    model.getCurrentShape(), startX, startY, endX, endY, model.getCurrentColor());
            model.addShape(shape);
            redrawCanvas();
        }
    }

    @FXML
    public void clearCanvas() {
        model.clear();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (!model.getPoints().isEmpty()) {
            gc.beginPath();
            for (int i = 0; i < model.getPoints().size(); i += 2) {
                double x = model.getPoints().get(i);
                double y = model.getPoints().get(i + 1);
                if (i == 0) gc.moveTo(x, y);
                else gc.lineTo(x, y);
            }
            gc.stroke();
        }

        for (DrawingModel.ShapeData shape : model.getShapes()) {
            gc.setStroke(shape.color);
            double x = Math.min(shape.startX, shape.endX);
            double y = Math.min(shape.startY, shape.endY);
            double width = Math.abs(shape.endX - shape.startX);
            double height = Math.abs(shape.endY - shape.startY);

            switch (shape.type) {
                case RECTANGLE:
                    gc.strokeRect(x, y, width, height);
                    break;
                case CIRCLE:
                    gc.strokeOval(x, y, width, height);
                    break;
                case LINE:
                    gc.strokeLine(shape.startX, shape.startY, shape.endX, shape.endY);
                    break;
            }
        }
    }
}