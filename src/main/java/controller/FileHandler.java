package controller;

import model.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public void save(DrawingModel model, Window owner) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Drawing");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ProSketch Files", "*.psk"));
        File file = fileChooser.showSaveDialog(owner);
        if (file != null) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(model.getActions());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void open(DrawingModel model, MainController controller, Window owner) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Drawing");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ProSketch Files", "*.psk"));
        File file = fileChooser.showOpenDialog(owner);
        if (file != null) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                model.clear();
                Object obj = in.readObject();
                if (obj instanceof ArrayList<?>) {
                    ArrayList<?> rawList = (ArrayList<?>) obj;
                    ArrayList<Action> actions = new ArrayList<>();
                    for (Object item : rawList) {
                        if (item instanceof Action) {
                            actions.add((Action) item);
                        }
                    }
                    for (Action action : actions) {
                        if (action.getData() instanceof StrokeData) {
                            model.addStroke(((StrokeData) action.getData()).getPoints(), ((StrokeData) action.getData()).getColor(), ((StrokeData) action.getData()).getBrushSize());
                        } else if (action.getData() instanceof ShapeData) {
                            model.addShape((ShapeData) action.getData());
                        } else if (action.getData() instanceof TextData) {
                            model.addText((TextData) action.getData());
                        }
                    }
                    controller.setModel(model);
                } else {
                    throw new IOException("Invalid file format: Expected ArrayList<Action>");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}