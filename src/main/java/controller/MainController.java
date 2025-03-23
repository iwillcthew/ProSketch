package controller;

import model.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class MainController {
    @FXML private Canvas canvas;
    @FXML private ColorPicker colorPicker;
    @FXML private ComboBox<ShapeType> shapeComboBox;
    @FXML private Slider brushSizeSlider;
    @FXML private Label brushSizeLabel;
    @FXML private Label mousePositionLabel;
    @FXML private Label statusLabel;
    @FXML private Button colorBlack, colorWhite, colorRed, colorGreen, colorBlue, colorYellow;

    private DrawingModel model;
    private GraphicsContext gc;
    private double startX, startY;
    private List<Double> tempStroke = new ArrayList<>();
    private Color tempStrokeColor;
    private double tempStrokeSize;

    @FXML
    public void initialize() {
        if (model == null) {
            model = new DrawingModel();
        }
        gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(model.getBrushSize());
        gc.setStroke(model.getCurrentColor());

        shapeComboBox.getItems().addAll(
                ShapeType.RECTANGLE,
                ShapeType.CIRCLE,
                ShapeType.LINE,
                ShapeType.TRIANGLE,
                ShapeType.STAR,
                ShapeType.HEXAGON
        );
        shapeComboBox.setOnAction(e -> {
            ShapeType selectedShape = shapeComboBox.getValue();
            if (selectedShape != null) {
                model.setCurrentShape(selectedShape);
                statusLabel.setText(selectedShape.toString());
            }
        });
        brushSizeSlider.setMin(1);
        brushSizeSlider.setMax(20);
        brushSizeSlider.setValue(model.getBrushSize());
        brushSizeLabel.setText(String.format("%.1f", model.getBrushSize()));
        brushSizeSlider.valueProperty().addListener((obs, old, newVal) -> {
            model.setBrushSize(newVal.doubleValue());
            gc.setLineWidth(model.getBrushSize());
            brushSizeLabel.setText(String.format("%.1f", newVal.doubleValue()));
        });
        colorPicker.setValue(model.getCurrentColor());

        redrawCanvas();
    }

    public void setModel(DrawingModel model) {
        this.model = model;
        gc.setLineWidth(model.getBrushSize());
        gc.setStroke(model.getCurrentColor());
        colorPicker.setValue(model.getCurrentColor());
        brushSizeSlider.setValue(model.getBrushSize());
        brushSizeLabel.setText(String.format("%.1f", model.getBrushSize()));
        redrawCanvas();
    }

    @FXML public void setFreeDrawMode() {
        model.setCurrentShape(ShapeType.FREE_DRAW);
        shapeComboBox.getSelectionModel().clearSelection();
        statusLabel.setText("Free Draw");
    }

    @FXML public void setEraserMode() {
        model.setCurrentShape(ShapeType.ERASER);
        shapeComboBox.getSelectionModel().clearSelection();
        statusLabel.setText("Eraser");
    }

    @FXML public void setTextMode() {
        model.setCurrentShape(ShapeType.TEXT);
        shapeComboBox.getSelectionModel().clearSelection();
        statusLabel.setText("Text");
    }

    @FXML public void setColor() {
        if (model.getCurrentShape() == ShapeType.ERASER) {
            model.setCurrentShape(ShapeType.FREE_DRAW);
            shapeComboBox.getSelectionModel().clearSelection();
            statusLabel.setText("Free Draw (from Eraser)");
        }
        model.setCurrentColor(colorPicker.getValue());
        gc.setStroke(model.getCurrentColor());
        if (model.getCurrentShape() != ShapeType.FREE_DRAW) {
            statusLabel.setText("Color Changed");
        }
    }

    @FXML public void setColorBlack() {
        if (model.getCurrentShape() == ShapeType.ERASER) {
            model.setCurrentShape(ShapeType.FREE_DRAW);
            shapeComboBox.getSelectionModel().clearSelection();
            statusLabel.setText("Free Draw (from Eraser)");
        }
        model.setCurrentColor(Color.BLACK);
        colorPicker.setValue(Color.BLACK);
        gc.setStroke(Color.BLACK);
        if (model.getCurrentShape() != ShapeType.FREE_DRAW) {
            statusLabel.setText("Color: Black");
        }
    }

    @FXML public void setColorWhite() {
        if (model.getCurrentShape() == ShapeType.ERASER) {
            model.setCurrentShape(ShapeType.FREE_DRAW);
            shapeComboBox.getSelectionModel().clearSelection();
            statusLabel.setText("Free Draw (from Eraser)");
        }
        model.setCurrentColor(Color.WHITE);
        colorPicker.setValue(Color.WHITE);
        gc.setStroke(Color.WHITE);
        if (model.getCurrentShape() != ShapeType.FREE_DRAW) {
            statusLabel.setText("Color: White");
        }
    }

    @FXML public void setColorRed() {
        if (model.getCurrentShape() == ShapeType.ERASER) {
            model.setCurrentShape(ShapeType.FREE_DRAW);
            shapeComboBox.getSelectionModel().clearSelection();
            statusLabel.setText("Free Draw (from Eraser)");
        }
        model.setCurrentColor(Color.RED);
        colorPicker.setValue(Color.RED);
        gc.setStroke(Color.RED);
        if (model.getCurrentShape() != ShapeType.FREE_DRAW) {
            statusLabel.setText("Color: Red");
        }
    }

    @FXML public void setColorGreen() {
        if (model.getCurrentShape() == ShapeType.ERASER) {
            model.setCurrentShape(ShapeType.FREE_DRAW);
            shapeComboBox.getSelectionModel().clearSelection();
            statusLabel.setText("Free Draw (from Eraser)");
        }
        model.setCurrentColor(Color.GREEN);
        colorPicker.setValue(Color.GREEN);
        gc.setStroke(Color.GREEN);
        if (model.getCurrentShape() != ShapeType.FREE_DRAW) {
            statusLabel.setText("Color: Green");
        }
    }

    @FXML public void setColorBlue() {
        if (model.getCurrentShape() == ShapeType.ERASER) {
            model.setCurrentShape(ShapeType.FREE_DRAW);
            shapeComboBox.getSelectionModel().clearSelection();
            statusLabel.setText("Free Draw (from Eraser)");
        }
        model.setCurrentColor(Color.BLUE);
        colorPicker.setValue(Color.BLUE);
        gc.setStroke(Color.BLUE);
        if (model.getCurrentShape() != ShapeType.FREE_DRAW) {
            statusLabel.setText("Color: Blue");
        }
    }

    @FXML public void setColorYellow() {
        if (model.getCurrentShape() == ShapeType.ERASER) {
            model.setCurrentShape(ShapeType.FREE_DRAW);
            shapeComboBox.getSelectionModel().clearSelection();
            statusLabel.setText("Free Draw (from Eraser)");
        }
        model.setCurrentColor(Color.YELLOW);
        colorPicker.setValue(Color.YELLOW);
        gc.setStroke(Color.YELLOW);
        if (model.getCurrentShape() != ShapeType.FREE_DRAW) {
            statusLabel.setText("Color: Yellow");
        }
    }

    @FXML public void startDrawing(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();

        if (model.getCurrentShape() == ShapeType.TEXT) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Enter Text");
            dialog.setHeaderText("Enter the text to add to the canvas:");
            dialog.setContentText("Text:");
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(text -> {
                if (!text.trim().isEmpty()) {
                    model.addText(new TextData(text, startX, startY, model.getCurrentColor(), model.getBrushSize() * 5));
                    redrawCanvas();
                    statusLabel.setText("Text Added");
                }
            });
        } else {
            tempStroke.clear();
            tempStrokeColor = (model.getCurrentShape() == ShapeType.ERASER) ? Color.WHITE : model.getCurrentColor();
            tempStrokeSize = model.getBrushSize();
            if (model.getCurrentShape() == ShapeType.FREE_DRAW || model.getCurrentShape() == ShapeType.ERASER) {
                gc.beginPath();
                gc.setLineWidth(tempStrokeSize);
                gc.setStroke(tempStrokeColor);
                gc.moveTo(startX, startY);
                tempStroke.add(startX);
                tempStroke.add(startY);
            }
            statusLabel.setText("Drawing");
        }
    }

    @FXML public void draw(MouseEvent e) {
        if (model.getCurrentShape() == ShapeType.FREE_DRAW || model.getCurrentShape() == ShapeType.ERASER) {
            double x = e.getX(), y = e.getY();
            gc.lineTo(x, y);
            gc.stroke();
            tempStroke.add(x);
            tempStroke.add(y);
        }
    }

    @FXML public void endDrawing(MouseEvent e) {
        double endX = e.getX(), endY = e.getY();
        if (model.getCurrentShape() == ShapeType.FREE_DRAW || model.getCurrentShape() == ShapeType.ERASER) {
            if (tempStroke.size() >= 4) model.addStroke(tempStroke, tempStrokeColor, tempStrokeSize);
        } else if (model.getCurrentShape() != ShapeType.TEXT) {
            model.addShape(new ShapeData(model.getCurrentShape(), startX, startY, endX, endY, model.getCurrentColor(), model.getBrushSize()));
        }
        redrawCanvas();
        if (model.getCurrentShape() != ShapeType.TEXT) {
            statusLabel.setText("Ready");
        }
    }

    @FXML public void updateMousePosition(MouseEvent e) {
        mousePositionLabel.setText(String.format("Mouse: (%.0f, %.0f)", e.getX(), e.getY()));
    }

    @FXML public void clearCanvas() {
        model.clear();
        redrawCanvas();
        statusLabel.setText("Canvas Cleared");
    }

    @FXML public void undo() {
        model.undo();
        redrawCanvas();
        statusLabel.setText("Undo");
    }

    @FXML public void redo() {
        model.redo();
        redrawCanvas();
        statusLabel.setText("Redo");
    }

    @FXML public void saveDrawing() {
        new FileHandler().save(model, canvas.getScene().getWindow());
        statusLabel.setText("Saved");
    }

    @FXML public void openDrawing() {
        new FileHandler().open(model, this, canvas.getScene().getWindow());
        statusLabel.setText("Opened");
    }

    @FXML public void exportToPNG() {
        WritableImage image = canvas.snapshot(null, null);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export to PNG");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File file = fileChooser.showSaveDialog(canvas.getScene().getWindow());

        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                statusLabel.setText("Exported to PNG");
            } catch (IOException e) {
                e.printStackTrace();
                statusLabel.setText("Export Failed");
            }
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void redrawCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Action action : model.getActions()) {
            Object data = action.getData();
            if (data instanceof StrokeData) {
                StrokeData stroke = (StrokeData) data;
                if (stroke.getPoints().size() >= 4) {
                    gc.beginPath();
                    gc.setLineWidth(stroke.getBrushSize());
                    gc.setStroke(stroke.getColor());
                    for (int i = 0; i < stroke.getPoints().size() - 1; i += 2) {
                        double x = stroke.getPoints().get(i), y = stroke.getPoints().get(i + 1);
                        if (i == 0) gc.moveTo(x, y); else gc.lineTo(x, y);
                    }
                    gc.stroke();
                }
            } else if (data instanceof ShapeData) {
                ShapeData shape = (ShapeData) data;
                gc.setStroke(shape.getColor());
                gc.setLineWidth(shape.getBrushSize());
                double x = Math.min(shape.getStartX(), shape.getEndX());
                double y = Math.min(shape.getStartY(), shape.getEndY());
                double width = Math.abs(shape.getEndX() - shape.getStartX());
                double height = Math.abs(shape.getEndY() - shape.getStartY());
                double centerX = x + width / 2;
                double centerY = y + height / 2;

                if (shape.getType() != null) {
                    switch (shape.getType()) {
                        case RECTANGLE:
                            gc.strokeRect(x, y, width, height);
                            break;
                        case CIRCLE:
                            gc.strokeOval(x, y, width, height);
                            break;
                        case LINE:
                            gc.strokeLine(shape.getStartX(), shape.getStartY(), shape.getEndX(), shape.getEndY());
                            break;
                        case TRIANGLE:
                            double[] xPoints = {centerX, x, x + width};
                            double[] yPoints = {y, y + height, y + height};
                            gc.strokePolygon(xPoints, yPoints, 3);
                            break;
                        case STAR:
                            double outerRadius = Math.min(width, height) / 2;
                            double innerRadius = outerRadius / 2.5;
                            double[] starX = new double[10];
                            double[] starY = new double[10];
                            for (int i = 0; i < 10; i++) {
                                double radius = (i % 2 == 0) ? outerRadius : innerRadius;
                                double angle = Math.PI / 2 + i * Math.PI / 5;
                                starX[i] = centerX + radius * Math.cos(angle);
                                starY[i] = centerY - radius * Math.sin(angle);
                            }
                            gc.strokePolygon(starX, starY, 10);
                            break;
                        case HEXAGON:
                            double side = Math.min(width, height) / 2;
                            double[] hexX = new double[6];
                            double[] hexY = new double[6];
                            for (int i = 0; i < 6; i++) {
                                hexX[i] = centerX + side * Math.cos(i * Math.PI / 3);
                                hexY[i] = centerY + side * Math.sin(i * Math.PI / 3);
                            }
                            gc.strokePolygon(hexX, hexY, 6);
                            break;
                    }
                }
            } else if (data instanceof TextData) {
                TextData text = (TextData) data;
                gc.setFill(text.getColor());
                gc.setFont(new Font(text.getFontSize()));
                gc.fillText(text.getText(), text.getX(), text.getY());
            }
        }
        gc.setLineWidth(model.getBrushSize());
        gc.setStroke(model.getCurrentColor());
    }
}