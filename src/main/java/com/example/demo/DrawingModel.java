package com.example.demo;

import javafx.scene.paint.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingModel {
    private Color currentColor = Color.BLACK;
    private ShapeType currentShape = ShapeType.FREE_DRAW;
    private List<Double> points = new ArrayList<>(); // Lưu các điểm vẽ tự do
    private List<ShapeData> shapes = new ArrayList<>(); // Lưu các hình vẽ

    public static class ShapeData {
        ShapeType type;
        double startX, startY, endX, endY;
        Color color;

        public ShapeData(ShapeType type, double startX, double startY, double endX, double endY, Color color) {
            this.type = type;
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.color = color;
        }
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public ShapeType getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(ShapeType shapeType) {
        this.currentShape = shapeType;
    }

    public void addPoint(double x, double y) {
        points.add(x);
        points.add(y);
    }

    public List<Double> getPoints() {
        return points;
    }

    public void addShape(ShapeData shape) {
        shapes.add(shape);
    }

    public List<ShapeData> getShapes() {
        return shapes;
    }

    public void clear() {
        points.clear();
        shapes.clear();
    }

}