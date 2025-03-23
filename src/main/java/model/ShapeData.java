package model;

import javafx.scene.paint.Color;
import java.io.Serializable;

public class ShapeData implements Serializable {
    private final ShapeType type;
    private final double startX, startY, endX, endY;
    private final double red, green, blue, opacity;
    private final double brushSize;

    public ShapeData(ShapeType type, double startX, double startY, double endX, double endY, Color color, double brushSize) {
        this.type = type;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.opacity = color.getOpacity();
        this.brushSize = brushSize;
    }

    public ShapeType getType() { return type; }
    public double getStartX() { return startX; }
    public double getStartY() { return startY; }
    public double getEndX() { return endX; }
    public double getEndY() { return endY; }
    public Color getColor() { return new Color(red, green, blue, opacity); }
    public double getBrushSize() { return brushSize; }
}