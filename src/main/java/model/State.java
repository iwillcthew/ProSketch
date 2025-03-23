package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class State implements Serializable {
    private final List<Action> actions;

    public State(List<Action> actions) {
        this.actions = new ArrayList<>(actions);
    }

    public List<Action> getActions() {
        return new ArrayList<>(actions);
    }
}