package models;

/**
 *
 * @author Karry
 */

public class LightsModel {

    public enum Action {
        lighten, darken, STATUS, lightOff, lightOn
    }

    private Action action;
    private String message;
    private boolean value;

    public LightsModel() {
    }

    public LightsModel(Action action) {
        this.action = action;
    }

    public LightsModel(Action action, String message) {
        this.action = action;
        this.message = message;
    }

    public LightsModel(Action action, String message, boolean value) {
        this.action = action;
        this.message = message;
        this.value = value;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
