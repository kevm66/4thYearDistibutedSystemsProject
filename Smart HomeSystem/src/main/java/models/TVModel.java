package models;

/* @File Title:ClientManager.java							
 *
 * @author:Kevin Maher,     x14328981
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
public class TVModel {

    public enum Action {
        STATUS, ON, OFF, CONNECT, DISCONNECT, INCREASE_VOLUME, DECREASE_VOLUME, NEXT_CHANNEL, PREVIOUS_CHANNEL;
    }
    private Action action;
    private String message;
    private boolean value;

    public TVModel(Action action) {
        this.action = action;
    }

    public TVModel(Action action, String message) {
        this.action = action;
        this.message = message;
    }

    public TVModel(Action action, String message, boolean value) {
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
