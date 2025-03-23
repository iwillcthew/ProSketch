package model;

import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StrokeData implements Serializable {
    private final List<Double> points;
    private final double red, green, blue, opacity;
    private final double brushSize;

    public StrokeData(List<Double> points, Color color, double brushSize) {
        this.points = new ArrayList<>(points);
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.opacity = color.getOpacity();
        this.brushSize = brushSize;
    }

    public Color getColor() {
        return new Color(red, green, blue, opacity);
    }

    public List<Double> getPoints() {
        return new ArrayList<>(points);
    }

    public double getBrushSize() {
        return brushSize;
    }
}