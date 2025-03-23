package model;

import java.io.Serializable;

public class Action implements Serializable {
    private final Object data;

    public Action(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}