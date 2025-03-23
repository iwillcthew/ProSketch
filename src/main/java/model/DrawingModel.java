package model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DrawingModel {
    private List<Action> actions = new ArrayList<>();
    private Stack<Action> undoStack = new Stack<>();
    private Stack<Action> redoStack = new Stack<>();
    private Color currentColor = Color.BLACK;
    private double brushSize = 2.0;
    private ShapeType currentShape = ShapeType.FREE_DRAW;

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setBrushSize(double size) {
        this.brushSize = size;
    }

    public double getBrushSize() {
        return brushSize;
    }

    public void setCurrentShape(ShapeType shape) {
        this.currentShape = shape;
    }

    public ShapeType getCurrentShape() {
        return currentShape;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void addStroke(List<Double> points, Color color, double brushSize) {
        actions.add(new Action(new StrokeData(points, color, brushSize)));
        undoStack.push(actions.get(actions.size() - 1));
        redoStack.clear();
    }

    public void addShape(ShapeData shape) {
        actions.add(new Action(shape));
        undoStack.push(actions.get(actions.size() - 1));
        redoStack.clear();
    }

    public void addText(TextData text) {
        actions.add(new Action(text));
        undoStack.push(actions.get(actions.size() - 1));
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            redoStack.push(action);
            actions.remove(action);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop();
            actions.add(action);
            undoStack.push(action);
        }
    }

    public void clear() {
        actions.clear();
        undoStack.clear();
        redoStack.clear();
    }
}