package model;

import javafx.scene.paint.Color;
import java.io.Serializable;

public class TextData implements Serializable {
    private final String text;
    private final double x, y;
    private final double red, green, blue, opacity;
    private final double fontSize;

    public TextData(String text, double x, double y, Color color, double fontSize) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.opacity = color.getOpacity();
        this.fontSize = fontSize;
    }

    public String getText() {
        return text;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getColor() {
        return new Color(red, green, blue, opacity);
    }

    public double getFontSize() {
        return fontSize;
    }
}